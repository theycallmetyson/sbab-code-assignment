package se.tcmt.sbab.busline.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import se.tcmt.sbab.busline.models.BaseModel;
import se.tcmt.sbab.busline.models.JourneyPatternPointOnLine;
import se.tcmt.sbab.busline.models.StopPoint;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class LineService {

    @Value("${line.url}")
    private String lineUrl;

    @Value("${stop.point.url}")
    private String stopUrl;

    @Value("${journey.pattern.url}")
    private String journeyUrl;
    private final OkHttpClient client = new OkHttpClient();

    @Cacheable("lines")
    public Map<String, Collection<?>> getAllBusLines(Optional<Integer> topRanks) throws IOException {
        if (topRanks.isPresent()) {
            return getAllStopPointsGroupedByLineNumber(topRanks.get());
        } else {
            return Collections.singletonMap("Lines", fetchData(lineUrl, new TypeReference<>() {
            }));
        }
    }

    private Collection<StopPoint> getAllBusStops() throws IOException {
        Collection<StopPoint> stopPoints = fetchData(stopUrl, new TypeReference<>() {
        });
        stopPoints.removeIf(stopPoint -> !stopPoint.getStopAreaTypeCode().equals("BUSTERM"));
        return stopPoints;
    }

    private Collection<JourneyPatternPointOnLine> getAllBusJourneyPatterns() throws IOException {
        return fetchData(journeyUrl, new TypeReference<>() {
        });
    }

    private Map<String, Collection<JourneyPatternPointOnLine>> getBusJourneyPatternsByTopRank(int topRanks) throws IOException {
        Collection<JourneyPatternPointOnLine> journeyPatterns = getAllBusJourneyPatterns();

        return journeyPatterns.stream()
                .collect(Collectors.groupingBy(JourneyPatternPointOnLine::getLineNumber))
                .entrySet().parallelStream()
                .sorted(Comparator.comparingInt(entry -> -entry.getValue().size()))
                .limit(topRanks)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (v1, v2) -> v1, LinkedHashMap::new));
    }

    private Map<String, Collection<?>> getAllStopPointsGroupedByLineNumber(Integer topRanks) throws IOException {
        Collection<StopPoint> allStopPoints = getAllBusStops();
        Map<String, Collection<JourneyPatternPointOnLine>> topJourneyPatterns = getBusJourneyPatternsByTopRank(topRanks);
        Map<String, Collection<?>> stopPointsGroupedByTopLineNumbers = new HashMap<>();

        for (Map.Entry<String, Collection<JourneyPatternPointOnLine>> entry : topJourneyPatterns.entrySet()) {
            Collection<StopPoint> stopPoints = new ArrayList<>();
            Collection<JourneyPatternPointOnLine> journeyPatterns = entry.getValue();

            for (JourneyPatternPointOnLine journeyPattern : journeyPatterns) {
                List<String> stopPointNumbers = Arrays.asList(journeyPattern.getJourneyPatternPointNumber().split(","));
                for (StopPoint stopPoint : allStopPoints) {
                    if (stopPointNumbers.contains(String.valueOf(stopPoint.getStopPointNumber()))) {
                        stopPoints.add(stopPoint);
                    }
                }
            }
            stopPointsGroupedByTopLineNumbers.put(entry.getKey(), stopPoints);
        }
        return stopPointsGroupedByTopLineNumbers;
    }

    private <T> Collection<T> fetchData(String url, TypeReference<BaseModel<T>> typeReference) throws IOException {
        Request request = new Request.Builder().url(url).build();
        ObjectMapper objectMapper = new ObjectMapper();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code: " + response);
            }
            assert response.body() != null;
            BaseModel<T> data = objectMapper.readValue(response.body().string(), typeReference);

            return data.getResponseData().getResult();
        }
    }
}

package se.tcmt.sbab.busline.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import se.tcmt.sbab.busline.models.BaseModel;
import se.tcmt.sbab.busline.models.JourneyPatternPointOnLine;
import se.tcmt.sbab.busline.models.Line;
import se.tcmt.sbab.busline.models.StopPoint;

import java.io.IOException;
import java.util.Collection;

@Service
public class LineService {

    @Value("${line.url}")
    private String lineUrl;

    @Value("${stop.point.url}")
    private String stopUrl;

    @Value("${journey.pattern.url}")
    private String journeyUrl;
    private final OkHttpClient client = new OkHttpClient();

    public Collection<Line> getAllBusLines() throws IOException {
        return fetchData(lineUrl, new TypeReference<>() {
        });
    }

    public Collection<StopPoint> getAllBusStops() throws IOException {
        Collection<StopPoint> stopPoints = fetchData(stopUrl, new TypeReference<>() {
        });
        stopPoints.removeIf(stopPoint -> !stopPoint.getStopAreaTypeCode().equals("BUSTERM"));
        return stopPoints;
    }

    public Collection<JourneyPatternPointOnLine> getAllBusJourneyPatterns() throws IOException {
        return fetchData(journeyUrl, new TypeReference<>() {
        });
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

package se.tcmt.sbab.busline.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import se.tcmt.sbab.busline.models.BaseModel;
import se.tcmt.sbab.busline.models.Line;

import java.io.IOException;
import java.util.Collection;

@Service
public class LineService {

    @Value("${line.url}")
    private String url;
    private final OkHttpClient client = new OkHttpClient();

    public Collection<Line> getAllLines() throws IOException {
        return fetchData(url);
    }

    private <T> Collection<T> fetchData(String url) throws IOException {
        Request request = new Request.Builder().url(url).build();
        ObjectMapper objectMapper = new ObjectMapper();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code: " + response);
            }
            BaseModel<T> data = objectMapper.readValue(response.body().string(), new TypeReference<>() {
            });

            return data.getResponseData().getResult();
        }
    }
}

package se.tcmt.sbab.busline.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import se.tcmt.sbab.busline.models.BaseModel;
import se.tcmt.sbab.busline.models.Line;

import java.util.Collection;

@Service
public class LineService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${line.url}")
    private String url;

    private HttpEntity<?> requestEntityWithCompressionHeaders() {
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.add(HttpHeaders.ACCEPT_ENCODING, "gzip");
        return new HttpEntity<>(requestHeaders);
    }

    public Collection<Line> getAllLines() {
        BaseModel<Line> response = fetchAllLines();
        return response.getResponseData().getResult();
    }

    private BaseModel<Line> fetchAllLines() {
        ResponseEntity<BaseModel<Line>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                requestEntityWithCompressionHeaders(),
                new ParameterizedTypeReference<>() {
                });
        return response.getBody();
    }
}

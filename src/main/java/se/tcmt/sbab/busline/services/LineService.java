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
import se.tcmt.sbab.busline.models.Line;
import se.tcmt.sbab.busline.models.ResponseModel;

import java.util.List;

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

    public List<Line> getAllLines() {
        ResponseEntity<ResponseModel<Line>> responseModel = getLines();

        return responseModel.getBody().getResponseData().getResult();
    }

    private ResponseEntity<ResponseModel<Line>> getLines() {
        ResponseEntity<ResponseModel<Line>> response = restTemplate
                .exchange(
                        url,
                        HttpMethod.GET,
                        requestEntityWithCompressionHeaders(),
                        new ParameterizedTypeReference<>() {
                        }
                );

        return ResponseEntity
                .status(response.getStatusCode())
                .headers(response.getHeaders())
                .body(response.getBody());
    }
}

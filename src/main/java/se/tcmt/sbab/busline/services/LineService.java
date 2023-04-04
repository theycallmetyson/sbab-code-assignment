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
import java.util.Optional;

@Service
public class LineService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${line.url}")
    private String url;


    public List<Line> getLines(Optional<Integer> top) {
        ResponseEntity<ResponseModel<Line>> responseModel = invokeExternalAPI(top);

        return responseModel.getBody().getResponseData().getResult();
    }

    private HttpEntity<?> requestEntityWithCompressionHeaders() {
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.add(HttpHeaders.ACCEPT_ENCODING, "gzip");
        return new HttpEntity<>(requestHeaders);
    }

    private ResponseEntity<ResponseModel<Line>> invokeExternalAPI(Optional<Integer> top) {
        ResponseEntity<ResponseModel<Line>> response;

        if (top.isPresent()) {
            return null;
        } else {
            response = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    requestEntityWithCompressionHeaders(),
                    new ParameterizedTypeReference<>() {
                    }
            );
        }

        return response;
    }
}

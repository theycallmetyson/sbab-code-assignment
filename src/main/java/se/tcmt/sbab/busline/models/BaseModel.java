package se.tcmt.sbab.busline.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class BaseModel<T> {
    @JsonProperty("ExecutionTime")
    private int executionTime;
    @JsonProperty("StatusCode")
    private int statusCode;
    @JsonProperty("Message")
    private String message;
    @JsonProperty("ResponseData")
    private ResponseData<T> responseData;
}

package se.tcmt.sbab.busline.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

@Data
@JsonPropertyOrder({"StatusCode", "Message", "ExecutionTime", "ResponseData"})
public class ResponseModel<T> {
    @JsonProperty("StatusCode")
    private int statusCode;
    @JsonProperty("Message")
    private Object message;
    @JsonProperty("ExecutionTime")
    private int executionTime;
    @JsonProperty("ResponseData")
    private ResponseData<T> responseData;
}

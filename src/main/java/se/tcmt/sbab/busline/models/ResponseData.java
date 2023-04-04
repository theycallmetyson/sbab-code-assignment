package se.tcmt.sbab.busline.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

import java.util.List;

@Data
@JsonPropertyOrder({"Version", "Type", "Result"})
public class ResponseData<T> {
    @JsonProperty("Version")
    private String version;
    @JsonProperty("Type")
    private String type;
    @JsonProperty("Result")
    private List<T> result;
}

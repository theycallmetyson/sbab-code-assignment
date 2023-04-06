package se.tcmt.sbab.busline.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Collection;

@Data
public class ResponseData<T> {
    @JsonProperty("Type")
    private String type;
    @JsonProperty("Version")
    private String version;
    @JsonProperty("Result")
    private Collection<T> result;
}

package se.tcmt.sbab.busline.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Line {
    private int lineNumber;
    @JsonProperty("DefaultTransportMode")
    private String defaultTransportMode;
    @JsonProperty("DefaultTransportModeCode")
    private String defaultTransportModeCode;
    @JsonProperty("ExistsFromDate")
    private String existsFromDate;
    @JsonProperty("ExistsToDate")
    private String lastModifiedUtcDateTime;
    @JsonProperty("LineDesignation")
    private String lineDesignation;
}

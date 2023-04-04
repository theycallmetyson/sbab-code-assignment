package se.tcmt.sbab.busline.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
@JsonPropertyOrder({"LineNumber", "LineDesignation", "DefaultTransportMode", "DefaultTransportModeCode", "LastModifiedUtcDateTime", "ExistsFromDate"})
public class Line {
    @Id
    @JsonProperty("LineNumber")
    private int lineNumber;
    @JsonProperty("LineDesignation")
    private String lineDesignation;
    @JsonProperty("DefaultTransportMode")
    private String defaultTransportMode;
    @JsonProperty("DefaultTransportModeCode")
    private String defaultTransportModeCode;
    @JsonProperty("LastModifiedUtcDateTime")
    private String lastModifiedUtcDateTime;
    @JsonProperty("ExistsFromDate")
    private String existsFromDate;
}

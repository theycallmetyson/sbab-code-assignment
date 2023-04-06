package se.tcmt.sbab.busline.models;

import lombok.Data;

@Data
public class Line {
    private int lineNumber;
    private String lineDesignation;
    private String defaultTransportMode;
    private String defaultTransportModeCode;
    private String lastModifiedUtcDateTime;
    private String existsFromDate;
}

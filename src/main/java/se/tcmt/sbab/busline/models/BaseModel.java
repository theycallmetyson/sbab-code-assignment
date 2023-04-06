package se.tcmt.sbab.busline.models;

import lombok.Data;

@Data
public class BaseModel<T> {
    private int statusCode;
    private Object message;
    private int executionTime;
    private ResponseData<T> responseData;
}

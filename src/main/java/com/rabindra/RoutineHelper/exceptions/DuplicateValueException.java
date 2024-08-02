package com.rabindra.RoutineHelper.exceptions;

import lombok.Data;

@Data
public class DuplicateValueException extends RuntimeException{

    private int errorCode;
    private String errorDescription;
    private String message;

    public DuplicateValueException(int errorCode, String errorDescription, String message) {
        this.errorCode=errorCode;
        this.errorDescription=errorDescription;
        this.message=message;
    }
}

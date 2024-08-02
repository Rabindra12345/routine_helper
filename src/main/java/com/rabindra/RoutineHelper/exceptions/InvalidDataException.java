package com.rabindra.RoutineHelper.exceptions;

import lombok.Data;

@Data
public class InvalidDataException extends RuntimeException{

    private int errorCode;
    private String errorDescription;
    private String message;

    public InvalidDataException(int errorCode, String errorDescription, String message) {
        this.errorCode=errorCode;
        this.errorDescription=errorDescription;
        this.message=message;
    }
}
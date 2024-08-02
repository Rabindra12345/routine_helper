package com.rabindra.RoutineHelper.exceptions;

import lombok.Data;

@Data
public class PayLoadIsEmptyException extends RuntimeException{

    private int errorCode;
    private String errorDescription;
    private String message;

    public PayLoadIsEmptyException(int errorCode, String errorDescription, String message) {
        this.errorCode=errorCode;
        this.errorDescription=errorDescription;
        this.message=message;
    }
}

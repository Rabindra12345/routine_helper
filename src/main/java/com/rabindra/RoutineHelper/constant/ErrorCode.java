package com.rabindra.RoutineHelper.constant;

import lombok.Getter;
import org.springframework.http.HttpStatus;
@Getter
public enum ErrorCode {
    OK(0,"Ok",HttpStatus.OK),
    // BAD_REQUEST(400,HttpStatus.BAD_REQUEST),
    ALREADY_EXISTS(604,"Please Provide Another %S",HttpStatus.BAD_REQUEST),
    INVALID_DATA(606,"Please Provide Valid Data",HttpStatus.BAD_REQUEST),
    NOT_FOUND(404,"Please Provide Valid %S",HttpStatus.BAD_REQUEST),
    IS_NULL(4,"Please Provide Valid %S",HttpStatus.BAD_REQUEST),
    INTERNAL_SERVER_ERROR(500,"Please Contact Support Team",HttpStatus.INTERNAL_SERVER_ERROR);

    private final int code;
    private final String message;
    private final HttpStatus httpStatus;

    private ErrorCode(int code, String message, HttpStatus httpStatus){
        this.code=code;
        this.message=message;
        this.httpStatus=httpStatus;
    }


}


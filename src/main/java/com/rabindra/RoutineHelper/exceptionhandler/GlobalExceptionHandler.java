package com.rabindra.RoutineHelper.exceptionhandler;

import com.rabindra.RoutineHelper.dtos.ApiResponse;
import com.rabindra.RoutineHelper.exceptions.InvalidDataException;
import com.rabindra.RoutineHelper.exceptions.NotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ApiResponse<?>> handleNotFoundException(NotFoundException ex, WebRequest request) {
        ApiResponse<?> response = ApiResponse.forError(ex.getErrorCode(), ex.getErrorDescription());
        response.setMessage(ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidDataException.class)
    public ResponseEntity<ApiResponse<?>> handleInvalidDataException(HttpServletRequest request, HttpServletResponse response, InvalidDataException exception) {
        ApiResponse<?> apiResponse = ApiResponse.forError(exception.getErrorCode(), exception.getErrorDescription(), exception.getMessage());
        return ResponseEntity.badRequest().body(apiResponse);
    }

}


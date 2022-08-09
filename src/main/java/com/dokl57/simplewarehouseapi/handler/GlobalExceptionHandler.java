package com.dokl57.simplewarehouseapi.handler;

import com.dokl57.simplewarehouseapi.exception.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.LinkedHashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = {
            ValidationException.class
    })
    protected ResponseEntity<Object> handleBadRequest(RuntimeException ex) {
        return handle(ex, HttpStatus.BAD_REQUEST);
    }


    protected ResponseEntity<Object> handle(RuntimeException ex, HttpStatus httpStatus) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("message", ex.getMessage());
        return new ResponseEntity<>(body, httpStatus);
    }

}
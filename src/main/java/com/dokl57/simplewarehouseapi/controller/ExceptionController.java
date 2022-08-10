package com.dokl57.simplewarehouseapi.controller;

import com.dokl57.simplewarehouseapi.exception.ValidationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionController {
    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<String> handleException(ValidationException e){
        return ResponseEntity.badRequest().body("{\n" +
                "\"message\" : \"" +
                e.getMessage() + "\"\n}");
    }
}
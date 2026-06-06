package com.hcl.lead.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errorPayload = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> 
            errorPayload.put(error.getField(), error.getDefaultMessage())
        );
        return new ResponseEntity<>(errorPayload, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, String>> handleBusinessViolations(IllegalArgumentException ex) {
        Map<String, String> errorPayload = new HashMap<>();
        errorPayload.put("error", ex.getMessage());
        return new ResponseEntity<>(errorPayload, HttpStatus.CONFLICT);
    }
}

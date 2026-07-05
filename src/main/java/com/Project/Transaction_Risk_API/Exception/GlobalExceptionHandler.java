package com.Project.Transaction_Risk_API.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String,String> handleValidationException(MethodArgumentNotValidException ex) {
        Map<String,String> errors = new HashMap<>();

        for(FieldError error : ex.getBindingResult().getFieldErrors())
        {
            errors.put(error.getField(),error.getDefaultMessage());
        }
        return errors;
    }

//    .getBindingResult() :- Gets all validation errors from the exception
//     .getFieldErrors() :- Return only Field Related errors
//    .FiFieldError :- represent one validation error forb one field


    @ExceptionHandler(TransactionNotFoundException.class)
    public ResponseEntity<Map<String,String>> hnadleTransactionNotFoundException(TransactionNotFoundException ex)
    {
        Map<String,String> errors = new HashMap<>();
        errors.put("error",ex.getMessage());
        return new ResponseEntity<>(errors, HttpStatus.NOT_FOUND);
    }
}

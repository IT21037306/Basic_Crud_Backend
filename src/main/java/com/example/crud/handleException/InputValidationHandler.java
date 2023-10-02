package com.example.crud.handleException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class InputValidationHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String,String> handleInputValidation (MethodArgumentNotValidException ex){
        Map<String,String> errMap = new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach(error ->{
            errMap.put(error.getField(),error.getDefaultMessage());
        });

        return errMap;
    }
}

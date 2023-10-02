package com.example.crud.handleException;

import com.example.crud.customException.ServerException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

//Use this else compiler will throw exception in console not in server
@RestControllerAdvice
public class ServerExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ServerException.class)
    public Map<String,String> handlerNotFoundError(ServerException e){
        Map<String,String> errMap = new HashMap<>();
        errMap.put("errorMessage" ,e.getMessage());
        return errMap;
    }

}

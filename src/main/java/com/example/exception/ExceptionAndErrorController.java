package com.example.exception;

import javax.naming.AuthenticationException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionAndErrorController
{

    /* 
    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleMissingParam(MissingServletRequestParameterException ex)
    {
        return ex.getParameterName();
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleNotFound(ResourceNotFoundException ex)
    {
        return ex.getMessage();
    }
    */

    @ExceptionHandler(AuthenticationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleUnauthorized(AuthenticationException ex)
    {
        return ex.getMessage();
    }

    @ExceptionHandler(DuplicateUsernameFoundException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public String handleDuplicateUsername(DuplicateUsernameFoundException ex)
    {
        return ex.getMessage();
    }

}

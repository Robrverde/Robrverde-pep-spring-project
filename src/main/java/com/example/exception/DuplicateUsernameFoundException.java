package com.example.exception;

public class DuplicateUsernameFoundException extends RuntimeException{

    public DuplicateUsernameFoundException(String message)
    {
        super(message);
    }
}

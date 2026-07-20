package com.example.movieapp.exception;

import org.springframework.http.HttpStatus;

public class InvalidPasswordException extends ServiceException{
    public InvalidPasswordException(String message) {
        super(HttpStatus.UNAUTHORIZED, message);
    }
}

package com.lab1.lab.Model.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class UserNotFoundExceptionn extends RuntimeException {
    public UserNotFoundExceptionn(String userId) {
        super(String.format("User with username %s is not found", userId));
    }
}

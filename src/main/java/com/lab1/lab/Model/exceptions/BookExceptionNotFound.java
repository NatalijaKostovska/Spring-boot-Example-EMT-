package com.lab1.lab.Model.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class BookExceptionNotFound extends RuntimeException{
    public BookExceptionNotFound(Long id){
        super(String.format("Book with id: %d was not found",id));
    }
}

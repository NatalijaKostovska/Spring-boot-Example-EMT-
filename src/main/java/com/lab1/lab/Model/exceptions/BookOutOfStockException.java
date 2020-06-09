package com.lab1.lab.Model.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.PRECONDITION_FAILED)
public class BookOutOfStockException extends RuntimeException {
    public BookOutOfStockException(String name) {
        super(String.format("Book %s is out of stock!", name));
    }
}
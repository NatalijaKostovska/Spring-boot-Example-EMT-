package com.lab1.lab.Model.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.METHOD_NOT_ALLOWED)
public class ShoppingCartIsAlraedyCreatedd extends RuntimeException {
    public ShoppingCartIsAlraedyCreatedd(String username) {
        super(String.format("Shopping cart is already craeted for user: %s", username));
    }
}

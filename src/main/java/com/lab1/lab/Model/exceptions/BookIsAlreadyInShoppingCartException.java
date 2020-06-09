package com.lab1.lab.Model.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.PRECONDITION_FAILED)
public class BookIsAlreadyInShoppingCartException extends RuntimeException {
    public BookIsAlreadyInShoppingCartException(String productName) {
        super(String.format("Book %s is alraedy in active shopping cart", productName));
    }
}

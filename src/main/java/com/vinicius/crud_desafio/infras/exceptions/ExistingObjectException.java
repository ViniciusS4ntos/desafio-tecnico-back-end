package com.vinicius.crud_desafio.infras.exceptions;

public class ExistingObjectException extends RuntimeException {
    public ExistingObjectException(String message) {
        super(message);
    }
}

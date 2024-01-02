package com.jornada.dev.eficiente.desafio1.domains.execptions;

public class AuthorAlreadyExistsException extends RuntimeException {

    public AuthorAlreadyExistsException(String message) {
        super(message);
    }
}

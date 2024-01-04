package com.jornada.dev.eficiente.desafio1.domains.exceptions;

import lombok.Getter;

@Getter
public class NotFoundException extends RuntimeException {
    private final String fieldName;

    public NotFoundException(String fieldName, String message) {
        super(message);
        this.fieldName = fieldName;
    }
}

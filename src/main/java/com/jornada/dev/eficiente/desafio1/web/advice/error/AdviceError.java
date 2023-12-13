package com.jornada.dev.eficiente.desafio1.web.advice.error;

import org.springframework.http.HttpStatus;

public record AdviceError(String field, String message, HttpStatus httpStatus) { }

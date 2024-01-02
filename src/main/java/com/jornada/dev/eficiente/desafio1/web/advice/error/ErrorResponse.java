package com.jornada.dev.eficiente.desafio1.web.advice.error;

import java.util.List;

public record ErrorResponse(List<ErrorDetails> errors) {}

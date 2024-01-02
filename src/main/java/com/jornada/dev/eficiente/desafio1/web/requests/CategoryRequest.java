package com.jornada.dev.eficiente.desafio1.web.requests;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record CategoryRequest(@NotBlank(message = "Name is required") String name) { }

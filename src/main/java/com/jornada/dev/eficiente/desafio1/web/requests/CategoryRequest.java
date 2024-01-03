package com.jornada.dev.eficiente.desafio1.web.requests;

import com.jornada.dev.eficiente.desafio1.domains.annotations.Unique;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

import static com.jornada.dev.eficiente.desafio1.domains.enuns.UniqueType.CATEGORY_NAME;

@Builder
public record CategoryRequest(
        @NotBlank(message = "Name is required")
        @Unique(value = CATEGORY_NAME, message = "Category name must be unique")
        String name) { }

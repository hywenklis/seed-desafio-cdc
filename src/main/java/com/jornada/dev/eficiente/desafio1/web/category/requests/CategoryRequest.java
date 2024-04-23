package com.jornada.dev.eficiente.desafio1.web.category.requests;

import static com.jornada.dev.eficiente.desafio1.domains.enuns.UniqueType.CATEGORY_NAME;

import com.jornada.dev.eficiente.desafio1.domains.configuration.annotations.Unique;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record CategoryRequest(
        @NotBlank(message = "Name is required")
        @Unique(value = CATEGORY_NAME, message = "Category name must be unique")
        String name) {
}

package com.jornada.dev.eficiente.desafio1.web.requests;

import com.jornada.dev.eficiente.desafio1.domains.annotations.Unique;
import com.jornada.dev.eficiente.desafio1.domains.enuns.UniqueType;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record CategoryRequest(
        @NotBlank(message = "Name is required")
        @Unique(value = UniqueType.CATEGORY_NAME, message = "Category name must be unique")
        String name) {
}

package com.jornada.dev.eficiente.desafio1.web.requests;

import static com.jornada.dev.eficiente.desafio1.domains.enuns.UniqueType.STATE_NAME;

import com.jornada.dev.eficiente.desafio1.domains.annotations.Unique;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record StateRequest(@NotBlank(message = "Name is required")
                           @Unique(value = STATE_NAME, message = "State name must be unique")
                           String name,
                           CountryRequest country) {
}

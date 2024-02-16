package com.jornada.dev.eficiente.desafio1.web.requests;

import static com.jornada.dev.eficiente.desafio1.domains.enuns.UniqueType.COUNTRY_NAME;

import com.jornada.dev.eficiente.desafio1.domains.annotations.Unique;
import jakarta.validation.constraints.NotBlank;

public record CountryRequest(
    @NotBlank(message = "Name is required")
    @Unique(value = COUNTRY_NAME, message = "Country name must be unique")
    String name) {
}
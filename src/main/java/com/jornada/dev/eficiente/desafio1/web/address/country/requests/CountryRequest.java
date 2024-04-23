package com.jornada.dev.eficiente.desafio1.web.address.country.requests;

import static com.jornada.dev.eficiente.desafio1.domains.enuns.UniqueType.COUNTRY_NAME;

import com.jornada.dev.eficiente.desafio1.domains.configuration.annotations.Unique;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record CountryRequest(
    @NotBlank(message = "Name is required")
    @Unique(value = COUNTRY_NAME, message = "Country name must be unique")
    String name) {
}
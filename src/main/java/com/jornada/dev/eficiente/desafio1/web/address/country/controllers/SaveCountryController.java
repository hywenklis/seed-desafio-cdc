package com.jornada.dev.eficiente.desafio1.web.address.country.controllers;

import com.jornada.dev.eficiente.desafio1.domains.address.country.dtos.CountryDto;
import com.jornada.dev.eficiente.desafio1.domains.address.country.services.CountryRegistrationService;
import com.jornada.dev.eficiente.desafio1.web.address.country.mappers.CountryMapperRequest;
import com.jornada.dev.eficiente.desafio1.web.address.country.mappers.CountryMapperResponse;
import com.jornada.dev.eficiente.desafio1.web.address.country.requests.CountryRequest;
import com.jornada.dev.eficiente.desafio1.web.address.country.responses.CountryResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/countries")
@RequiredArgsConstructor
@Tag(name = "Country", description = "Endpoint related to countryName registration")
public class SaveCountryController {

    private final CountryRegistrationService service;
    private final CountryMapperRequest mapperRequest;
    private final CountryMapperResponse mapperResponse;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(
        summary = "Register countryName",
        description = "Register countries with their appropriate information")
    public CountryResponse registration(@RequestBody @Valid CountryRequest countryRequest) {
        CountryDto savedCountry = service.save(mapperRequest.mapToDto(countryRequest));
        return mapperResponse.mapToDto(savedCountry);
    }
}

package com.jornada.dev.eficiente.desafio1.web.controllers.address;

import com.jornada.dev.eficiente.desafio1.domains.dtos.CountryDto;
import com.jornada.dev.eficiente.desafio1.domains.services.CountryRegistrationService;
import com.jornada.dev.eficiente.desafio1.web.mappers.country.CountryMapperRequest;
import com.jornada.dev.eficiente.desafio1.web.mappers.country.CountryMapperResponse;
import com.jornada.dev.eficiente.desafio1.web.requests.CountryRequest;
import com.jornada.dev.eficiente.desafio1.web.responses.CountryResponse;
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

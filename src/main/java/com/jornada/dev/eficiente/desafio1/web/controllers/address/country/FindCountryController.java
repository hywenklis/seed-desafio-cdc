package com.jornada.dev.eficiente.desafio1.web.controllers.address.country;

import com.jornada.dev.eficiente.desafio1.domains.services.CountryFindService;
import com.jornada.dev.eficiente.desafio1.web.mappers.country.CountryMapperResponse;
import com.jornada.dev.eficiente.desafio1.web.responses.CountryResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/countries")
@RequiredArgsConstructor
@Tag(name = "Country", description = "Endpoint related to country get")
public class FindCountryController {

    private final CountryFindService service;
    private final CountryMapperResponse mapperResponse;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(
        summary = "Find country",
        description = "Find country by name"
    )
    public Optional<CountryResponse> findCountryByName(@RequestParam String name) {
        return service.findCountryByName(name).map(mapperResponse::mapToDto);
    }
}

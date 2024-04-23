package com.jornada.dev.eficiente.desafio1.web.address.state.controllers;

import com.jornada.dev.eficiente.desafio1.domains.address.state.dtos.StateDto;
import com.jornada.dev.eficiente.desafio1.domains.address.state.services.StateRegistrationService;
import com.jornada.dev.eficiente.desafio1.web.address.state.mappers.StateMapperRequest;
import com.jornada.dev.eficiente.desafio1.web.address.state.mappers.StateMapperResponse;
import com.jornada.dev.eficiente.desafio1.web.address.state.requests.StateRequest;
import com.jornada.dev.eficiente.desafio1.web.address.state.responses.StateResponse;
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
@RequestMapping("/v1/states")
@RequiredArgsConstructor
@Tag(name = "State", description = "Endpoint related to stateName registration")
public class SaveStateController {

    private final StateRegistrationService service;
    private final StateMapperRequest mapperRequest;
    private final StateMapperResponse mapperResponse;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(
        summary = "Register stateName",
        description = "Register states with their appropriate information")
    public StateResponse registration(@RequestBody @Valid StateRequest stateRequest) {
        StateDto savedState = service.save(mapperRequest.mapToDto(stateRequest));
        return mapperResponse.mapToDto(savedState);
    }
}

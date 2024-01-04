package com.jornada.dev.eficiente.desafio1.web.controllers;

import com.jornada.dev.eficiente.desafio1.domains.dtos.AuthorDto;
import com.jornada.dev.eficiente.desafio1.domains.services.AuthorRegistrationService;
import com.jornada.dev.eficiente.desafio1.web.mappers.author.AuthorMapperRequest;
import com.jornada.dev.eficiente.desafio1.web.mappers.author.AuthorMapperResponse;
import com.jornada.dev.eficiente.desafio1.web.requests.AuthorRequest;
import com.jornada.dev.eficiente.desafio1.web.responses.AuthorResponse;
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
@RequestMapping("/v1/authors")
@RequiredArgsConstructor
@Tag(name = "Author", description = "Endpoint related to authorEmail registration")
public class AuthorController {

    private final AuthorRegistrationService service;
    private final AuthorMapperRequest mapperRequest;
    private final AuthorMapperResponse mapperResponse;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(
        summary = "Register authorEmail",
        description = "Register authors with their appropriate information")
    public AuthorResponse registration(@RequestBody @Valid AuthorRequest authorRequest) {
        AuthorDto savedAuthor = service.save(mapperRequest.mapToDto(authorRequest));
        return mapperResponse.mapToDto(savedAuthor);
    }
}

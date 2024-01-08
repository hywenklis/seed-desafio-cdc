package com.jornada.dev.eficiente.desafio1.web.controllers.book;

import com.jornada.dev.eficiente.desafio1.domains.dtos.BookDto;
import com.jornada.dev.eficiente.desafio1.domains.services.BookRegistrationService;
import com.jornada.dev.eficiente.desafio1.web.mappers.book.BookMapperRequest;
import com.jornada.dev.eficiente.desafio1.web.mappers.book.BookMapperResponse;
import com.jornada.dev.eficiente.desafio1.web.requests.BookRequest;
import com.jornada.dev.eficiente.desafio1.web.responses.BookResponse;
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
@RequestMapping("/v1/books")
@RequiredArgsConstructor
@Tag(name = "Book", description = "Endpoint related to book registration")
public class SaveBookController {

    private final BookRegistrationService service;
    private final BookMapperRequest mapperRequest;
    private final BookMapperResponse mapperResponse;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(
        summary = "Register book",
        description = "Register books with their appropriate information")
    public BookResponse registration(@RequestBody @Valid BookRequest bookRequest) {
        BookDto savedBook = service.save(mapperRequest.mapToDto(bookRequest));
        return mapperResponse.mapToDto(savedBook);
    }
}

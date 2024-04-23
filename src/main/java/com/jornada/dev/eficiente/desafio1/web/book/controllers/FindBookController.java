package com.jornada.dev.eficiente.desafio1.web.book.controllers;

import com.jornada.dev.eficiente.desafio1.domains.book.services.BookFindService;
import com.jornada.dev.eficiente.desafio1.web.book.mappers.BookMapperResponse;
import com.jornada.dev.eficiente.desafio1.web.book.responses.BookCompactResponse;
import com.jornada.dev.eficiente.desafio1.web.book.responses.BookResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/books")
@RequiredArgsConstructor
@Tag(name = "Book", description = "Endpoint related to compact book get")
public class FindBookController {

    private final BookFindService service;
    private final BookMapperResponse mapperResponse;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(
        summary = "Find all compact book",
        description = "Find all compact books existing")
    public Optional<List<BookCompactResponse>> findAllCompactBook() {
        return service.findAll().map(mapperResponse::mapToDto);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(
        summary = "Find book details",
        description = "Find book details existing"
    )
    public Optional<BookResponse> findBookDetails(@PathVariable UUID id) {
        return service.findBookDetails(id).map(mapperResponse::mapToDto);
    }
}

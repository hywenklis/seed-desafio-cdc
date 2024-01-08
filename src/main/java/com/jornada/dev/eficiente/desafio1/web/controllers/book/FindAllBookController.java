package com.jornada.dev.eficiente.desafio1.web.controllers.book;

import com.jornada.dev.eficiente.desafio1.domains.services.BookFindService;
import com.jornada.dev.eficiente.desafio1.web.mappers.book.BookMapperResponse;
import com.jornada.dev.eficiente.desafio1.web.responses.BookCompactResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/books")
@RequiredArgsConstructor
@Tag(name = "Book", description = "Endpoint related to book get")
public class FindAllBookController {

    private final BookFindService service;
    private final BookMapperResponse mapperResponse;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(
        summary = "find all book",
        description = "find all books existing")
    public Optional<List<BookCompactResponse>> findAll() {
        return service.findAll().map(mapperResponse::mapToDto);
    }
}

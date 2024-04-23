package com.jornada.dev.eficiente.desafio1.domains.book.services;

import com.jornada.dev.eficiente.desafio1.domains.book.dtos.BookDto;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BookFindService {
    Optional<BookDto> findBookByTitle(String title);

    Optional<BookDto> findBookByIsbn(String isbn);

    Optional<List<BookDto>> findAll();

    Optional<BookDto> findBookDetails(UUID id);
}

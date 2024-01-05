package com.jornada.dev.eficiente.desafio1.domains.services;

import com.jornada.dev.eficiente.desafio1.domains.dtos.BookDto;
import java.util.Optional;

public interface BookFindService {
    Optional<BookDto> findBookByTitle(String title);

    Optional<BookDto> findBookByIsbn(String isbn);
}

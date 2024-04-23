package com.jornada.dev.eficiente.desafio1.domains.book.services;

import com.jornada.dev.eficiente.desafio1.domains.book.dtos.BookDto;

public interface BookRegistrationService {
    BookDto save(BookDto bookDto);
}

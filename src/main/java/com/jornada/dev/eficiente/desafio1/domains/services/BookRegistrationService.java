package com.jornada.dev.eficiente.desafio1.domains.services;

import com.jornada.dev.eficiente.desafio1.domains.dtos.BookDto;

public interface BookRegistrationService {
    BookDto save(BookDto bookDto);
}

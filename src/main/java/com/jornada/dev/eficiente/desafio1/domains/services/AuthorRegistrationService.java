package com.jornada.dev.eficiente.desafio1.domains.services;

import com.jornada.dev.eficiente.desafio1.domains.dtos.AuthorDto;

public interface AuthorRegistrationService {
    AuthorDto save(AuthorDto authorDto);
}

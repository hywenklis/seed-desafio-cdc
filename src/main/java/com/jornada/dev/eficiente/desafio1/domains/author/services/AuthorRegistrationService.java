package com.jornada.dev.eficiente.desafio1.domains.author.services;

import com.jornada.dev.eficiente.desafio1.domains.author.dtos.AuthorDto;

public interface AuthorRegistrationService {
    AuthorDto save(AuthorDto authorDto);
}

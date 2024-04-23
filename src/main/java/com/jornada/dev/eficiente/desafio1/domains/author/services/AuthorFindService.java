package com.jornada.dev.eficiente.desafio1.domains.author.services;

import com.jornada.dev.eficiente.desafio1.domains.author.dtos.AuthorDto;
import java.util.Optional;

public interface AuthorFindService {
    Optional<AuthorDto> findAuthorByEmail(String email);
}

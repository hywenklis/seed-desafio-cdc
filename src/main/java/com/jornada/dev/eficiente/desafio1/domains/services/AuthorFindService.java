package com.jornada.dev.eficiente.desafio1.domains.services;

import com.jornada.dev.eficiente.desafio1.domains.dtos.AuthorDto;
import java.util.Optional;

public interface AuthorFindService {
    Optional<AuthorDto> findAuthorByEmail(String email);
}

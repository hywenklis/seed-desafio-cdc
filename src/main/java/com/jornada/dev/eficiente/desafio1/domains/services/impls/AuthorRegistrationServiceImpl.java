package com.jornada.dev.eficiente.desafio1.domains.services.impls;

import com.jornada.dev.eficiente.desafio1.domains.dtos.AuthorDto;
import com.jornada.dev.eficiente.desafio1.domains.entities.AuthorEntity;
import com.jornada.dev.eficiente.desafio1.domains.mappers.AuthorDomainMapper;
import com.jornada.dev.eficiente.desafio1.domains.repositories.AuthorRepository;
import com.jornada.dev.eficiente.desafio1.domains.services.AuthorRegistrationService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthorRegistrationServiceImpl
        implements AuthorRegistrationService {

    private final AuthorRepository authorRepository;
    private final AuthorDomainMapper mapper;

    @Override
    @Transactional
    public AuthorDto save(AuthorDto authorDto) {
        AuthorEntity savedAuthor = authorRepository.save(mapper.mapToEntity(authorDto));
        return mapper.mapToDto(savedAuthor);
    }
}

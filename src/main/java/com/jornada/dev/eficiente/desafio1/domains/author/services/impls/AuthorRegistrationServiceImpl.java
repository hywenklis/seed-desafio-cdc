package com.jornada.dev.eficiente.desafio1.domains.author.services.impls;

import com.jornada.dev.eficiente.desafio1.domains.author.dtos.AuthorDto;
import com.jornada.dev.eficiente.desafio1.domains.author.entities.AuthorEntity;
import com.jornada.dev.eficiente.desafio1.domains.author.mappers.AuthorDomainMapper;
import com.jornada.dev.eficiente.desafio1.domains.author.repositories.AuthorRepository;
import com.jornada.dev.eficiente.desafio1.domains.author.services.AuthorRegistrationService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthorRegistrationServiceImpl implements AuthorRegistrationService {

    private final AuthorRepository authorRepository;
    private final AuthorDomainMapper mapper;

    @Override
    @Transactional
    public AuthorDto save(AuthorDto authorDto) {
        AuthorEntity savedAuthor = authorRepository.save(mapper.mapToEntity(authorDto));
        return mapper.mapToDto(savedAuthor);
    }
}

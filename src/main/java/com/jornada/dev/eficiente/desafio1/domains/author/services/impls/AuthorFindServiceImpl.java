package com.jornada.dev.eficiente.desafio1.domains.author.services.impls;

import com.jornada.dev.eficiente.desafio1.domains.author.dtos.AuthorDto;
import com.jornada.dev.eficiente.desafio1.domains.author.mappers.AuthorDomainMapper;
import com.jornada.dev.eficiente.desafio1.domains.author.repositories.AuthorRepository;
import com.jornada.dev.eficiente.desafio1.domains.author.services.AuthorFindService;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthorFindServiceImpl implements AuthorFindService {

    private final AuthorRepository authorRepository;
    private final AuthorDomainMapper authorMapper;

    @Override
    public Optional<AuthorDto> findAuthorByEmail(String email) {
        return authorRepository.findByEmail(email).map(authorMapper::mapToDto);
    }
}

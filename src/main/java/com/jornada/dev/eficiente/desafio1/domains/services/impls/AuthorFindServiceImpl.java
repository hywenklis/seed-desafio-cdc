package com.jornada.dev.eficiente.desafio1.domains.services.impls;

import com.jornada.dev.eficiente.desafio1.domains.dtos.AuthorDto;
import com.jornada.dev.eficiente.desafio1.domains.mappers.AuthorDomainMapper;
import com.jornada.dev.eficiente.desafio1.domains.repositories.AuthorRepository;
import com.jornada.dev.eficiente.desafio1.domains.services.AuthorFindService;
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

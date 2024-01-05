package com.jornada.dev.eficiente.desafio1.components;

import com.jornada.dev.eficiente.desafio1.builders.AuthorBuilder;
import com.jornada.dev.eficiente.desafio1.domains.entities.AuthorEntity;
import com.jornada.dev.eficiente.desafio1.domains.repositories.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AuthorComponent {

    @Autowired
    private AuthorRepository authorRepository;

    public AuthorEntity createAuthor(final String name,
                                     final String email,
                                     final String description) {
        return authorRepository.save(AuthorBuilder.createAuthorEntity(name, email, description));
    }
}

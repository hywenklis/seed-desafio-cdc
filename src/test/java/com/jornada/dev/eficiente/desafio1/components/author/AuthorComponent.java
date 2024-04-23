package com.jornada.dev.eficiente.desafio1.components.author;

import com.jornada.dev.eficiente.desafio1.builders.author.AuthorBuilder;
import com.jornada.dev.eficiente.desafio1.domains.author.entities.AuthorEntity;
import com.jornada.dev.eficiente.desafio1.domains.author.repositories.AuthorRepository;
import org.springframework.stereotype.Component;

@Component
public class AuthorComponent {

    private final AuthorRepository authorRepository;

    public AuthorComponent(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public AuthorEntity createAuthor(final String name,
                                     final String email,
                                     final String description) {
        return authorRepository.save(AuthorBuilder.createAuthorEntity(name, email, description));
    }
}

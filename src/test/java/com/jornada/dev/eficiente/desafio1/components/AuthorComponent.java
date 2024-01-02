package com.jornada.dev.eficiente.desafio1.components;

import com.jornada.dev.eficiente.desafio1.domains.entities.AuthorEntity;
import com.jornada.dev.eficiente.desafio1.domains.repositories.AuthorRepository;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AuthorComponent {

    @Autowired
    private AuthorRepository authorRepository;

    public void createAuthor(final String name,
                             final String email,
                             final String description,
                             final LocalDateTime createDate,
                             final LocalDateTime updateDate) {
        authorRepository.save(AuthorEntity.builder()
                .id(UUID.randomUUID())
                .name(name)
                .email(email)
                .description(description)
                .createDate(createDate)
                .updateDate(updateDate)
                .build());
    }
}

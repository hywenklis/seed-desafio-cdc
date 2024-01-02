package com.jornada.dev.eficiente.desafio1.builders;

import com.jornada.dev.eficiente.desafio1.domains.dtos.AuthorDto;
import com.jornada.dev.eficiente.desafio1.domains.entities.AuthorEntity;
import com.jornada.dev.eficiente.desafio1.web.requests.AuthorRequest;
import java.time.LocalDateTime;
import java.util.UUID;

public class AuthorBuilder {

  public static AuthorRequest createAuthorRequest(String name, String email,
                                                  String description) {
    return AuthorRequest.builder()
        .name(name)
        .email(email)
        .description(description)
        .build();
  }

  public static AuthorDto createAuthorDto(String name, String email,
                                          String description) {
    return AuthorDto.builder()
        .name(name)
        .email(email)
        .description(description)
        .createDate(LocalDateTime.now())
        .updateDate(LocalDateTime.now())
        .build();
  }

  public static AuthorEntity createAuthorEntity(String name, String email,
                                                String description) {
    return AuthorEntity.builder()
        .id(UUID.randomUUID())
        .name(name)
        .email(email)
        .description(description)
        .createDate(LocalDateTime.now())
        .updateDate(LocalDateTime.now())
        .build();
  }
}

package com.jornada.dev.eficiente.desafio1.web.author.mappers;

import com.jornada.dev.eficiente.desafio1.domains.author.dtos.AuthorDto;
import com.jornada.dev.eficiente.desafio1.web.author.responses.AuthorResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AuthorMapperResponse {

    AuthorResponse mapToDto(AuthorDto authorDto);
}

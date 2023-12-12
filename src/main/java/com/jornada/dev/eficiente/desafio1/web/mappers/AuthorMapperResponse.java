package com.jornada.dev.eficiente.desafio1.web.mappers;

import com.jornada.dev.eficiente.desafio1.domains.dtos.AuthorDto;
import com.jornada.dev.eficiente.desafio1.domains.entities.AuthorEntity;
import com.jornada.dev.eficiente.desafio1.web.responses.AuthorResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AuthorMapperResponse {

  AuthorResponse mapToDto(AuthorDto authorDto);

}

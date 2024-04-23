package com.jornada.dev.eficiente.desafio1.domains.author.mappers;

import com.jornada.dev.eficiente.desafio1.domains.author.dtos.AuthorDto;
import com.jornada.dev.eficiente.desafio1.domains.author.entities.AuthorEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AuthorDomainMapper {

    AuthorDto mapToDto(AuthorEntity authorEntity);

    AuthorEntity mapToEntity(AuthorDto authorDto);
}

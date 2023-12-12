package com.jornada.dev.eficiente.desafio1.domains.mappers;

import com.jornada.dev.eficiente.desafio1.domains.dtos.AuthorDto;
import com.jornada.dev.eficiente.desafio1.domains.entities.AuthorEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AuthorDomainMapper {

  AuthorDto mapToDto(AuthorEntity authorEntity);

  @Mapping(target = "id", ignore = true)
  AuthorEntity mapToEntity(AuthorDto authorDto);

}

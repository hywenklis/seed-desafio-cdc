package com.jornada.dev.eficiente.desafio1.web.author.mappers;

import com.jornada.dev.eficiente.desafio1.domains.author.dtos.AuthorDto;
import com.jornada.dev.eficiente.desafio1.web.author.requests.AuthorRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AuthorMapperRequest {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "updateDate", ignore = true)
    @Mapping(target = "createDate", ignore = true)
    AuthorDto mapToDto(AuthorRequest authorRequest);
}

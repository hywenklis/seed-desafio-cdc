package com.jornada.dev.eficiente.desafio1.domains.mappers;

import com.jornada.dev.eficiente.desafio1.domains.dtos.BookDto;
import com.jornada.dev.eficiente.desafio1.domains.entities.BookEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BookDomainMapper {

    @Mapping(target = "bookDetails", ignore = true)
    BookDto mapToDto(BookEntity bookEntity);

    @Mapping(target = "id", ignore = true)
    BookEntity mapToEntity(BookDto bookDto);
}

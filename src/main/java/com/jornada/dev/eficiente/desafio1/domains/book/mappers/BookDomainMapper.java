package com.jornada.dev.eficiente.desafio1.domains.book.mappers;

import com.jornada.dev.eficiente.desafio1.domains.book.dtos.BookDto;
import com.jornada.dev.eficiente.desafio1.domains.book.entities.BookEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BookDomainMapper {

    @Mapping(target = "bookDetails", ignore = true)
    BookDto mapToDto(BookEntity bookEntity);

    @Mapping(target = "id", ignore = true)
    BookEntity mapToEntity(BookDto bookDto);
}

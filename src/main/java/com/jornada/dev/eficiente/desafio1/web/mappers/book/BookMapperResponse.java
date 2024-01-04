package com.jornada.dev.eficiente.desafio1.web.mappers.book;

import com.jornada.dev.eficiente.desafio1.domains.dtos.BookDto;
import com.jornada.dev.eficiente.desafio1.web.responses.BookResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BookMapperResponse {

    BookResponse mapToDto(BookDto bookDto);
}

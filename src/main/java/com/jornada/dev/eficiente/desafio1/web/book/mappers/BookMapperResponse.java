package com.jornada.dev.eficiente.desafio1.web.book.mappers;

import com.jornada.dev.eficiente.desafio1.domains.book.dtos.BookDto;
import com.jornada.dev.eficiente.desafio1.web.book.responses.BookCompactResponse;
import com.jornada.dev.eficiente.desafio1.web.book.responses.BookResponse;
import java.util.List;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BookMapperResponse {

    BookResponse mapToDto(BookDto bookDto);

    List<BookCompactResponse> mapToDto(List<BookDto> bookDto);
}

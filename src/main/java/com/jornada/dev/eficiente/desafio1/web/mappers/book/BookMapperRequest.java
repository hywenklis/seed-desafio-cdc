package com.jornada.dev.eficiente.desafio1.web.mappers.book;

import com.jornada.dev.eficiente.desafio1.domains.dtos.BookDto;
import com.jornada.dev.eficiente.desafio1.web.requests.BookRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BookMapperRequest {

    @Mapping(target = "bookDetails", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "updateDate", ignore = true)
    @Mapping(target = "createDate", ignore = true)
    @Mapping(target = "author.email", expression = "java(bookRequest.authorEmail())")
    @Mapping(target = "category.name", expression = "java(bookRequest.categoryName())")
    BookDto mapToDto(BookRequest bookRequest);
}


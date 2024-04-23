package com.jornada.dev.eficiente.desafio1.web.category.mappers;

import com.jornada.dev.eficiente.desafio1.domains.category.dtos.CategoryDto;
import com.jornada.dev.eficiente.desafio1.web.category.responses.CategoryResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapperResponse {

    CategoryResponse mapToDto(CategoryDto categoryDto);
}

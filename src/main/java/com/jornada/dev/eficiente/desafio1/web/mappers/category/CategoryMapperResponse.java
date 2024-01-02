package com.jornada.dev.eficiente.desafio1.web.mappers.category;

import com.jornada.dev.eficiente.desafio1.domains.dtos.CategoryDto;
import com.jornada.dev.eficiente.desafio1.web.responses.CategoryResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapperResponse {

  CategoryResponse mapToDto(CategoryDto categoryDto);
}

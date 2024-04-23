package com.jornada.dev.eficiente.desafio1.domains.category.mappers;

import com.jornada.dev.eficiente.desafio1.domains.category.dtos.CategoryDto;
import com.jornada.dev.eficiente.desafio1.domains.category.entities.CategoryEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryDomainMapper {

    CategoryDto mapToDto(CategoryEntity categoryEntity);

    CategoryEntity mapToEntity(CategoryDto categoryDto);
}

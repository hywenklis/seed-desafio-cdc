package com.jornada.dev.eficiente.desafio1.domains.mappers;

import com.jornada.dev.eficiente.desafio1.domains.dtos.CategoryDto;
import com.jornada.dev.eficiente.desafio1.domains.entities.CategoryEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CategoryDomainMapper {

  CategoryDto mapToDto(CategoryEntity categoryEntity);

  @Mapping(target = "id", ignore = true)
  CategoryEntity mapToEntity(CategoryDto categoryDto);
}

package com.jornada.dev.eficiente.desafio1.web.category.mappers;

import com.jornada.dev.eficiente.desafio1.domains.category.dtos.CategoryDto;
import com.jornada.dev.eficiente.desafio1.web.category.requests.CategoryRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CategoryMapperRequest {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "updateDate", ignore = true)
    @Mapping(target = "createDate", ignore = true)
    CategoryDto mapToDto(CategoryRequest categoryRequest);
}

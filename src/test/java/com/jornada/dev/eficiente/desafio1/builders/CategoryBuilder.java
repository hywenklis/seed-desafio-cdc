package com.jornada.dev.eficiente.desafio1.builders;

import com.jornada.dev.eficiente.desafio1.domains.dtos.CategoryDto;
import com.jornada.dev.eficiente.desafio1.domains.entities.CategoryEntity;
import com.jornada.dev.eficiente.desafio1.web.requests.CategoryRequest;
import java.time.LocalDateTime;
import java.util.UUID;

public class CategoryBuilder {

  public static CategoryRequest createCategoryRequest(String name) {
    return CategoryRequest.builder().name(name).build();
  }

  public static CategoryDto createCategoryDto(String name) {
    return CategoryDto.builder()
        .name(name)
        .createDate(LocalDateTime.now())
        .updateDate(LocalDateTime.now())
        .build();
  }

  public static CategoryEntity createCategoryEntity(String name) {
    return CategoryEntity.builder()
        .id(UUID.randomUUID())
        .name(name)
        .createDate(LocalDateTime.now())
        .updateDate(LocalDateTime.now())
        .build();
  }
}

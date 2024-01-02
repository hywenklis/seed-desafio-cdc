package com.jornada.dev.eficiente.desafio1.web.controllers;

import com.jornada.dev.eficiente.desafio1.domains.dtos.CategoryDto;
import com.jornada.dev.eficiente.desafio1.domains.services.CategoryRegistrationService;
import com.jornada.dev.eficiente.desafio1.web.mappers.category.CategoryMapperRequest;
import com.jornada.dev.eficiente.desafio1.web.mappers.category.CategoryMapperResponse;
import com.jornada.dev.eficiente.desafio1.web.requests.CategoryRequest;
import com.jornada.dev.eficiente.desafio1.web.responses.CategoryResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/categories")
@RequiredArgsConstructor
@Tag(name = "Category",
     description = "Endpoint related to category registration")
public class CategoryController {

  private final CategoryRegistrationService service;
  private final CategoryMapperRequest mapperRequest;
  private final CategoryMapperResponse mapperResponse;

  @PostMapping("/register")
  @ResponseStatus(HttpStatus.CREATED)
  @Operation(
      summary = "Register category",
      description = "Register categories with their appropriate information")
  public CategoryResponse
  registration(@RequestBody @Valid CategoryRequest categoryRequest) {
    CategoryDto savedCategory =
        service.save(mapperRequest.mapToDto(categoryRequest));
    return mapperResponse.mapToDto(savedCategory);
  }
}

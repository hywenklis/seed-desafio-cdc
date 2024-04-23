package com.jornada.dev.eficiente.desafio1.web.category.controllers;

import com.jornada.dev.eficiente.desafio1.domains.category.dtos.CategoryDto;
import com.jornada.dev.eficiente.desafio1.domains.category.services.CategoryRegistrationService;
import com.jornada.dev.eficiente.desafio1.web.category.mappers.CategoryMapperRequest;
import com.jornada.dev.eficiente.desafio1.web.category.mappers.CategoryMapperResponse;
import com.jornada.dev.eficiente.desafio1.web.category.requests.CategoryRequest;
import com.jornada.dev.eficiente.desafio1.web.category.responses.CategoryResponse;
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
@Tag(name = "Category", description = "Endpoint related to categoryName registration")
public class SaveCategoryController {

    private final CategoryRegistrationService service;
    private final CategoryMapperRequest mapperRequest;
    private final CategoryMapperResponse mapperResponse;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(
        summary = "Register categoryName",
        description = "Register categories with their appropriate information")
    public CategoryResponse registration(@RequestBody @Valid CategoryRequest categoryRequest) {
        CategoryDto savedCategory = service.save(mapperRequest.mapToDto(categoryRequest));
        return mapperResponse.mapToDto(savedCategory);
    }
}

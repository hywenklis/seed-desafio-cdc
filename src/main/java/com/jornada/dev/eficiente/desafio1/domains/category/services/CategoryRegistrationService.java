package com.jornada.dev.eficiente.desafio1.domains.category.services;

import com.jornada.dev.eficiente.desafio1.domains.category.dtos.CategoryDto;

public interface CategoryRegistrationService {
    CategoryDto save(CategoryDto categoryDto);
}

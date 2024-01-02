package com.jornada.dev.eficiente.desafio1.domains.services;

import com.jornada.dev.eficiente.desafio1.domains.dtos.CategoryDto;

public interface CategoryRegistrationService {
  CategoryDto save(CategoryDto categoryDto);
}

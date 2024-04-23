package com.jornada.dev.eficiente.desafio1.domains.category.services;

import com.jornada.dev.eficiente.desafio1.domains.category.dtos.CategoryDto;
import java.util.Optional;

public interface CategoryFindService {
    Optional<CategoryDto> findCategoryByName(String name);
}

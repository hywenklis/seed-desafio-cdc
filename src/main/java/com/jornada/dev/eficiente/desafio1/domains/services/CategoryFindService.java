package com.jornada.dev.eficiente.desafio1.domains.services;

import com.jornada.dev.eficiente.desafio1.domains.dtos.CategoryDto;
import java.util.Optional;

public interface CategoryFindService {
    Optional<CategoryDto> findCategoryByName(String name);
}

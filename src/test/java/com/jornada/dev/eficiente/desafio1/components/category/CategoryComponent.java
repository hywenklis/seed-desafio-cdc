package com.jornada.dev.eficiente.desafio1.components.category;

import static com.jornada.dev.eficiente.desafio1.builders.category.CategoryBuilder.createCategoryEntity;

import com.jornada.dev.eficiente.desafio1.domains.category.entities.CategoryEntity;
import com.jornada.dev.eficiente.desafio1.domains.category.repositories.CategoryRepository;
import org.springframework.stereotype.Component;

@Component
public class CategoryComponent {

    private final CategoryRepository categoryRepository;

    public CategoryComponent(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public CategoryEntity createCategory(final String name) {
        return categoryRepository.save(createCategoryEntity(name));
    }
}

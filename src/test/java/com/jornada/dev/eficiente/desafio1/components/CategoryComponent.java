package com.jornada.dev.eficiente.desafio1.components;

import static com.jornada.dev.eficiente.desafio1.builders.CategoryBuilder.createCategoryEntity;

import com.jornada.dev.eficiente.desafio1.domains.entities.CategoryEntity;
import com.jornada.dev.eficiente.desafio1.domains.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

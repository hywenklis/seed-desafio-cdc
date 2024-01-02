package com.jornada.dev.eficiente.desafio1.components;

import com.jornada.dev.eficiente.desafio1.domains.entities.CategoryEntity;
import com.jornada.dev.eficiente.desafio1.domains.repositories.CategoryRepository;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CategoryComponent {

    @Autowired
    private CategoryRepository categoryRepository;

    public void createCategory(final String name,
                               final LocalDateTime createDate,
                               final LocalDateTime updateDate) {
        categoryRepository.save(CategoryEntity.builder()
                .id(UUID.randomUUID())
                .name(name)
                .createDate(createDate)
                .updateDate(updateDate)
                .build());
    }
}

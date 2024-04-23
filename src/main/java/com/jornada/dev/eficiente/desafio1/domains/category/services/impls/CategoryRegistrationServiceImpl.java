package com.jornada.dev.eficiente.desafio1.domains.category.services.impls;

import com.jornada.dev.eficiente.desafio1.domains.category.dtos.CategoryDto;
import com.jornada.dev.eficiente.desafio1.domains.category.entities.CategoryEntity;
import com.jornada.dev.eficiente.desafio1.domains.category.mappers.CategoryDomainMapper;
import com.jornada.dev.eficiente.desafio1.domains.category.repositories.CategoryRepository;
import com.jornada.dev.eficiente.desafio1.domains.category.services.CategoryRegistrationService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryRegistrationServiceImpl implements CategoryRegistrationService {

    private final CategoryRepository categoryRepository;
    private final CategoryDomainMapper mapper;

    @Override
    @Transactional
    public CategoryDto save(CategoryDto categoryDto) {
        CategoryEntity savedCategory = categoryRepository.save(mapper.mapToEntity(categoryDto));
        return mapper.mapToDto(savedCategory);
    }
}

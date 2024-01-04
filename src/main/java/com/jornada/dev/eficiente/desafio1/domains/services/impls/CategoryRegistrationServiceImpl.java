package com.jornada.dev.eficiente.desafio1.domains.services.impls;

import com.jornada.dev.eficiente.desafio1.domains.dtos.CategoryDto;
import com.jornada.dev.eficiente.desafio1.domains.entities.CategoryEntity;
import com.jornada.dev.eficiente.desafio1.domains.mappers.CategoryDomainMapper;
import com.jornada.dev.eficiente.desafio1.domains.repositories.CategoryRepository;
import com.jornada.dev.eficiente.desafio1.domains.services.CategoryRegistrationService;
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

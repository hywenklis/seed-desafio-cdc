package com.jornada.dev.eficiente.desafio1.domains.services.impls;

import com.jornada.dev.eficiente.desafio1.domains.dtos.CategoryDto;
import com.jornada.dev.eficiente.desafio1.domains.mappers.CategoryDomainMapper;
import com.jornada.dev.eficiente.desafio1.domains.repositories.CategoryRepository;
import com.jornada.dev.eficiente.desafio1.domains.services.CategoryFindService;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryFindServiceImpls implements CategoryFindService {

    private final CategoryRepository categoryRepository;
    private final CategoryDomainMapper categoryMapper;

    @Override
    public Optional<CategoryDto> findCategoryByName(String name) {
        return categoryRepository.findByName(name).map(categoryMapper::mapToDto);
    }
}

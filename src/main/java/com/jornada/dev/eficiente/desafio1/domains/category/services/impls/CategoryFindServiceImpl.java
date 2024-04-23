package com.jornada.dev.eficiente.desafio1.domains.category.services.impls;

import com.jornada.dev.eficiente.desafio1.domains.category.dtos.CategoryDto;
import com.jornada.dev.eficiente.desafio1.domains.category.mappers.CategoryDomainMapper;
import com.jornada.dev.eficiente.desafio1.domains.category.repositories.CategoryRepository;
import com.jornada.dev.eficiente.desafio1.domains.category.services.CategoryFindService;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryFindServiceImpl implements CategoryFindService {

    private final CategoryRepository categoryRepository;
    private final CategoryDomainMapper categoryMapper;

    @Override
    public Optional<CategoryDto> findCategoryByName(String name) {
        return categoryRepository.findByName(name).map(categoryMapper::mapToDto);
    }
}

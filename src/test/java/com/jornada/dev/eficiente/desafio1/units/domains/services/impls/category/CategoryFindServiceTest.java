package com.jornada.dev.eficiente.desafio1.units.domains.services.impls.category;

import static com.jornada.dev.eficiente.desafio1.builders.CategoryBuilder.createCategoryDto;
import static com.jornada.dev.eficiente.desafio1.builders.CategoryBuilder.createCategoryEntity;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.jornada.dev.eficiente.desafio1.domains.dtos.CategoryDto;
import com.jornada.dev.eficiente.desafio1.domains.mappers.CategoryDomainMapper;
import com.jornada.dev.eficiente.desafio1.domains.repositories.CategoryRepository;
import com.jornada.dev.eficiente.desafio1.domains.services.impls.CategoryFindServiceImpl;
import com.jornada.dev.eficiente.desafio1.units.UnitTestAbstract;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

class CategoryFindServiceTest extends UnitTestAbstract {

    @Mock
    private CategoryRepository categoryRepository;

    @Mock
    private CategoryDomainMapper categoryMapper;

    @InjectMocks
    private CategoryFindServiceImpl categoryFindServiceImpls;

    @Test
    @DisplayName("Should return category when found in the database")
    void shouldReturnCategoryWhenFoundInDatabase() {
        // Given
        var categoryName = randomAlphabetic(10);
        var categoryDto = createCategoryDto(categoryName);
        var categoryEntity = createCategoryEntity(categoryName);

        when(categoryMapper.mapToDto(categoryEntity)).thenReturn(categoryDto);
        when(categoryRepository.findByName(categoryName)).thenReturn(Optional.of(categoryEntity));

        // When
        Optional<CategoryDto> category = categoryFindServiceImpls.findCategoryByName(categoryName);

        // Then
        assertThat(category).isPresent();
        assertThat(category.get().id()).isEqualTo(categoryDto.id());
        assertThat(category.get().name()).isEqualTo(categoryDto.name());
        assertThat(category.get().createDate()).isEqualTo(categoryDto.createDate());
        assertThat(category.get().updateDate()).isEqualTo(categoryDto.updateDate());

        // Verify
        verify(categoryRepository, times(1)).findByName(categoryName);
    }

    @Test
    @DisplayName("Should return empty when category not found in the database")
    void shouldReturnEmptyWhenCategoryNotFoundInDatabase() {
        // Given
        var categoryName = randomAlphabetic(10);

        when(categoryRepository.findByName(categoryName)).thenReturn(Optional.empty());

        // When
        Optional<CategoryDto> category = categoryFindServiceImpls.findCategoryByName(categoryName);

        // Then
        assertThat(category).isEmpty();

        // Verify
        verify(categoryRepository, times(1)).findByName(categoryName);
    }
}

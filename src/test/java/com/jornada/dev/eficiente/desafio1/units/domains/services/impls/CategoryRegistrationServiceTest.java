package com.jornada.dev.eficiente.desafio1.units.domains.services.impls;

import static com.jornada.dev.eficiente.desafio1.builders.CategoryBuilder.createCategoryDto;
import static com.jornada.dev.eficiente.desafio1.builders.CategoryBuilder.createCategoryEntity;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.jornada.dev.eficiente.desafio1.domains.dtos.CategoryDto;
import com.jornada.dev.eficiente.desafio1.domains.execptions.CategoryAlreadyExistsException;
import com.jornada.dev.eficiente.desafio1.domains.mappers.CategoryDomainMapper;
import com.jornada.dev.eficiente.desafio1.domains.repositories.CategoryRepository;
import com.jornada.dev.eficiente.desafio1.domains.services.impls.CategoryRegistrationServiceImpl;
import com.jornada.dev.eficiente.desafio1.units.UnitTestAbstract;

import java.util.Objects;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

class CategoryRegistrationServiceTest extends UnitTestAbstract {

    @Mock
    private CategoryRepository categoryRepository;

    @Mock
    private CategoryDomainMapper mapper;

    @InjectMocks
    private CategoryRegistrationServiceImpl categoryRegistrationService;

    @Test
    @DisplayName("Should return success when registering an category that does not exist in the database")
    void save_category_success() {
        // Given
        var categoryDto = createCategoryDto("Categoria");
        var categoryEntity = createCategoryEntity(categoryDto.name());

        // Mock
        when(mapper.mapToEntity(categoryDto)).thenReturn(categoryEntity);
        when(mapper.mapToDto(categoryEntity)).thenReturn(categoryDto);
        when(categoryRepository.findByName(categoryDto.name()))
                .thenReturn(Optional.empty());
        when(categoryRepository.save(categoryEntity)).thenReturn(categoryEntity);

        // When
        CategoryDto categorySaved = categoryRegistrationService.save(categoryDto);

        // Then
        assertThat(categorySaved).isNotNull();
        assertThat(categorySaved.name()).isEqualTo(categoryDto.name());
        assertThat(categorySaved.createDate()).isEqualTo(categoryDto.createDate());
        assertThat(categorySaved.updateDate()).isEqualTo(categoryDto.updateDate());

        // Verify
        verify(categoryRepository, times(1)).findByName(categoryDto.name());
        verify(categoryRepository, times(1)).save(categoryEntity);
    }

    @Test
    @DisplayName("CategoryAlreadyExistsException must be thrown when trying " +
            "to register an category that exists in the database")
    void save_category_email_validated() {
        // Given
        var categoryDto = createCategoryDto("Categoria");
        var categoryEntity = createCategoryEntity(categoryDto.name());

        // Mock
        when(categoryRepository.findByName(categoryDto.name()))
                .thenReturn(Optional.ofNullable(categoryEntity));

        // When & Then
        assertThatThrownBy(() -> categoryRegistrationService.save(categoryDto))
                .isInstanceOf(CategoryAlreadyExistsException.class)
                .hasMessage("Category with name " + categoryDto.name() +
                        " already exists");

        // Verify
        verify(categoryRepository, times(1)).findByName(categoryDto.name());
        verify(categoryRepository, times(0))
                .save(Objects.requireNonNull(categoryEntity));
    }
}

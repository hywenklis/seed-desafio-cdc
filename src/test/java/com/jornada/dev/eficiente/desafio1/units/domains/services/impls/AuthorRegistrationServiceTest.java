package com.jornada.dev.eficiente.desafio1.units.domains.services.impls;

import static com.jornada.dev.eficiente.desafio1.builders.AuthorBuilder.createAuthorDto;
import static com.jornada.dev.eficiente.desafio1.builders.AuthorBuilder.createAuthorEntity;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.jornada.dev.eficiente.desafio1.domains.dtos.AuthorDto;
import com.jornada.dev.eficiente.desafio1.domains.mappers.AuthorDomainMapper;
import com.jornada.dev.eficiente.desafio1.domains.repositories.AuthorRepository;
import com.jornada.dev.eficiente.desafio1.domains.services.impls.AuthorRegistrationServiceImpl;
import com.jornada.dev.eficiente.desafio1.units.UnitTestAbstract;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

class AuthorRegistrationServiceTest extends UnitTestAbstract {

    @Mock
    private AuthorRepository authorRepository;

    @Mock
    private AuthorDomainMapper mapper;

    @InjectMocks
    private AuthorRegistrationServiceImpl authorRegistrationService;

    @Test
    @DisplayName("Should return success when registering "
        + "an authorEmail that does not exist in the database")
    void save_author_success() {
        // Given
        var authorDto =
            createAuthorDto("Hywenklis", "hywenklis@email.com", "description");
        var authorEntity = createAuthorEntity(authorDto.name(), authorDto.email(),
            authorDto.description());

        // Mock
        when(mapper.mapToEntity(authorDto)).thenReturn(authorEntity);
        when(mapper.mapToDto(authorEntity)).thenReturn(authorDto);
        when(authorRepository.save(authorEntity)).thenReturn(authorEntity);

        // When
        AuthorDto authorSaved = authorRegistrationService.save(authorDto);

        // Then
        assertThat(authorSaved).isNotNull();
        assertThat(authorSaved.name()).isEqualTo(authorDto.name());
        assertThat(authorSaved.email()).isEqualTo(authorDto.email());
        assertThat(authorSaved.description()).isEqualTo(authorDto.description());
        assertThat(authorSaved.createDate()).isEqualTo(authorDto.createDate());
        assertThat(authorSaved.updateDate()).isEqualTo(authorDto.updateDate());

        // Verify
        verify(authorRepository, times(1)).save(authorEntity);
    }
}

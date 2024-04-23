package com.jornada.dev.eficiente.desafio1.units.domains.services.impls.author;

import static com.jornada.dev.eficiente.desafio1.builders.author.AuthorBuilder.createAuthorDto;
import static com.jornada.dev.eficiente.desafio1.builders.author.AuthorBuilder.createAuthorEntity;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.jornada.dev.eficiente.desafio1.domains.author.dtos.AuthorDto;
import com.jornada.dev.eficiente.desafio1.domains.author.mappers.AuthorDomainMapper;
import com.jornada.dev.eficiente.desafio1.domains.author.repositories.AuthorRepository;
import com.jornada.dev.eficiente.desafio1.domains.author.services.impls.AuthorRegistrationServiceImpl;
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
        var authorDto = createAuthorDto(
            randomAlphabetic(10),
            randomAlphabetic(10) + "@email.com",
            randomAlphabetic(10));

        var authorEntity = createAuthorEntity(
            authorDto.name(),
            authorDto.email(),
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

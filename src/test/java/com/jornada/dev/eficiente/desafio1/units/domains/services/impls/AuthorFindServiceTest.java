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
import com.jornada.dev.eficiente.desafio1.domains.services.impls.AuthorFindServiceImpls;
import com.jornada.dev.eficiente.desafio1.units.UnitTestAbstract;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

class AuthorFindServiceTest extends UnitTestAbstract {

    @Mock
    private AuthorRepository authorRepository;

    @Mock
    private AuthorDomainMapper mapper;

    @InjectMocks
    private AuthorFindServiceImpls authorFindServiceImpls;

    @Test
    @DisplayName("Should return success when finding an author in the database")
    void shouldReturnAuthor_WhenFoundInDatabase() {
        // Given
        var authorDto =
            createAuthorDto("Hywenklis", "hywenklis@email.com", "description");
        var authorEntity = createAuthorEntity(authorDto.name(), authorDto.email(),
            authorDto.description());

        when(mapper.mapToDto(authorEntity)).thenReturn(authorDto);
        when(authorRepository.findByEmail(authorDto.email()))
            .thenReturn(Optional.of(authorEntity));

        // When
        Optional<AuthorDto> author = authorFindServiceImpls.findAuthorByEmail(authorDto.email());

        assertThat(author).isPresent();
        assertThat(author.get().id()).isEqualTo(authorDto.id());
        assertThat(author.get().name()).isEqualTo(authorDto.name());
        assertThat(author.get().email()).isEqualTo(authorDto.email());
        assertThat(author.get().description()).isEqualTo(authorDto.description());
        assertThat(author.get().createDate()).isEqualTo(authorDto.createDate());
        assertThat(author.get().updateDate()).isEqualTo(authorDto.updateDate());

        // Verify
        verify(authorRepository, times(1)).findByEmail(authorDto.email());
    }

    @Test
    @DisplayName("Should return empty when you cannot find an author in the database")
    void shouldReturnEmpty_WhenAuthorNotFoundInDatabase() {
        // Given
        var authorDto =
            createAuthorDto("Hywenklis", "hywenklis@email.com", "description");

        when(authorRepository.findByEmail(authorDto.email())).thenReturn(Optional.empty());

        // When
        Optional<AuthorDto> author = authorFindServiceImpls.findAuthorByEmail(authorDto.email());

        assertThat(author).isEmpty();

        // Verify
        verify(authorRepository, times(1)).findByEmail(authorDto.email());
    }
}

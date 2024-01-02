package com.jornada.dev.eficiente.desafio1.units.domains.services.impls;

import static com.jornada.dev.eficiente.desafio1.builders.AuthorBuilder.createAuthorDto;
import static com.jornada.dev.eficiente.desafio1.builders.AuthorBuilder.createAuthorEntity;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.jornada.dev.eficiente.desafio1.domains.dtos.AuthorDto;
import com.jornada.dev.eficiente.desafio1.domains.execptions.AuthorAlreadyExistsException;
import com.jornada.dev.eficiente.desafio1.domains.mappers.AuthorDomainMapper;
import com.jornada.dev.eficiente.desafio1.domains.repositories.AuthorRepository;
import com.jornada.dev.eficiente.desafio1.domains.services.impls.AuthorRegistrationServiceImpl;
import com.jornada.dev.eficiente.desafio1.units.UnitTestAbstract;
import java.util.Objects;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

class AuthorRegistrationServiceTest extends UnitTestAbstract {

  @Mock private AuthorRepository authorRepository;

  @Mock private AuthorDomainMapper mapper;

  @InjectMocks private AuthorRegistrationServiceImpl authorRegistrationService;

  @Test
  @DisplayName(
      "Should return success when registering an author that does not exist in the database")
  void
  save_author_success() {
    // Given
    var authorDto =
        createAuthorDto("Hywenklis", "hywenklis@email.com", "description");
    var authorEntity = createAuthorEntity(authorDto.name(), authorDto.email(),
                                          authorDto.description());

    // Mock
    when(mapper.mapToEntity(authorDto)).thenReturn(authorEntity);
    when(mapper.mapToDto(authorEntity)).thenReturn(authorDto);
    when(authorRepository.findByEmail(authorDto.email()))
        .thenReturn(Optional.empty());
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
    verify(authorRepository, times(1)).findByEmail(authorDto.email());
    verify(authorRepository, times(1)).save(authorEntity);
  }

  @Test
  @DisplayName("AuthorAlreadyExistsException must be thrown when trying "
               + "to register an author that exists in the database")
  void
  save_author_email_validated() {
    // Given
    var authorDto =
        createAuthorDto("Hywenklis", "hywenklis@email.com", "description");
    var authorEntity = createAuthorEntity(authorDto.name(), authorDto.email(),
                                          authorDto.description());

    // Mock
    when(authorRepository.findByEmail(authorDto.email()))
        .thenReturn(Optional.ofNullable(authorEntity));

    // When & Then
    assertThatThrownBy(() -> authorRegistrationService.save(authorDto))
        .isInstanceOf(AuthorAlreadyExistsException.class)
        .hasMessage("Author with email " + authorDto.email() +
                    " already exists");

    // Verify
    verify(authorRepository, times(1)).findByEmail(authorDto.email());
    verify(authorRepository, times(0))
        .save(Objects.requireNonNull(authorEntity));
  }
}

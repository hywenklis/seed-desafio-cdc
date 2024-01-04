package com.jornada.dev.eficiente.desafio1.units.domains.annotations.validators;

import static com.jornada.dev.eficiente.desafio1.domains.enuns.UniqueType.AUTHOR_EMAIL;
import static com.jornada.dev.eficiente.desafio1.domains.enuns.UniqueType.BOOK_ISBN;
import static com.jornada.dev.eficiente.desafio1.domains.enuns.UniqueType.BOOK_TITLE;
import static com.jornada.dev.eficiente.desafio1.domains.enuns.UniqueType.CATEGORY_NAME;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

import com.jornada.dev.eficiente.desafio1.domains.annotations.Unique;
import com.jornada.dev.eficiente.desafio1.domains.annotations.validators.UniqueValidator;
import com.jornada.dev.eficiente.desafio1.domains.entities.AuthorEntity;
import com.jornada.dev.eficiente.desafio1.domains.entities.BookEntity;
import com.jornada.dev.eficiente.desafio1.domains.entities.CategoryEntity;
import com.jornada.dev.eficiente.desafio1.domains.enuns.UniqueType;
import com.jornada.dev.eficiente.desafio1.domains.repositories.AuthorRepository;
import com.jornada.dev.eficiente.desafio1.domains.repositories.BookRepository;
import com.jornada.dev.eficiente.desafio1.domains.repositories.CategoryRepository;
import com.jornada.dev.eficiente.desafio1.units.UnitTestAbstract;
import jakarta.validation.ConstraintValidatorContext;
import java.util.Optional;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;

class UniqueValidatorTest extends UnitTestAbstract {

    @Mock
    private AuthorRepository authorRepository;

    @Mock
    private CategoryRepository categoryRepository;

    @Mock
    private BookRepository bookRepository;

    @Mock
    private ConstraintValidatorContext context;

    @Mock
    private Unique uniqueAnnotation;

    @InjectMocks
    private UniqueValidator uniqueValidator;

    @DisplayName("isValid should return true when value is null or empty")
    @ParameterizedTest
    @MethodSource("provideNullOrEmptyValues")
    void isValid_ShouldReturnTrue_WhenValueIsNullOrEmpty(String value) {
        // When
        boolean result = uniqueValidator.isValid(value, context);

        // Then
        assertThat(result).isTrue();

        // Verify that repository methods are not called
        verifyNoInteractions(authorRepository, categoryRepository);
    }

    @DisplayName("isValid should return true when value is unique in the repository")
    @ParameterizedTest
    @MethodSource("provideUniqueValues")
    void isValid_ShouldReturnTrue_WhenValueIsUnique(UniqueType type, String uniqueValue) {
        // Given
        when(uniqueAnnotation.value()).thenReturn(type);

        // When
        uniqueValidator.initialize(uniqueAnnotation);
        boolean result = uniqueValidator.isValid(uniqueValue, context);

        // Then
        assertThat(result).isTrue();

        // Verify that the correct repository method is called
        verifyRepositoryMethodInvocation(type, uniqueValue, 1);
    }

    @DisplayName("isValid should return false when value is not unique in the repository")
    @ParameterizedTest
    @MethodSource("provideNonUniqueValues")
    void isValid_ShouldReturnFalse_WhenValueIsNotUnique(UniqueType type, String nonUniqueValue) {
        // Given
        var author = AuthorEntity.builder().email(nonUniqueValue).build();
        var category = CategoryEntity.builder().name(nonUniqueValue).build();
        var book = BookEntity.builder().title(nonUniqueValue).isbn(nonUniqueValue).build();

        when(uniqueAnnotation.value()).thenReturn(type);

        // Mock repository responses based on type
        if (type == AUTHOR_EMAIL) {
            when(authorRepository.findByEmail(nonUniqueValue)).thenReturn(Optional.of(author));
        } else if (type == CATEGORY_NAME) {
            when(categoryRepository.findByName(nonUniqueValue)).thenReturn(Optional.of(category));
        } else if (type == BOOK_TITLE) {
            when(bookRepository.findByTitle(nonUniqueValue)).thenReturn(Optional.of(book));
        } else if (type == BOOK_ISBN) {
            when(bookRepository.findByIsbn(nonUniqueValue)).thenReturn(Optional.of(book));
        }

        // When
        uniqueValidator.initialize(uniqueAnnotation);
        boolean result = uniqueValidator.isValid(nonUniqueValue, context);

        // Then
        assertThat(result).isFalse();

        // Verify that the correct repository method is called
        verifyRepositoryMethodInvocation(type, nonUniqueValue, 1);
    }

    private static Stream<Arguments> provideNullOrEmptyValues() {
        return Stream.of(
            Arguments.of((String) null),
            Arguments.of("")
        );
    }

    private static Stream<Arguments> provideNonUniqueValues() {
        return Stream.of(
            Arguments.of(AUTHOR_EMAIL, "nonUniqueEmail"),
            Arguments.of(CATEGORY_NAME, "nonUniqueCategoryName"),
            Arguments.of(BOOK_TITLE, "nonUniqueBookTitle"),
            Arguments.of(BOOK_ISBN, "nonUniqueBookIsbn")
        );
    }

    private static Stream<Arguments> provideUniqueValues() {
        return Stream.of(
            Arguments.of(AUTHOR_EMAIL, "uniqueEmail"),
            Arguments.of(CATEGORY_NAME, "uniqueCategoryName"),
            Arguments.of(BOOK_TITLE, "uniqueBookTitle"),
            Arguments.of(BOOK_ISBN, "uniqueBookIsbn")
        );
    }

    private void verifyRepositoryMethodInvocation(UniqueType type, String value, int times) {
        switch (type) {
            case AUTHOR_EMAIL:
                verify(authorRepository, times(times)).findByEmail(value);
                verifyNoInteractions(categoryRepository, bookRepository);
                break;
            case CATEGORY_NAME:
                verify(categoryRepository, times(times)).findByName(value);
                verifyNoInteractions(authorRepository, bookRepository);
                break;
            case BOOK_TITLE:
                verify(bookRepository, times(times)).findByTitle(value);
                verifyNoInteractions(authorRepository, categoryRepository);
                break;
            case BOOK_ISBN:
                verify(bookRepository, times(times)).findByIsbn(value);
                verifyNoInteractions(authorRepository, categoryRepository);
                break;
            default:
                throw new IllegalArgumentException("Unsupported validation type: " + type);
        }
    }
}

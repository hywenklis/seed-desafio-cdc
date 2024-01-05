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
import com.jornada.dev.eficiente.desafio1.domains.dtos.AuthorDto;
import com.jornada.dev.eficiente.desafio1.domains.dtos.BookDto;
import com.jornada.dev.eficiente.desafio1.domains.dtos.CategoryDto;
import com.jornada.dev.eficiente.desafio1.domains.enuns.UniqueType;
import com.jornada.dev.eficiente.desafio1.domains.services.AuthorFindService;
import com.jornada.dev.eficiente.desafio1.domains.services.BookFindService;
import com.jornada.dev.eficiente.desafio1.domains.services.CategoryFindService;
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
    private AuthorFindService authorFindService;

    @Mock
    private CategoryFindService categoryFindService;

    @Mock
    private BookFindService bookFindService;

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
        verifyNoInteractions(authorFindService, categoryFindService);
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
        var author = AuthorDto.builder().email(nonUniqueValue).build();
        var category = CategoryDto.builder().name(nonUniqueValue).build();
        var book = BookDto.builder().title(nonUniqueValue).isbn(nonUniqueValue).build();

        when(uniqueAnnotation.value()).thenReturn(type);

        // Mock repository responses based on type
        mockRepositoryByType(type, nonUniqueValue, author, category, book);

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

    private void mockRepositoryByType(UniqueType type,
                                      String nonUniqueValue,
                                      AuthorDto author,
                                      CategoryDto category,
                                      BookDto book) {
        if (type == AUTHOR_EMAIL) {
            when(authorFindService.findAuthorByEmail(nonUniqueValue)).thenReturn(
                Optional.of(author));
        } else if (type == CATEGORY_NAME) {
            when(categoryFindService.findCategoryByName(nonUniqueValue)).thenReturn(
                Optional.of(category));
        } else if (type == BOOK_TITLE) {
            when(bookFindService.findBookByTitle(nonUniqueValue)).thenReturn(Optional.of(book));
        } else if (type == BOOK_ISBN) {
            when(bookFindService.findBookByIsbn(nonUniqueValue)).thenReturn(Optional.of(book));
        }
    }

    private void verifyRepositoryMethodInvocation(UniqueType type, String value, int times) {
        switch (type) {
            case AUTHOR_EMAIL:
                verify(authorFindService, times(times)).findAuthorByEmail(value);
                verifyNoInteractions(categoryFindService, bookFindService);
                break;
            case CATEGORY_NAME:
                verify(categoryFindService, times(times)).findCategoryByName(value);
                verifyNoInteractions(authorFindService, bookFindService);
                break;
            case BOOK_TITLE:
                verify(bookFindService, times(times)).findBookByTitle(value);
                verifyNoInteractions(authorFindService, categoryFindService);
                break;
            case BOOK_ISBN:
                verify(bookFindService, times(times)).findBookByIsbn(value);
                verifyNoInteractions(authorFindService, categoryFindService);
                break;
            default:
                throw new IllegalArgumentException("Unsupported validation type: " + type);
        }
    }
}

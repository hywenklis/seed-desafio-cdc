package com.jornada.dev.eficiente.desafio1.units.domains.services.impls;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.jornada.dev.eficiente.desafio1.domains.dtos.BookDto;
import com.jornada.dev.eficiente.desafio1.domains.entities.BookEntity;
import com.jornada.dev.eficiente.desafio1.domains.mappers.BookDomainMapper;
import com.jornada.dev.eficiente.desafio1.domains.repositories.BookRepository;
import com.jornada.dev.eficiente.desafio1.domains.services.impls.BookFindServiceImpls;
import com.jornada.dev.eficiente.desafio1.units.UnitTestAbstract;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

class BookFindServiceTest extends UnitTestAbstract {

    @Mock
    private BookRepository bookRepository;

    @Mock
    private BookDomainMapper bookMapper;

    @InjectMocks
    private BookFindServiceImpls bookFindServiceImpls;

    @Test
    @DisplayName("Should return book when found in the database by title")
    void shouldReturnBook_WhenFoundInDatabaseByTitle() {
        // Given
        var bookTitle = "The Great Gatsby";
        var bookDto = BookDto.builder().title(bookTitle).build();
        var bookEntity = BookEntity.builder().title(bookTitle).build();

        // Mock
        when(bookMapper.mapToDto(bookEntity)).thenReturn(bookDto);
        when(bookRepository.findByTitle(bookTitle)).thenReturn(Optional.of(bookEntity));

        // When
        Optional<BookDto> book = bookFindServiceImpls.findBookByTitle(bookTitle);

        // Then
        assertThat(book).isPresent();
        assertThat(book.get().title()).isEqualTo(bookDto.title());

        // Verify
        verify(bookRepository, times(1)).findByTitle(bookTitle);
    }

    @Test
    @DisplayName("Should return empty when book not found in the database by title")
    void shouldReturnEmpty_WhenBookNotFoundInDatabaseByTitle() {
        // Given
        var bookTitle = "NonExistentBook";

        // Mock
        when(bookRepository.findByTitle(bookTitle)).thenReturn(Optional.empty());

        // When
        Optional<BookDto> book = bookFindServiceImpls.findBookByTitle(bookTitle);

        // Then
        assertThat(book).isEmpty();

        // Verify
        verify(bookRepository, times(1)).findByTitle(bookTitle);
    }

    @Test
    @DisplayName("Should return book when found in the database by ISBN")
    void shouldReturnBook_WhenFoundInDatabaseByIsbn() {
        // Given
        var bookIsbn = "978-3-16-148410-0";
        var bookDto = BookDto.builder().isbn(bookIsbn).build();
        var bookEntity = BookEntity.builder().isbn(bookIsbn).build();

        // Mock
        when(bookMapper.mapToDto(bookEntity)).thenReturn(bookDto);
        when(bookRepository.findByIsbn(bookIsbn)).thenReturn(Optional.of(bookEntity));

        // When
        Optional<BookDto> book = bookFindServiceImpls.findBookByIsbn(bookIsbn);

        // Then
        assertThat(book).isPresent();
        assertThat(book.get().isbn()).isEqualTo(bookDto.isbn());

        // Verify
        verify(bookRepository, times(1)).findByIsbn(bookIsbn);
    }

    @Test
    @DisplayName("Should return empty when book not found in the database by ISBN")
    void shouldReturnEmpty_WhenBookNotFoundInDatabaseByIsbn() {
        // Given
        var bookIsbn = "NonExistentISBN";

        // Mock
        when(bookRepository.findByIsbn(bookIsbn)).thenReturn(Optional.empty());

        // When
        Optional<BookDto> book = bookFindServiceImpls.findBookByIsbn(bookIsbn);

        // Then
        assertThat(book).isEmpty();

        // Verify
        verify(bookRepository, times(1)).findByIsbn(bookIsbn);
    }
}

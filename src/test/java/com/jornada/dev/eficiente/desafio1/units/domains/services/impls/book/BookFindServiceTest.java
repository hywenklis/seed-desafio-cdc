package com.jornada.dev.eficiente.desafio1.units.domains.services.impls.book;

import static java.util.UUID.randomUUID;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.apache.commons.lang3.RandomStringUtils.randomNumeric;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.jornada.dev.eficiente.desafio1.domains.book.dtos.BookDto;
import com.jornada.dev.eficiente.desafio1.domains.book.entities.BookEntity;
import com.jornada.dev.eficiente.desafio1.domains.book.mappers.BookDomainMapper;
import com.jornada.dev.eficiente.desafio1.domains.book.repositories.BookRepository;
import com.jornada.dev.eficiente.desafio1.domains.book.services.impls.BookFindServiceImpl;
import com.jornada.dev.eficiente.desafio1.units.UnitTestAbstract;
import java.util.List;
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
    private BookFindServiceImpl bookFindServiceImpls;

    @Test
    @DisplayName("Should return book when found in the database by title")
    void shouldReturnBook_WhenFoundInDatabaseByTitle() {
        // Given
        var bookTitle = randomAlphabetic(10);
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
        var bookTitle = randomAlphabetic(10);

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
        var bookIsbn = randomNumeric(10);
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
        var bookIsbn = randomNumeric(10);

        // Mock
        when(bookRepository.findByIsbn(bookIsbn)).thenReturn(Optional.empty());

        // When
        Optional<BookDto> book = bookFindServiceImpls.findBookByIsbn(bookIsbn);

        // Then
        assertThat(book).isEmpty();

        // Verify
        verify(bookRepository, times(1)).findByIsbn(bookIsbn);
    }

    @Test
    @DisplayName("Should return all books when found in the database")
    void shouldReturnAllBooks_WhenFoundInDatabase() {
        // Given
        var bookDto = BookDto.builder().id(randomUUID()).title(randomAlphabetic(10)).build();
        var bookEntity = BookEntity.builder().id(bookDto.id()).title(bookDto.title()).build();

        // Mock
        when(bookRepository.findAll()).thenReturn(List.of(bookEntity));

        // When
        when(bookMapper.mapToDto(bookEntity)).thenReturn(bookDto);
        Optional<List<BookDto>> book = bookFindServiceImpls.findAll();

        // Then
        assertThat(book).isPresent();
        assertThat(book.get().stream().toList().getFirst().id()).isEqualTo(bookDto.id());
        assertThat(book.get().stream().toList().getFirst().title()).isEqualTo(bookDto.title());

        // Verify
        verify(bookRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("Should return list empty when not found in the database")
    void shouldReturnListEmpty_WhenNotFoundInDatabase() {
        // Mock
        when(bookRepository.findAll()).thenReturn(List.of());

        var book = bookFindServiceImpls.findAll();

        // Then
        assertThat(book)
            .isPresent()
            .isEqualTo(Optional.of(List.of()));

        // Verify
        verify(bookRepository, times(1)).findAll();
    }
}

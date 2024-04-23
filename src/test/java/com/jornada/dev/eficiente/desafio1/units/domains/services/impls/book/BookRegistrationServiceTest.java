package com.jornada.dev.eficiente.desafio1.units.domains.services.impls.book;

import static com.jornada.dev.eficiente.desafio1.builders.author.AuthorBuilder.createAuthorDto;
import static com.jornada.dev.eficiente.desafio1.builders.author.AuthorBuilder.createAuthorEntity;
import static com.jornada.dev.eficiente.desafio1.builders.book.BookBuilder.createBookDetailsDto;
import static com.jornada.dev.eficiente.desafio1.builders.book.BookBuilder.createBookDto;
import static com.jornada.dev.eficiente.desafio1.builders.book.BookBuilder.createBookEntity;
import static com.jornada.dev.eficiente.desafio1.builders.book.BookBuilder.createSocialMediaDto;
import static com.jornada.dev.eficiente.desafio1.builders.category.CategoryBuilder.createCategoryDto;
import static com.jornada.dev.eficiente.desafio1.builders.category.CategoryBuilder.createCategoryEntity;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.apache.commons.lang3.RandomStringUtils.randomNumeric;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.jornada.dev.eficiente.desafio1.domains.author.services.AuthorFindService;
import com.jornada.dev.eficiente.desafio1.domains.book.dtos.BookDto;
import com.jornada.dev.eficiente.desafio1.domains.book.entities.BookEntity;
import com.jornada.dev.eficiente.desafio1.domains.book.mappers.BookDomainMapper;
import com.jornada.dev.eficiente.desafio1.domains.book.repositories.BookRepository;
import com.jornada.dev.eficiente.desafio1.domains.book.services.impls.BookRegistrationServiceImpl;
import com.jornada.dev.eficiente.desafio1.domains.category.services.CategoryFindService;
import com.jornada.dev.eficiente.desafio1.domains.exceptions.NotFoundException;
import com.jornada.dev.eficiente.desafio1.units.UnitTestAbstract;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

class BookRegistrationServiceTest extends UnitTestAbstract {

    @Mock
    private BookRepository bookRepository;

    @Mock
    private BookDomainMapper bookMapper;

    @Mock
    private AuthorFindService authorFindService;

    @Mock
    private CategoryFindService categoryFindService;

    @InjectMocks
    private BookRegistrationServiceImpl bookRegistrationServiceImpl;

    @Test
    @DisplayName("Should save book successfully")
    void shouldSaveBook_Successfully() {
        // Given
        var authorDto = createAuthorDto(
            randomAlphabetic(10),
            randomAlphabetic(10) + "@example.com",
            randomAlphabetic(10));

        var authorEntity = createAuthorEntity(
            authorDto.name(),
            authorDto.email(),
            authorDto.description());

        var categoryDto = createCategoryDto(randomAlphabetic(10));
        var categoryEntity = createCategoryEntity(categoryDto.name());
        var publicationDate = LocalDateTime.now().plusDays(1);
        var bigDecimal = BigDecimal.valueOf(20.0);

        var socialMediaDto = createSocialMediaDto(
            randomAlphabetic(10),
            randomAlphabetic(10),
            randomAlphabetic(10),
            randomAlphabetic(10)
        );

        var bookDetailsDto = createBookDetailsDto(
            randomAlphabetic(10),
            bigDecimal.add(bigDecimal),
            socialMediaDto
        );

        var bookDto = createBookDto(
            randomAlphabetic(10),
            randomAlphabetic(10),
            randomAlphabetic(10),
            randomAlphabetic(10),
            bigDecimal,
            bigDecimal,
            100L,
            randomNumeric(10),
            LocalDateTime.of(publicationDate.getYear(),
                publicationDate.getMonth(),
                publicationDate.getDayOfMonth(),
                publicationDate.getHour(),
                publicationDate.getMinute(),
                publicationDate.getSecond()),
            categoryDto,
            authorDto,
            bookDetailsDto);

        var bookEntity = createBookEntity(
            bookDto.title(),
            bookDto.subtitle(),
            bookDto.description(),
            bookDto.summary(),
            bookDto.ebookPrice(),
            bookDto.printedBookPrice(),
            bookDto.numberOfPages(),
            bookDto.isbn(),
            bookDto.publicationDate(),
            categoryEntity,
            authorEntity);

        // Mock
        when(bookMapper.mapToDto(bookEntity)).thenReturn(bookDto);
        when(bookMapper.mapToEntity(bookDto)).thenReturn(bookEntity);
        when(authorFindService.findAuthorByEmail(authorDto.email())).thenReturn(
            Optional.of(authorDto));
        when(categoryFindService.findCategoryByName(categoryDto.name())).thenReturn(
            Optional.of(categoryDto));
        when(bookRepository.save(bookEntity)).thenReturn(bookEntity);

        // When
        BookDto savedBook = bookRegistrationServiceImpl.save(bookDto);

        // Then
        assertThat(savedBook.title()).isEqualTo(bookDto.title());
        assertThat(savedBook.description()).isEqualTo(bookDto.description());
        assertThat(savedBook.summary()).isEqualTo(bookDto.summary());
        assertThat(savedBook.ebookPrice()).isEqualTo(bookDto.ebookPrice());
        assertThat(savedBook.numberOfPages()).isEqualTo(bookDto.numberOfPages());
        assertThat(savedBook.isbn()).isEqualTo(bookDto.isbn());
        assertThat(savedBook.publicationDate()).isEqualTo(bookDto.publicationDate());
        assertThat(savedBook.category().id()).isEqualTo(bookDto.category().id());
        assertThat(savedBook.category().name()).isEqualTo(bookDto.category().name());
        assertThat(savedBook.category().createDate()).isEqualTo(bookDto.category().createDate());
        assertThat(savedBook.category().updateDate()).isEqualTo(bookDto.category().updateDate());
        assertThat(savedBook.author().id()).isEqualTo(bookDto.author().id());
        assertThat(savedBook.author().name()).isEqualTo(bookDto.author().name());
        assertThat(savedBook.author().email()).isEqualTo(bookDto.author().email());
        assertThat(savedBook.author().description()).isEqualTo(bookDto.author().description());
        assertThat(savedBook.author().createDate()).isEqualTo(bookDto.author().createDate());
        assertThat(savedBook.author().updateDate()).isEqualTo(bookDto.author().updateDate());

        // Verify
        verify(authorFindService, times(1)).findAuthorByEmail(authorDto.email());
        verify(categoryFindService, times(1)).findCategoryByName(categoryDto.name());
        verify(bookMapper, times(1)).mapToEntity(bookDto);
        verify(bookMapper, times(1)).mapToDto(bookEntity);
        verify(bookRepository, times(1)).save(bookEntity);
    }

    @Test
    @DisplayName("Should throw NotFoundException when author not found")
    void shouldThrowNotFoundExceptionWhenAuthorNotFound() {
        // Given
        var authorDto = createAuthorDto(
            randomAlphabetic(10),
            randomAlphabetic(10) + "@example.com",
            randomAlphabetic(10));

        var categoryDto = createCategoryDto(randomAlphabetic(10));
        var publicationDate = LocalDateTime.now().plusDays(1);
        var bigDecimal = BigDecimal.valueOf(20.0);

        var socialMediaDto = createSocialMediaDto(
            randomAlphabetic(10),
            randomAlphabetic(10),
            randomAlphabetic(10),
            randomAlphabetic(10)
        );

        var bookDetailsDto = createBookDetailsDto(
            randomAlphabetic(10),
            bigDecimal.add(bigDecimal),
            socialMediaDto
        );

        var bookDto = createBookDto(
            randomAlphabetic(10),
            randomAlphabetic(10),
            randomAlphabetic(10),
            randomAlphabetic(10),
            bigDecimal,
            bigDecimal,
            100L,
            randomNumeric(10),
            LocalDateTime.of(publicationDate.getYear(),
                publicationDate.getMonth(),
                publicationDate.getDayOfMonth(),
                publicationDate.getHour(),
                publicationDate.getMinute(),
                publicationDate.getSecond()),
            categoryDto,
            authorDto,
            bookDetailsDto);

        // Mock
        when(authorFindService.findAuthorByEmail(bookDto.author().email()))
            .thenReturn(Optional.empty());

        // When/Then
        assertThatThrownBy(() -> bookRegistrationServiceImpl.save(bookDto))
            .isInstanceOf(NotFoundException.class)
            .hasMessageContaining("Author not found with email: " + bookDto.author().email());

        // Verify
        verify(authorFindService, times(1)).findAuthorByEmail(bookDto.author().email());
        verify(categoryFindService, times(0)).findCategoryByName(bookDto.category().name());
        verify(bookRepository, times(0)).save(BookEntity.builder().build());
        verify(bookMapper, times(0)).mapToEntity(bookDto);
        verify(bookMapper, times(0)).mapToDto(BookEntity.builder().build());
    }

    @Test
    @DisplayName("Should throw NotFoundException when category not found")
    void shouldThrowNotFoundExceptionWhenCategoryNotFound() {
        // Given
        var authorDto = createAuthorDto(
            randomAlphabetic(10),
            randomAlphabetic(10) + "@example.com",
            randomAlphabetic(10));

        var categoryDto = createCategoryDto(randomAlphabetic(10));
        var publicationDate = LocalDateTime.now().plusDays(1);
        var bigDecimal = BigDecimal.valueOf(20.0);

        var socialMediaDto = createSocialMediaDto(
            randomAlphabetic(10),
            randomAlphabetic(10),
            randomAlphabetic(10),
            randomAlphabetic(10)
        );

        var bookDetailsDto = createBookDetailsDto(
            randomAlphabetic(10),
            bigDecimal.add(bigDecimal),
            socialMediaDto
        );

        var bookDto = createBookDto(
            randomAlphabetic(10),
            randomAlphabetic(10),
            randomAlphabetic(10),
            randomAlphabetic(10),
            bigDecimal,
            bigDecimal,
            100L,
            randomNumeric(10),
            LocalDateTime.of(publicationDate.getYear(),
                publicationDate.getMonth(),
                publicationDate.getDayOfMonth(),
                publicationDate.getHour(),
                publicationDate.getMinute(),
                publicationDate.getSecond()),
            categoryDto,
            authorDto,
            bookDetailsDto);

        // Mock
        when(authorFindService.findAuthorByEmail(authorDto.email())).thenReturn(
            Optional.of(authorDto));
        when(categoryFindService.findCategoryByName(bookDto.category().name())).thenReturn(
            Optional.empty());

        // When/Then
        assertThatThrownBy(() -> bookRegistrationServiceImpl.save(bookDto))
            .isInstanceOf(NotFoundException.class)
            .hasMessageContaining(
                "Category not found with name: " + bookDto.category().name());

        // Verify
        verify(authorFindService, times(1)).findAuthorByEmail(authorDto.email());
        verify(categoryFindService, times(1)).findCategoryByName(bookDto.category().name());
        verify(bookRepository, times(0)).save(BookEntity.builder().build());
        verify(bookMapper, times(0)).mapToEntity(bookDto);
        verify(bookMapper, times(0)).mapToDto(BookEntity.builder().build());
    }
}

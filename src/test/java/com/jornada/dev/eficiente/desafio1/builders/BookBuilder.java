package com.jornada.dev.eficiente.desafio1.builders;

import com.jornada.dev.eficiente.desafio1.domains.dtos.AuthorDto;
import com.jornada.dev.eficiente.desafio1.domains.dtos.BookDto;
import com.jornada.dev.eficiente.desafio1.domains.dtos.CategoryDto;
import com.jornada.dev.eficiente.desafio1.domains.entities.AuthorEntity;
import com.jornada.dev.eficiente.desafio1.domains.entities.BookEntity;
import com.jornada.dev.eficiente.desafio1.domains.entities.CategoryEntity;
import com.jornada.dev.eficiente.desafio1.web.requests.BookRequest;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class BookBuilder {

    public static BookRequest createBookRequest(String title,
                                                String description,
                                                String summary,
                                                BigDecimal price,
                                                Long numberOfPages,
                                                String isbn,
                                                LocalDateTime publicationDate,
                                                String categoryName,
                                                String authorEmail) {
        return BookRequest.builder()
            .title(title)
            .description(description)
            .summary(summary)
            .price(price)
            .numberOfPages(numberOfPages)
            .isbn(isbn)
            .publicationDate(publicationDate)
            .categoryName(categoryName)
            .authorEmail(authorEmail)
            .build();
    }

    public static BookDto createBookDto(String title,
                                        String description,
                                        String summary,
                                        BigDecimal price,
                                        Long numberOfPages,
                                        String isbn,
                                        LocalDateTime publicationDate,
                                        CategoryDto categoryDto,
                                        AuthorDto authorDto) {
        return BookDto.builder()
            .title(title)
            .description(description)
            .summary(summary)
            .price(price)
            .numberOfPages(numberOfPages)
            .isbn(isbn)
            .publicationDate(publicationDate)
            .category(categoryDto)
            .author(authorDto)
            .createDate(LocalDateTime.now())
            .updateDate(LocalDateTime.now())
            .build();
    }

    public static BookEntity createBookEntity(String title,
                                              String description,
                                              String summary,
                                              BigDecimal price,
                                              Long numberOfPages,
                                              String isbn,
                                              LocalDateTime publicationDate,
                                              CategoryEntity category,
                                              AuthorEntity author) {
        return BookEntity.builder()
            .id(UUID.randomUUID())
            .title(title)
            .description(description)
            .summary(summary)
            .price(price)
            .numberOfPages(numberOfPages)
            .isbn(isbn)
            .publicationDate(publicationDate)
            .category(category)
            .author(author)
            .createDate(LocalDateTime.now())
            .updateDate(LocalDateTime.now())
            .build();
    }
}

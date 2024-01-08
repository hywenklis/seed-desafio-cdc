package com.jornada.dev.eficiente.desafio1.components;

import com.jornada.dev.eficiente.desafio1.builders.BookBuilder;
import com.jornada.dev.eficiente.desafio1.domains.entities.AuthorEntity;
import com.jornada.dev.eficiente.desafio1.domains.entities.BookEntity;
import com.jornada.dev.eficiente.desafio1.domains.entities.CategoryEntity;
import com.jornada.dev.eficiente.desafio1.domains.repositories.BookRepository;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import org.springframework.stereotype.Component;

@Component
public class BookComponent {
    private final BookRepository bookRepository;

    public BookComponent(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public BookEntity createBook(String title,
                                 String description,
                                 String summary,
                                 BigDecimal price,
                                 Long numberOfPages,
                                 String isbn,
                                 LocalDateTime publicationDate,
                                 CategoryEntity category,
                                 AuthorEntity author) {
        return bookRepository.save(
            BookBuilder.createBookEntity(
                title,
                description,
                summary,
                price,
                numberOfPages,
                isbn,
                publicationDate,
                category,
                author
            )
        );
    }
}

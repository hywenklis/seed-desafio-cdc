package com.jornada.dev.eficiente.desafio1.components.book;

import com.jornada.dev.eficiente.desafio1.builders.book.BookBuilder;
import com.jornada.dev.eficiente.desafio1.domains.author.entities.AuthorEntity;
import com.jornada.dev.eficiente.desafio1.domains.book.entities.BookEntity;
import com.jornada.dev.eficiente.desafio1.domains.book.repositories.BookRepository;
import com.jornada.dev.eficiente.desafio1.domains.category.entities.CategoryEntity;
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
                                 String subtitle,
                                 String description,
                                 String summary,
                                 BigDecimal ebookPrice,
                                 BigDecimal printedBookPrice,
                                 Long numberOfPages,
                                 String isbn,
                                 LocalDateTime publicationDate,
                                 CategoryEntity category,
                                 AuthorEntity author) {
        return bookRepository.save(
            BookBuilder.createBookEntity(
                title,
                subtitle,
                description,
                summary,
                ebookPrice,
                printedBookPrice,
                numberOfPages,
                isbn,
                publicationDate,
                category,
                author
            )
        );
    }
}

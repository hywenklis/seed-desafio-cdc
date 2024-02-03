package com.jornada.dev.eficiente.desafio1.components;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.apache.commons.lang3.RandomStringUtils.randomNumeric;

import com.jornada.dev.eficiente.desafio1.domains.entities.BookEntity;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class DataInitializerComponent {
    private final AuthorComponent authorComponent;
    private final CategoryComponent categoryComponent;
    private final BookComponent bookComponent;

    public DataInitializerComponent(AuthorComponent authorComponent,
                                    CategoryComponent categoryComponent,
                                    BookComponent bookComponent) {
        this.authorComponent = authorComponent;
        this.categoryComponent = categoryComponent;
        this.bookComponent = bookComponent;
    }

    @Transactional
    public BookEntity initializeBookData() {
        var author = authorComponent.createAuthor(
            randomAlphabetic(10),
            randomAlphabetic(10) + "@example.com",
            randomAlphabetic(10));

        var category = categoryComponent.createCategory(randomAlphabetic(10));

        var publicationDate = LocalDateTime.now().plusDays(1);

        return bookComponent.createBook(
            randomAlphabetic(10),
            randomAlphabetic(10),
            randomAlphabetic(10),
            randomAlphabetic(10),
            BigDecimal.valueOf(20.0),
            BigDecimal.valueOf(20.0),
            100L,
            randomNumeric(10),
            publicationDate,
            category,
            author
        );
    }
}

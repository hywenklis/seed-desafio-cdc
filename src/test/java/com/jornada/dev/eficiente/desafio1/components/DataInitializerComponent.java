package com.jornada.dev.eficiente.desafio1.components;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.apache.commons.lang3.RandomStringUtils.randomNumeric;

import com.jornada.dev.eficiente.desafio1.components.author.AuthorComponent;
import com.jornada.dev.eficiente.desafio1.components.book.BookComponent;
import com.jornada.dev.eficiente.desafio1.components.category.CategoryComponent;
import com.jornada.dev.eficiente.desafio1.components.country.CountryComponent;
import com.jornada.dev.eficiente.desafio1.components.state.StateComponent;
import com.jornada.dev.eficiente.desafio1.domains.address.country.entities.CountryEntity;
import com.jornada.dev.eficiente.desafio1.domains.address.state.entities.StateEntity;
import com.jornada.dev.eficiente.desafio1.domains.book.entities.BookEntity;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class DataInitializerComponent {
    private final AuthorComponent authorComponent;
    private final CategoryComponent categoryComponent;
    private final BookComponent bookComponent;
    private final StateComponent stateComponent;
    private final CountryComponent countryComponent;

    public DataInitializerComponent(AuthorComponent authorComponent,
                                    CategoryComponent categoryComponent,
                                    BookComponent bookComponent, StateComponent stateComponent,
                                    CountryComponent countryComponent) {
        this.authorComponent = authorComponent;
        this.categoryComponent = categoryComponent;
        this.bookComponent = bookComponent;
        this.stateComponent = stateComponent;
        this.countryComponent = countryComponent;
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

    @Transactional
    public StateEntity initializeStateData() {
        var country = countryComponent.createCountry(randomAlphabetic(10));
        return stateComponent.createState(randomAlphabetic(10), country);
    }

    @Transactional
    public CountryEntity initializeCountryData() {
        return countryComponent.createCountry(randomAlphabetic(10));
    }
}

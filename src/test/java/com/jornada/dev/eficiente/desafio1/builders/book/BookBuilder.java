package com.jornada.dev.eficiente.desafio1.builders.book;

import com.jornada.dev.eficiente.desafio1.domains.author.dtos.AuthorDto;
import com.jornada.dev.eficiente.desafio1.domains.author.entities.AuthorEntity;
import com.jornada.dev.eficiente.desafio1.domains.book.dtos.BookDetailsDto;
import com.jornada.dev.eficiente.desafio1.domains.book.dtos.BookDto;
import com.jornada.dev.eficiente.desafio1.domains.book.dtos.SocialMediaDto;
import com.jornada.dev.eficiente.desafio1.domains.book.entities.BookEntity;
import com.jornada.dev.eficiente.desafio1.domains.category.dtos.CategoryDto;
import com.jornada.dev.eficiente.desafio1.domains.category.entities.CategoryEntity;
import com.jornada.dev.eficiente.desafio1.web.book.requests.BookRequest;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class BookBuilder {

    public static BookRequest createBookRequest(String title,
                                                String subtitle,
                                                String description,
                                                String summary,
                                                BigDecimal ebookPrice,
                                                BigDecimal printedBookPrice,
                                                Long numberOfPages,
                                                String isbn,
                                                LocalDateTime publicationDate,
                                                String categoryName,
                                                String authorEmail) {
        return BookRequest.builder()
            .title(title)
            .subtitle(subtitle)
            .description(description)
            .summary(summary)
            .ebookPrice(ebookPrice)
            .printedBookPrice(printedBookPrice)
            .numberOfPages(numberOfPages)
            .isbn(isbn)
            .publicationDate(publicationDate)
            .categoryName(categoryName)
            .authorEmail(authorEmail)
            .build();
    }

    public static BookDetailsDto createBookDetailsDto(String accessUpdates,
                                                      BigDecimal ebookAndPrintedBookPrice,
                                                      SocialMediaDto socialMedia) {
        return BookDetailsDto.builder()
            .accessUpdates(accessUpdates)
            .ebookAndPrintedBookPrice(ebookAndPrintedBookPrice)
            .socialMedia(socialMedia)
            .build();
    }

    public static SocialMediaDto createSocialMediaDto(String linkFacebook,
                                                String linkTwitter,
                                                String iconFacebookUrl,
                                                String iconTwitterUrl) {

        return SocialMediaDto.builder()
            .linkFacebook(linkFacebook)
            .linkTwitter(linkTwitter)
            .iconFacebookUrl(iconFacebookUrl)
            .iconTwitterUrl(iconTwitterUrl)
            .build();
    }

    public static BookDto createBookDto(String title,
                                        String subtitle,
                                        String description,
                                        String summary,
                                        BigDecimal ebookPrice,
                                        BigDecimal printedBookPrice,
                                        Long numberOfPages,
                                        String isbn,
                                        LocalDateTime publicationDate,
                                        CategoryDto categoryDto,
                                        AuthorDto authorDto,
                                        BookDetailsDto bookDetails) {
        return BookDto.builder()
            .title(title)
            .subtitle(subtitle)
            .description(description)
            .summary(summary)
            .ebookPrice(ebookPrice)
            .printedBookPrice(printedBookPrice)
            .numberOfPages(numberOfPages)
            .isbn(isbn)
            .publicationDate(publicationDate)
            .category(categoryDto)
            .author(authorDto)
            .bookDetails(bookDetails)
            .createDate(LocalDateTime.now())
            .updateDate(LocalDateTime.now())
            .build();
    }

    public static BookEntity createBookEntity(String title,
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
        return BookEntity.builder()
            .id(UUID.randomUUID())
            .title(title)
            .subtitle(subtitle)
            .description(description)
            .summary(summary)
            .ebookPrice(ebookPrice)
            .printedBookPrice(printedBookPrice)
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

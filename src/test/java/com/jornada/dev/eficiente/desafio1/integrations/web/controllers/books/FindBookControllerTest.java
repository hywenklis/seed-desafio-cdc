package com.jornada.dev.eficiente.desafio1.integrations.web.controllers.books;

import static com.jornada.dev.eficiente.desafio1.builders.book.BookBuilder.createSocialMediaDto;
import static com.jornada.dev.eficiente.desafio1.utils.DateUtils.formatLocalDateTime;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.jornada.dev.eficiente.desafio1.builders.book.BookBuilder;
import com.jornada.dev.eficiente.desafio1.integrations.IntegrationTestAbstract;
import java.util.UUID;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

@DisplayName("GET /v1/books")
class FindBookControllerTest extends IntegrationTestAbstract {


    @Test
    @DisplayName("Should return a list of books with details compact")
    void test_success_compact_book() throws Exception {
        var book = dataInitializerComponent.initializeBookData();

        mockMvc.perform(get("/v1/books")
                .contentType(APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isNotEmpty())
            .andExpect(jsonPath("$.[0].id").value(book.getId().toString()))
            .andExpect(jsonPath("$.[0].title").value(book.getTitle()));
    }

    @Test
    @DisplayName("Should return an empty list compact book")
    void test_success_compact_book_empty() throws Exception {
        mockMvc.perform(get("/v1/books")
                .contentType(APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());
    }

    @Test
    @DisplayName("Should return all details of a book")
    void test_success_book_details() throws Exception {
        var book = dataInitializerComponent.initializeBookData();
        var socialMediaDto = createSocialMediaDto(
            socialMediaProperty.getFacebookUrl(book.getTitle()),
            socialMediaProperty.getTwitterUrl(book.getTitle()),
            socialMediaProperty.facebook().icon(),
            socialMediaProperty.twitter().icon()
        );

        var bookDetails = BookBuilder.createBookDetailsDto(
            "Você terá acesso às futuras atualizações do livro",
            book.getEbookPrice().add(book.getPrintedBookPrice()),
            socialMediaDto
        );

        mockMvc.perform(get("/v1/books/{id}", book.getId())
                .contentType(APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.title").value(book.getTitle()))
            .andExpect(jsonPath("$.subtitle").value(book.getSubtitle()))
            .andExpect(jsonPath("$.description").value(book.getDescription()))
            .andExpect(jsonPath("$.summary").value(book.getSummary()))
            .andExpect(jsonPath("$.ebookPrice").value(book.getEbookPrice()))
            .andExpect(jsonPath("$.printedBookPrice").value(book.getPrintedBookPrice()))
            .andExpect(jsonPath("$.numberOfPages").value(book.getNumberOfPages()))
            .andExpect(jsonPath("$.isbn").value(book.getIsbn()))
            .andExpect(jsonPath("$.category.name").value(book.getCategory().getName()))
            .andExpect(jsonPath("$.category.createDate").value(
                formatLocalDateTime(book.getCategory().getCreateDate())))
            .andExpect(jsonPath("$.author.name").value(book.getAuthor().getName()))
            .andExpect(jsonPath("$.author.email").value(book.getAuthor().getEmail()))
            .andExpect(jsonPath("$.author.description").value(book.getAuthor().getDescription()))
            .andExpect(jsonPath("$.author.createDate").value(
                formatLocalDateTime(book.getAuthor().getCreateDate())))
            .andExpect(jsonPath("$.bookDetails.accessUpdates").value(bookDetails.accessUpdates()))
            .andExpect(jsonPath("$.bookDetails.ebookAndPrintedBookPrice").value(
                bookDetails.ebookAndPrintedBookPrice()))
            .andExpect(jsonPath("$.bookDetails.socialMedia.linkFacebook").value(
                bookDetails.socialMedia().linkFacebook()))
            .andExpect(jsonPath("$.bookDetails.socialMedia.linkTwitter").value(
                bookDetails.socialMedia().linkTwitter()))
            .andExpect(jsonPath("$.bookDetails.socialMedia.iconFacebookUrl").value(
                bookDetails.socialMedia().iconFacebookUrl()))
            .andExpect(jsonPath("$.bookDetails.socialMedia.iconTwitterUrl").value(
                bookDetails.socialMedia().iconTwitterUrl()))
            .andExpect(
                jsonPath("$.publicationDate").value(formatLocalDateTime(book.getPublicationDate())))
            .andExpect(jsonPath("$.createDate").value(formatLocalDateTime(book.getCreateDate())))
            .andExpect(jsonPath("$.updateDate").value(formatLocalDateTime(book.getUpdateDate())));
    }

    @Test
    @DisplayName("Should return not found when there are no details of a book for the given id")
    void test_success_book_details_empty() throws Exception {
        var randomId = UUID.randomUUID();
        mockMvc.perform(get("/v1/books/{id}", randomId)
                .contentType(APPLICATION_JSON))
            .andExpect(status().isNotFound())
            .andExpect(jsonPath("$.errors[0].field").value("bookId"))
            .andExpect(jsonPath("$.errors[0].message").value("Book not found with id: " + randomId))
            .andExpect(jsonPath("$.errors[0].httpStatus").value("NOT_FOUND"))
            .andExpect(jsonPath("$.errors[0].errorCode").value(HttpStatus.NOT_FOUND.value()))
            .andExpect(jsonPath("$.errors[0].timestamp").isNotEmpty());
    }
}

package com.jornada.dev.eficiente.desafio1.integrations.web.controllers.books;

import static com.jornada.dev.eficiente.desafio1.builders.book.BookBuilder.createBookRequest;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.apache.commons.lang3.RandomStringUtils.randomNumeric;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.jornada.dev.eficiente.desafio1.integrations.IntegrationTestAbstract;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.http.HttpStatus;

@DisplayName("POST /v1/books/register")
class SaveBookControllerTest extends IntegrationTestAbstract {

    @Test
    @DisplayName("Should register a new book successfully")
    void registration_NewAuthor_Success() throws Exception {

        var author = authorComponent.createAuthor(
            randomAlphabetic(10),
            randomAlphabetic(10) + "@example.com",
            randomAlphabetic(10));

        var category = categoryComponent.createCategory(randomAlphabetic(10));

        var publicationDate = LocalDateTime.now().plusDays(1);
        var formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        var request = createBookRequest(
            randomAlphabetic(10),
            randomAlphabetic(10),
            randomAlphabetic(10),
            randomAlphabetic(10),
            BigDecimal.valueOf(20.0),
            BigDecimal.valueOf(20.0),
            100L,
            randomNumeric(10),
            LocalDateTime.of(publicationDate.getYear(),
                publicationDate.getMonth(),
                publicationDate.getDayOfMonth(),
                publicationDate.getHour(),
                publicationDate.getMinute(),
                publicationDate.getSecond()),
            category.getName(),
            author.getEmail()
        );

        mockMvc
            .perform(post("/v1/books/register")
                .contentType(APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.title").value(request.title()))
            .andExpect(jsonPath("$.subtitle").value(request.subtitle()))
            .andExpect(jsonPath("$.description").value(request.description()))
            .andExpect(jsonPath("$.ebookPrice").value(request.ebookPrice()))
            .andExpect(jsonPath("$.printedBookPrice").value(request.printedBookPrice()))
            .andExpect(jsonPath("$.numberOfPages").value(request.numberOfPages()))
            .andExpect(jsonPath("$.isbn").value(request.isbn()))
            .andExpect(
                jsonPath("$.publicationDate").value(request.publicationDate().format(formatter)))
            .andExpect(jsonPath("$.category.name").value(category.getName()))
            .andExpect(jsonPath("$.author.name").value(author.getName()))
            .andExpect(jsonPath("$.author.email").value(author.getEmail()))
            .andExpect(jsonPath("$.author.description").value(author.getDescription()));
    }

    @ParameterizedTest
    @MethodSource("provideNullOrEmptyValues")
    @DisplayName("Should return 400 Bad Request when title is missing")
    void registration_MissingOrEmptyTitle_BadRequest(String value) throws Exception {
        var publicationDate = LocalDateTime.now().plusDays(1);
        var request = createBookRequest(
            value,
            randomAlphabetic(10),
            randomAlphabetic(10),
            randomAlphabetic(10),
            BigDecimal.valueOf(20.0),
            BigDecimal.valueOf(20.0),
            100L,
            randomNumeric(10),
            LocalDateTime.of(publicationDate.getYear(),
                publicationDate.getMonth(),
                publicationDate.getDayOfMonth(),
                publicationDate.getHour(),
                publicationDate.getMinute(),
                publicationDate.getSecond()),
            "category.getName()",
            "author@email.com"
        );

        mockMvc.perform(post("/v1/books/register")
                .contentType(APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$.errors[0].field").value("title"))
            .andExpect(jsonPath("$.errors[0].message").value("Title is required"))
            .andExpect(jsonPath("$.errors[0].httpStatus").value("BAD_REQUEST"))
            .andExpect(jsonPath("$.errors[0].errorCode").value(HttpStatus.BAD_REQUEST.value()))
            .andExpect(jsonPath("$.errors[0].timestamp").isNotEmpty());
    }

    @Test
    @DisplayName("Should return 400 Bad Request when title is not unique")
    void registration_NonUniqueTitle_BadRequest() throws Exception {
        var book = dataInitializerComponent.initializeBookData();

        var request = createBookRequest(
            book.getTitle(),
            randomAlphabetic(10),
            randomAlphabetic(10),
            randomAlphabetic(10),
            BigDecimal.valueOf(20.0),
            BigDecimal.valueOf(20.0),
            100L,
            randomNumeric(10),
            book.getPublicationDate(),
            book.getCategory().getName(),
            book.getAuthor().getEmail()
        );

        mockMvc.perform(post("/v1/books/register")
                .contentType(APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$.errors[0].field").value("title"))
            .andExpect(jsonPath("$.errors[0].message").value("Title must be unique"))
            .andExpect(jsonPath("$.errors[0].httpStatus").value("BAD_REQUEST"))
            .andExpect(jsonPath("$.errors[0].errorCode").value(HttpStatus.BAD_REQUEST.value()))
            .andExpect(jsonPath("$.errors[0].timestamp").isNotEmpty());
    }

    @Test
    @DisplayName("Should return 400 Bad Request when ISBN is not unique")
    void registration_NonUniqueIsbn_BadRequest() throws Exception {
        var book = dataInitializerComponent.initializeBookData();

        var request = createBookRequest(
            randomAlphabetic(10),
            randomAlphabetic(10),
            randomAlphabetic(10),
            randomAlphabetic(10),
            BigDecimal.valueOf(20.0),
            BigDecimal.valueOf(20.0),
            100L,
            book.getIsbn(),
            book.getPublicationDate(),
            book.getCategory().getName(),
            book.getAuthor().getEmail()
        );

        mockMvc.perform(post("/v1/books/register")
                .contentType(APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$.errors[0].field").value("isbn"))
            .andExpect(jsonPath("$.errors[0].message").value("ISBN must be unique"))
            .andExpect(jsonPath("$.errors[0].httpStatus").value("BAD_REQUEST"))
            .andExpect(jsonPath("$.errors[0].errorCode").value(HttpStatus.BAD_REQUEST.value()))
            .andExpect(jsonPath("$.errors[0].timestamp").isNotEmpty());
    }

    @ParameterizedTest
    @MethodSource("provideNullOrEmptyValues")
    @DisplayName("Should return 400 Bad Request when summary is missing or empty")
    void registration_MissingOrEmptySummary_BadRequest(String value) throws Exception {
        var publicationDate = LocalDateTime.now().plusDays(1);
        var request = createBookRequest(
            randomAlphabetic(10),
            randomAlphabetic(10),
            randomAlphabetic(10),
            value,
            BigDecimal.valueOf(20.0),
            BigDecimal.valueOf(20.0),
            100L,
            randomNumeric(10),
            LocalDateTime.of(publicationDate.getYear(),
                publicationDate.getMonth(),
                publicationDate.getDayOfMonth(),
                publicationDate.getHour(),
                publicationDate.getMinute(),
                publicationDate.getSecond()),
            "category.getName()",
            "author@email.com"
        );

        mockMvc.perform(post("/v1/books/register")
                .contentType(APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$.errors[0].field").value("summary"))
            .andExpect(jsonPath("$.errors[0].message").value("Summary is required"))
            .andExpect(jsonPath("$.errors[0].httpStatus").value("BAD_REQUEST"))
            .andExpect(jsonPath("$.errors[0].errorCode").value(HttpStatus.BAD_REQUEST.value()))
            .andExpect(jsonPath("$.errors[0].timestamp").isNotEmpty());
    }

    @Test
    @DisplayName("Should return 400 Bad Request when ebookPrice is less than 20")
    void registration_PriceLessThanMinimum_BadRequest() throws Exception {
        var publicationDate = LocalDateTime.now().plusDays(1);
        var request = createBookRequest(
            randomAlphabetic(10),
            randomAlphabetic(10),
            randomAlphabetic(10),
            randomAlphabetic(10),
            BigDecimal.valueOf(19.99),
            BigDecimal.valueOf(20.99),
            100L,
            randomNumeric(10),
            LocalDateTime.of(publicationDate.getYear(),
                publicationDate.getMonth(),
                publicationDate.getDayOfMonth(),
                publicationDate.getHour(),
                publicationDate.getMinute(),
                publicationDate.getSecond()),
            "category.getName()",
            "author@email.com"
        );

        mockMvc.perform(post("/v1/books/register")
                .contentType(APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$.errors[0].field").value("ebookPrice"))
            .andExpect(jsonPath("$.errors[0].message").value("Price must be at least 20"))
            .andExpect(jsonPath("$.errors[0].httpStatus").value("BAD_REQUEST"))
            .andExpect(jsonPath("$.errors[0].errorCode").value(HttpStatus.BAD_REQUEST.value()))
            .andExpect(jsonPath("$.errors[0].timestamp").isNotEmpty());
    }

    @Test
    @DisplayName("Should return 400 Bad Request when numberOfPages is less than 100")
    void registration_NumberOfPagesLessThanMinimum_BadRequest() throws Exception {
        var publicationDate = LocalDateTime.now().plusDays(1);
        var request = createBookRequest(
            randomAlphabetic(10),
            randomAlphabetic(10),
            randomAlphabetic(10),
            randomAlphabetic(10),
            BigDecimal.valueOf(20.0),
            BigDecimal.valueOf(20.0),
            99L, // less than 100
            randomNumeric(10),
            LocalDateTime.of(publicationDate.getYear(),
                publicationDate.getMonth(),
                publicationDate.getDayOfMonth(),
                publicationDate.getHour(),
                publicationDate.getMinute(),
                publicationDate.getSecond()),
            "category.getName()",
            "author@email.com"
        );

        mockMvc.perform(post("/v1/books/register")
                .contentType(APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$.errors[0].field").value("numberOfPages"))
            .andExpect(
                jsonPath("$.errors[0].message").value("Number of pages must be at least 100"))
            .andExpect(jsonPath("$.errors[0].httpStatus").value("BAD_REQUEST"))
            .andExpect(jsonPath("$.errors[0].errorCode").value(HttpStatus.BAD_REQUEST.value()))
            .andExpect(jsonPath("$.errors[0].timestamp").isNotEmpty());
    }

    @Test
    @DisplayName("Should return 400 Bad Request when description exceeds 500")
    void registration_DescriptionHigherThanMaximum_BadRequest() throws Exception {
        var publicationDate = LocalDateTime.now().plusDays(1);
        var request = createBookRequest(
            randomAlphabetic(10),
            randomAlphabetic(501),
            randomAlphabetic(501),
            randomAlphabetic(501),
            BigDecimal.valueOf(20.0),
            BigDecimal.valueOf(20.0),
            100L,
            randomNumeric(10),
            LocalDateTime.of(publicationDate.getYear(),
                publicationDate.getMonth(),
                publicationDate.getDayOfMonth(),
                publicationDate.getHour(),
                publicationDate.getMinute(),
                publicationDate.getSecond()),
            "category.getName()",
            "author@email.com"
        );

        mockMvc.perform(post("/v1/books/register")
                .contentType(APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$.errors[0].field").value("description"))
            .andExpect(
                jsonPath("$.errors[0].message").value("Description cannot exceed 500 characters"))
            .andExpect(jsonPath("$.errors[0].httpStatus").value("BAD_REQUEST"))
            .andExpect(jsonPath("$.errors[0].errorCode").value(HttpStatus.BAD_REQUEST.value()))
            .andExpect(jsonPath("$.errors[0].timestamp").isNotEmpty());
    }

    @Test
    @DisplayName("Should return 400 invalid authorEmail")
    void registration_InvalidAuthorEmail_BadRequest() throws Exception {
        var publicationDate = LocalDateTime.now().plusDays(1);

        var request = createBookRequest(
            randomAlphabetic(10),
            randomAlphabetic(10),
            randomAlphabetic(10),
            randomAlphabetic(10),
            BigDecimal.valueOf(20.0),
            BigDecimal.valueOf(20.0),
            100L,
            randomNumeric(10),
            publicationDate,
            randomAlphabetic(10),
            randomAlphabetic(10)
        );

        mockMvc.perform(post("/v1/books/register")
                .contentType(APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$.errors[0].field").value("authorEmail"))
            .andExpect(jsonPath("$.errors[0].message").value("Invalid email format"))
            .andExpect(jsonPath("$.errors[0].httpStatus").value("BAD_REQUEST"))
            .andExpect(jsonPath("$.errors[0].errorCode").value(HttpStatus.BAD_REQUEST.value()))
            .andExpect(jsonPath("$.errors[0].timestamp").isNotEmpty());
    }

    @ParameterizedTest
    @MethodSource("provideInvalidFuturePublicationDates")
    @DisplayName("Should return 400 Bad Request when publication date is not in the future")
    void registration_PublicationDateNotInFuture_BadRequest(LocalDateTime value) throws Exception {

        var author = authorComponent.createAuthor(
            randomAlphabetic(10),
            randomAlphabetic(10) + "@example.com",
            randomAlphabetic(10));

        var category = categoryComponent.createCategory(randomAlphabetic(10));

        var request = createBookRequest(
            randomAlphabetic(10),
            randomAlphabetic(10),
            randomAlphabetic(10),
            randomAlphabetic(10),
            BigDecimal.valueOf(20.0),
            BigDecimal.valueOf(20.0),
            100L,
            randomNumeric(10),
            LocalDateTime.of(value.getYear(),
                value.getMonth(),
                value.getDayOfMonth(),
                value.getHour(),
                value.getMinute(),
                value.getSecond()),
            category.getName(),
            author.getEmail()
        );

        mockMvc
            .perform(post("/v1/books/register")
                .contentType(APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$.errors[0].field").value("publicationDate"))
            .andExpect(
                jsonPath("$.errors[0].message").value("Publication date must be in the future"))
            .andExpect(jsonPath("$.errors[0].httpStatus").value("BAD_REQUEST"))
            .andExpect(jsonPath("$.errors[0].errorCode").value(HttpStatus.BAD_REQUEST.value()))
            .andExpect(jsonPath("$.errors[0].timestamp").isNotEmpty());
    }

    @Test
    @DisplayName("Should return 404 Not Found when author is not found")
    void registration_NonExistentAuthor_NotFound() throws Exception {
        var publicationDate = LocalDateTime.now().plusDays(1);

        var request = createBookRequest(
            randomAlphabetic(10),
            randomAlphabetic(10),
            randomAlphabetic(10),
            randomAlphabetic(10),
            BigDecimal.valueOf(20.0),
            BigDecimal.valueOf(20.0),
            100L,
            randomNumeric(10),
            publicationDate,
            randomAlphabetic(10),
            randomAlphabetic(10) + "@example.com"
        );

        mockMvc.perform(post("/v1/books/register")
                .contentType(APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
            .andExpect(status().isNotFound())
            .andExpect(jsonPath("$.errors[0].field").value("authorEmail"))
            .andExpect(jsonPath("$.errors[0].message").value(
                "Author not found with email: " + request.authorEmail()))
            .andExpect(jsonPath("$.errors[0].httpStatus").value("NOT_FOUND"))
            .andExpect(jsonPath("$.errors[0].errorCode").value(HttpStatus.NOT_FOUND.value()))
            .andExpect(jsonPath("$.errors[0].timestamp").isNotEmpty());
    }

    @Test
    @DisplayName("Should return 404 Not Found when category is not found")
    void registration_NonExistentCategory_NotFound() throws Exception {
        var author = authorComponent.createAuthor(
            randomAlphabetic(10),
            randomAlphabetic(10) + "@example.com",
            randomAlphabetic(10));

        var publicationDate = LocalDateTime.now().plusDays(1);

        var request = createBookRequest(
            randomAlphabetic(10),
            randomAlphabetic(10),
            randomAlphabetic(10),
            randomAlphabetic(10),
            BigDecimal.valueOf(20.0),
            BigDecimal.valueOf(20.0),
            100L,
            randomNumeric(10),
            publicationDate,
            randomAlphabetic(10),
            author.getEmail()
        );

        mockMvc.perform(post("/v1/books/register")
                .contentType(APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
            .andExpect(status().isNotFound())
            .andExpect(jsonPath("$.errors[0].field").value("categoryName"))
            .andExpect(jsonPath("$.errors[0].message").value(
                "Category not found with name: " + request.categoryName()))
            .andExpect(jsonPath("$.errors[0].httpStatus").value("NOT_FOUND"))
            .andExpect(jsonPath("$.errors[0].errorCode").value(HttpStatus.NOT_FOUND.value()))
            .andExpect(jsonPath("$.errors[0].timestamp").isNotEmpty());
    }

    private static Stream<Arguments> provideNullOrEmptyValues() {
        return Stream.of(
            Arguments.of((String) null),
            Arguments.of("")
        );
    }

    private static Stream<LocalDateTime> provideInvalidFuturePublicationDates() {
        return Stream.of(
            LocalDateTime.now().minusDays(1),
            LocalDateTime.now()
        );
    }
}

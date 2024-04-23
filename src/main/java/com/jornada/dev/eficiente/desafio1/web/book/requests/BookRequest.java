package com.jornada.dev.eficiente.desafio1.web.book.requests;

import static com.jornada.dev.eficiente.desafio1.domains.enuns.UniqueType.BOOK_ISBN;
import static com.jornada.dev.eficiente.desafio1.domains.enuns.UniqueType.BOOK_TITLE;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jornada.dev.eficiente.desafio1.domains.configuration.annotations.Unique;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Builder;

@Builder
public record BookRequest(@NotBlank(message = "Title is required")
                          @Unique(value = BOOK_TITLE, message = "Title must be unique")
                          String title,

                          @NotBlank(message = "SubTitle is required")
                          String subtitle,

                          @NotBlank(message = "Description is required")
                          @Size(max = 500, message = "Description cannot exceed 500 characters")
                          String description,

                          @NotBlank(message = "Summary is required")
                          String summary,

                          @NotNull(message = "Price is required")
                          @DecimalMin(value = "20", message = "Price must be at least 20")
                          BigDecimal ebookPrice,

                          @NotNull(message = "Price is required")
                          @DecimalMin(value = "20", message = "Price must be at least 20")
                          BigDecimal printedBookPrice,

                          @NotNull(message = "Number of pages is required")
                          @Min(value = 100, message = "Number of pages must be at least 100")
                          Long numberOfPages,

                          @NotBlank(message = "ISBN is required")
                          @Unique(value = BOOK_ISBN, message = "ISBN must be unique")
                          String isbn,

                          @Future(message = "Publication date must be in the future")
                          @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
                          LocalDateTime publicationDate,

                          @NotNull(message = "Category is required")
                          @NotBlank(message = "Category is required")
                          String categoryName,

                          @NotNull(message = "Author is required")
                          @NotBlank(message = "Author is required")
                          @Email(message = "Invalid email format")
                          String authorEmail) {
}

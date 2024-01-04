package com.jornada.dev.eficiente.desafio1.web.requests;

import static com.jornada.dev.eficiente.desafio1.domains.enuns.UniqueType.BOOK_ISBN;
import static com.jornada.dev.eficiente.desafio1.domains.enuns.UniqueType.BOOK_TITLE;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jornada.dev.eficiente.desafio1.domains.annotations.Unique;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Builder;

@Builder
public record BookRequest(@NotBlank(message = "Name is required")
                          @Unique(value = BOOK_TITLE, message = "Title must be unique")
                          String title,

                          @NotBlank(message = "Description is required")
                          @Size(max = 500, message = "Description cannot exceed 500 characters")
                          String description,

                          @NotBlank(message = "Summary is required")
                          String summary,

                          @NotNull(message = "Price is required")
                          @DecimalMax(value = "20", message = "Price cannot exceed 20")
                          BigDecimal price,

                          @NotNull(message = "Number of pages is required")
                          @Max(value = 100, message = "Number of pages must be at least 100")
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
                          String authorEmail) {
}

package com.jornada.dev.eficiente.desafio1.web.responses;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@JsonInclude(NON_NULL)
public record BookResponse(String title,
                           String subtitle,
                           String description,
                           String summary,
                           BigDecimal ebookPrice,
                           BigDecimal printedBookPrice,
                           Long numberOfPages,
                           String isbn,
                           CategoryResponse category,
                           AuthorResponse author,
                           BookDetailsResponse bookDetails,

                           @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
                           LocalDateTime publicationDate,

                           @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
                           LocalDateTime createDate,

                           @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
                           LocalDateTime updateDate) {
}

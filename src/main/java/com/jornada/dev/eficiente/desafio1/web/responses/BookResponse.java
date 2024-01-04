package com.jornada.dev.eficiente.desafio1.web.responses;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jornada.dev.eficiente.desafio1.domains.entities.AuthorEntity;
import com.jornada.dev.eficiente.desafio1.domains.entities.CategoryEntity;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public record BookResponse(String title,
                           String description,
                           String summary,
                           BigDecimal price,
                           Long numberOfPages,
                           String isbn,
                           CategoryEntity category,
                           AuthorEntity author,

                           @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
                           LocalDateTime publicationDate,

                           @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
                           LocalDateTime createDate,

                           @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
                           LocalDateTime updateDate) {
}

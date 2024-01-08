package com.jornada.dev.eficiente.desafio1.domains.dtos;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.Builder;

@Builder(toBuilder = true)
public record BookDto(UUID id,
                      String title,
                      String description,
                      String summary,
                      BigDecimal price,
                      Long numberOfPages,
                      String isbn,
                      LocalDateTime publicationDate,
                      CategoryDto category,
                      AuthorDto author,
                      LocalDateTime createDate,
                      LocalDateTime updateDate) {
    public BookDto update(AuthorDto author, CategoryDto category) {
        return toBuilder().author(author).category(category).build();
    }
}

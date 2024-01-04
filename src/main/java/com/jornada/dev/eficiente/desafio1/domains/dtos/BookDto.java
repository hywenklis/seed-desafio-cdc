package com.jornada.dev.eficiente.desafio1.domains.dtos;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Builder;

@Builder(toBuilder = true)
public record BookDto(String title,
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
    public BookDto update(AuthorDto author) {
        AuthorDto updatedAuthor = AuthorDto.builder()
            .id(author.id())
            .name(author.name())
            .email(author.email())
            .description(author.description())
            .createDate(author.createDate())
            .updateDate(author.updateDate())
            .build();

        return toBuilder().author(updatedAuthor).build();
    }

    public BookDto update(CategoryDto category) {
        CategoryDto updatedCategory = CategoryDto.builder()
            .id(category.id())
            .name(category.name())
            .createDate(category.createDate())
            .updateDate(category.updateDate())
            .build();

        return toBuilder().category(updatedCategory).build();
    }
}

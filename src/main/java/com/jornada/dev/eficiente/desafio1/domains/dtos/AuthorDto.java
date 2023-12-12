package com.jornada.dev.eficiente.desafio1.domains.dtos;

import java.time.LocalDateTime;

public record AuthorDto(String name,
                        String email,
                        String description,
                        LocalDateTime createDate,
                        LocalDateTime updateDate) {

}

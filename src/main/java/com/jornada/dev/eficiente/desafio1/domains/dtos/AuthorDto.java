package com.jornada.dev.eficiente.desafio1.domains.dtos;

import java.time.LocalDateTime;
import lombok.Builder;

@Builder
public record AuthorDto(String name,
                        String email,
                        String description,
                        LocalDateTime createDate,
                        LocalDateTime updateDate) { }

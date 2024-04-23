package com.jornada.dev.eficiente.desafio1.domains.author.dtos;

import java.time.LocalDateTime;
import java.util.UUID;
import lombok.Builder;

@Builder
public record AuthorDto(
                        UUID id,
                        String name,
                        String email,
                        String description,
                        LocalDateTime createDate,
                        LocalDateTime updateDate) { }

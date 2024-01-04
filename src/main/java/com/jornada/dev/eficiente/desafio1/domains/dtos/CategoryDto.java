package com.jornada.dev.eficiente.desafio1.domains.dtos;

import java.time.LocalDateTime;
import java.util.UUID;
import lombok.Builder;

@Builder
public record CategoryDto(UUID id,
                          String name,
                          LocalDateTime createDate,
                          LocalDateTime updateDate) {
}

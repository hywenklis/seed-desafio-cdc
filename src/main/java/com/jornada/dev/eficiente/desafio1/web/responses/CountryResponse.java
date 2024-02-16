package com.jornada.dev.eficiente.desafio1.web.responses;

import java.time.LocalDateTime;
import java.util.UUID;

public record CountryResponse(UUID id,
                             String name,
                             LocalDateTime createDate,
                             LocalDateTime updateDate) {
}

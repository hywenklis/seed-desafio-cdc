package com.jornada.dev.eficiente.desafio1.web.responses;

import java.time.LocalDateTime;
import java.util.UUID;

public record StateResponse(UUID id,
                            String name,
                            CountryResponse country,
                            LocalDateTime createDate,
                            LocalDateTime updateDate) {
}
package com.jornada.dev.eficiente.desafio1.web.responses;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.time.LocalDateTime;
import java.util.UUID;

@JsonInclude(NON_NULL)
public record StateResponse(UUID id,
                            String name,
                            CountryResponse country,
                            LocalDateTime createDate,
                            LocalDateTime updateDate) {
}
package com.jornada.dev.eficiente.desafio1.web.address.country.responses;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.time.LocalDateTime;
import java.util.UUID;

@JsonInclude(NON_NULL)
public record CountryResponse(UUID id,
                             String name,
                             LocalDateTime createDate,
                             LocalDateTime updateDate) {
}

package com.jornada.dev.eficiente.desafio1.domains.dtos;

import java.time.LocalDateTime;
import java.util.Locale;
import java.util.UUID;
import lombok.Builder;

@Builder(toBuilder = true)
public record CountryDto(UUID id,
                         String name,
                         LocalDateTime createDate,
                         LocalDateTime updateDate) {

    public CountryDto update() {
        return toBuilder().name(name.toLowerCase(Locale.ROOT)).build();
    }
}

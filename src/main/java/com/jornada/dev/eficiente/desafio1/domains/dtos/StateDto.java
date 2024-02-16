package com.jornada.dev.eficiente.desafio1.domains.dtos;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;
import lombok.Builder;

@Builder(toBuilder = true)
public record StateDto(UUID id,
                       String name,
                       CountryDto country,
                       LocalDateTime createDate,
                       LocalDateTime updateDate) {

    public StateDto update(CountryDto country) {
        return toBuilder().country(country).build();
    }
}

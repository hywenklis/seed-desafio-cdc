package com.jornada.dev.eficiente.desafio1.domains.address.state.dtos;

import com.jornada.dev.eficiente.desafio1.domains.address.country.dtos.CountryDto;
import java.time.LocalDateTime;
import java.util.Locale;
import java.util.UUID;
import lombok.Builder;

@Builder(toBuilder = true)
public record StateDto(UUID id,
                       String name,
                       CountryDto country,
                       LocalDateTime createDate,
                       LocalDateTime updateDate) {

    public StateDto update(CountryDto country) {
        return toBuilder()
            .name(name.toLowerCase(Locale.ROOT))
            .country(country).build();
    }
}

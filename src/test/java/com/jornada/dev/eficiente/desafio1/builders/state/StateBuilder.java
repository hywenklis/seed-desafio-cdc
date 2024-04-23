package com.jornada.dev.eficiente.desafio1.builders.state;

import com.jornada.dev.eficiente.desafio1.domains.address.country.dtos.CountryDto;
import com.jornada.dev.eficiente.desafio1.domains.address.country.entities.CountryEntity;
import com.jornada.dev.eficiente.desafio1.domains.address.state.dtos.StateDto;
import com.jornada.dev.eficiente.desafio1.domains.address.state.entities.StateEntity;
import com.jornada.dev.eficiente.desafio1.web.address.country.requests.CountryRequest;
import com.jornada.dev.eficiente.desafio1.web.address.state.requests.StateRequest;
import java.time.LocalDateTime;
import java.util.Locale;
import java.util.UUID;

public class StateBuilder {

    public static StateRequest createStateRequest(String name,
                                                  CountryRequest countryRequest) {
        return StateRequest.builder()
            .name(name)
            .country(countryRequest)
            .build();

    }

    public static StateDto createStateDto(String name,
                                          CountryDto country) {
        return StateDto.builder()
            .name(name.toLowerCase(Locale.ROOT))
            .country(country)
            .createDate(LocalDateTime.now())
            .updateDate(LocalDateTime.now())
            .build();
    }

    public static StateEntity createStateEntity(String name,
                                                CountryEntity country) {
        return StateEntity.builder()
            .id(UUID.randomUUID())
            .name(name.toLowerCase(Locale.ROOT))
            .country(country)
            .createDate(LocalDateTime.now())
            .updateDate(LocalDateTime.now())
            .build();
    }
}

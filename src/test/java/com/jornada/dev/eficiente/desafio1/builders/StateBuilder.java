package com.jornada.dev.eficiente.desafio1.builders;

import com.jornada.dev.eficiente.desafio1.domains.dtos.CountryDto;
import com.jornada.dev.eficiente.desafio1.domains.dtos.StateDto;
import com.jornada.dev.eficiente.desafio1.domains.entities.CountryEntity;
import com.jornada.dev.eficiente.desafio1.domains.entities.StateEntity;
import com.jornada.dev.eficiente.desafio1.web.requests.CountryRequest;
import com.jornada.dev.eficiente.desafio1.web.requests.StateRequest;
import java.time.LocalDateTime;
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
            .name(name)
            .country(country)
            .createDate(LocalDateTime.now())
            .updateDate(LocalDateTime.now())
            .build();
    }

    public static StateEntity createStateEntity(String name,
                                                 CountryEntity country) {
        return StateEntity.builder()
            .id(UUID.randomUUID())
            .name(name)
            .country(country)
            .createDate(LocalDateTime.now())
            .updateDate(LocalDateTime.now())
            .build();
    }
}

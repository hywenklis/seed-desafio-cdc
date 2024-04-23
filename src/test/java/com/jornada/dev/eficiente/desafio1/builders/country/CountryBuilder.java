package com.jornada.dev.eficiente.desafio1.builders.country;

import com.jornada.dev.eficiente.desafio1.domains.address.country.dtos.CountryDto;
import com.jornada.dev.eficiente.desafio1.domains.address.country.entities.CountryEntity;
import com.jornada.dev.eficiente.desafio1.web.address.country.requests.CountryRequest;
import java.time.LocalDateTime;
import java.util.Locale;
import java.util.UUID;

public class CountryBuilder {

    public static CountryRequest createCountryRequest(String name) {
        return CountryRequest.builder()
            .name(name)
            .build();

    }

    public static CountryDto createCountryDto(String name) {
        return CountryDto.builder()
            .name(name.toLowerCase(Locale.ROOT))
            .createDate(LocalDateTime.now())
            .updateDate(LocalDateTime.now())
            .build();
    }

    public static CountryEntity createCountryEntity(String name) {
        return CountryEntity.builder()
            .id(UUID.randomUUID())
            .name(name.toLowerCase(Locale.ROOT))
            .createDate(LocalDateTime.now())
            .updateDate(LocalDateTime.now())
            .build();
    }
}

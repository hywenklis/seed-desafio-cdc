package com.jornada.dev.eficiente.desafio1.web.address.country.mappers;

import com.jornada.dev.eficiente.desafio1.domains.address.country.dtos.CountryDto;
import com.jornada.dev.eficiente.desafio1.web.address.country.requests.CountryRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CountryMapperRequest {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "updateDate", ignore = true)
    @Mapping(target = "createDate", ignore = true)
    CountryDto mapToDto(CountryRequest countryRequest);
}

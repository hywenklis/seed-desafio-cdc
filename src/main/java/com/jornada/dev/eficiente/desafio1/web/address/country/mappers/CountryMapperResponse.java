package com.jornada.dev.eficiente.desafio1.web.address.country.mappers;

import com.jornada.dev.eficiente.desafio1.domains.address.country.dtos.CountryDto;
import com.jornada.dev.eficiente.desafio1.web.address.country.responses.CountryResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CountryMapperResponse {

    CountryResponse mapToDto(CountryDto countryDto);
}

package com.jornada.dev.eficiente.desafio1.web.mappers.country;

import com.jornada.dev.eficiente.desafio1.domains.dtos.CategoryDto;
import com.jornada.dev.eficiente.desafio1.domains.dtos.CountryDto;
import com.jornada.dev.eficiente.desafio1.web.responses.CategoryResponse;
import com.jornada.dev.eficiente.desafio1.web.responses.CountryResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CountryMapperResponse {

    CountryResponse mapToDto(CountryDto countryDto);
}

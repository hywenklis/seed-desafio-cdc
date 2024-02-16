package com.jornada.dev.eficiente.desafio1.web.mappers.country;

import com.jornada.dev.eficiente.desafio1.domains.dtos.CategoryDto;
import com.jornada.dev.eficiente.desafio1.domains.dtos.CountryDto;
import com.jornada.dev.eficiente.desafio1.web.requests.CategoryRequest;
import com.jornada.dev.eficiente.desafio1.web.requests.CountryRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CountryMapperRequest {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "updateDate", ignore = true)
    @Mapping(target = "createDate", ignore = true)
    CountryDto mapToDto(CountryRequest countryRequest);
}

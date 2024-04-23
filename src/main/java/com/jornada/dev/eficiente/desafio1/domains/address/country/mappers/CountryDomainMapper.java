package com.jornada.dev.eficiente.desafio1.domains.address.country.mappers;

import com.jornada.dev.eficiente.desafio1.domains.address.country.dtos.CountryDto;
import com.jornada.dev.eficiente.desafio1.domains.address.country.entities.CountryEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CountryDomainMapper {

    CountryDto mapToDto(CountryEntity countryEntity);

    CountryEntity mapToEntity(CountryDto countryDto);
}

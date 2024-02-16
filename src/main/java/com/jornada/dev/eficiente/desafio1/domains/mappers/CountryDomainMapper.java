package com.jornada.dev.eficiente.desafio1.domains.mappers;

import com.jornada.dev.eficiente.desafio1.domains.dtos.CategoryDto;
import com.jornada.dev.eficiente.desafio1.domains.dtos.CountryDto;
import com.jornada.dev.eficiente.desafio1.domains.entities.CategoryEntity;
import com.jornada.dev.eficiente.desafio1.domains.entities.CountryEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CountryDomainMapper {

    CountryDto mapToDto(CountryEntity countryEntity);

    CountryEntity mapToEntity(CountryDto countryDto);
}

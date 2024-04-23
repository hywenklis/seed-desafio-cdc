package com.jornada.dev.eficiente.desafio1.domains.address.country.services;

import com.jornada.dev.eficiente.desafio1.domains.address.country.dtos.CountryDto;

public interface CountryRegistrationService {
    CountryDto save(CountryDto countryDto);
}

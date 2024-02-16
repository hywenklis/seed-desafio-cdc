package com.jornada.dev.eficiente.desafio1.domains.services;

import com.jornada.dev.eficiente.desafio1.domains.dtos.CountryDto;

public interface CountryRegistrationService {
    CountryDto save(CountryDto countryDto);
}

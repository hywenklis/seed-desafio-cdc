package com.jornada.dev.eficiente.desafio1.domains.address.country.services;

import com.jornada.dev.eficiente.desafio1.domains.address.country.dtos.CountryDto;
import java.util.Optional;

public interface CountryFindService {
    Optional<CountryDto> findCountryByName(String name);
}

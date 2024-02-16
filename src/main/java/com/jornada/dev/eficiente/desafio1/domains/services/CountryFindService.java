package com.jornada.dev.eficiente.desafio1.domains.services;

import com.jornada.dev.eficiente.desafio1.domains.dtos.CategoryDto;
import com.jornada.dev.eficiente.desafio1.domains.dtos.CountryDto;
import java.util.Optional;

public interface CountryFindService {
    Optional<CountryDto> findCountryByName(String name);
}

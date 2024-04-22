package com.jornada.dev.eficiente.desafio1.components;

import com.jornada.dev.eficiente.desafio1.builders.CountryBuilder;
import com.jornada.dev.eficiente.desafio1.domains.entities.CountryEntity;
import com.jornada.dev.eficiente.desafio1.domains.repositories.CountryRepository;
import java.util.Locale;
import org.springframework.stereotype.Component;

@Component
public class CountryComponent {

    private final CountryRepository countryRepository;

    public CountryComponent(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    public CountryEntity createCountry(final String name) {
        return countryRepository.save(
            CountryBuilder.createCountryEntity(name.toLowerCase(Locale.ROOT)));
    }
}

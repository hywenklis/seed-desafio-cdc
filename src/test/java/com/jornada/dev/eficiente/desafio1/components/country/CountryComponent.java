package com.jornada.dev.eficiente.desafio1.components.country;

import com.jornada.dev.eficiente.desafio1.builders.country.CountryBuilder;
import com.jornada.dev.eficiente.desafio1.domains.address.country.entities.CountryEntity;
import com.jornada.dev.eficiente.desafio1.domains.address.country.repositories.CountryRepository;
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

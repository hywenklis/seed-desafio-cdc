package com.jornada.dev.eficiente.desafio1.domains.address.country.services.impls;

import com.jornada.dev.eficiente.desafio1.domains.address.country.dtos.CountryDto;
import com.jornada.dev.eficiente.desafio1.domains.address.country.mappers.CountryDomainMapper;
import com.jornada.dev.eficiente.desafio1.domains.address.country.repositories.CountryRepository;
import com.jornada.dev.eficiente.desafio1.domains.address.country.services.CountryFindService;
import java.util.Locale;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CountryFindServiceImpl implements CountryFindService {

    private final CountryRepository countryRepository;
    private final CountryDomainMapper mapper;

    @Override
    public Optional<CountryDto> findCountryByName(String name) {
        return countryRepository
            .findByName(name.toLowerCase(Locale.ROOT))
            .map(mapper::mapToDto);
    }
}

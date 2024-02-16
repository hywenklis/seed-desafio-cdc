package com.jornada.dev.eficiente.desafio1.domains.services.impls;

import com.jornada.dev.eficiente.desafio1.domains.dtos.CountryDto;
import com.jornada.dev.eficiente.desafio1.domains.mappers.CountryDomainMapper;
import com.jornada.dev.eficiente.desafio1.domains.repositories.CountryRepository;
import com.jornada.dev.eficiente.desafio1.domains.services.CountryFindService;
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
        return countryRepository.findByName(name).map(mapper::mapToDto);
    }
}

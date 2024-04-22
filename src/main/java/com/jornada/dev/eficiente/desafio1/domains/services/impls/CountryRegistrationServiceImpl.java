package com.jornada.dev.eficiente.desafio1.domains.services.impls;

import com.jornada.dev.eficiente.desafio1.domains.dtos.CountryDto;
import com.jornada.dev.eficiente.desafio1.domains.entities.CountryEntity;
import com.jornada.dev.eficiente.desafio1.domains.mappers.CountryDomainMapper;
import com.jornada.dev.eficiente.desafio1.domains.repositories.CountryRepository;
import com.jornada.dev.eficiente.desafio1.domains.services.CountryRegistrationService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CountryRegistrationServiceImpl implements CountryRegistrationService {

    private final CountryRepository countryRepository;
    private final CountryDomainMapper mapper;

    @Override
    @Transactional
    public CountryDto save(CountryDto countryDto) {
        CountryEntity countryEntity = createCountryEntity(countryDto);
        CountryEntity savedCountry = countryRepository.save(countryEntity);
        return mapper.mapToDto(savedCountry);
    }

    private CountryEntity createCountryEntity(CountryDto countryDto) {
        return mapper.mapToEntity(countryDto.update());
    }

}

package com.jornada.dev.eficiente.desafio1.domains.services.impls;

import com.jornada.dev.eficiente.desafio1.domains.dtos.CountryDto;
import com.jornada.dev.eficiente.desafio1.domains.dtos.StateDto;
import com.jornada.dev.eficiente.desafio1.domains.entities.StateEntity;
import com.jornada.dev.eficiente.desafio1.domains.exceptions.NotFoundException;
import com.jornada.dev.eficiente.desafio1.domains.mappers.StateDomainMapper;
import com.jornada.dev.eficiente.desafio1.domains.repositories.StateRepository;
import com.jornada.dev.eficiente.desafio1.domains.services.CountryFindService;
import com.jornada.dev.eficiente.desafio1.domains.services.StateRegistrationService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StateRegistrationServiceImpl implements StateRegistrationService {

    private final CountryFindService countryFindService;
    private final StateRepository stateRepository;
    private final StateDomainMapper mapper;

    @Override
    @Transactional
    public StateDto save(StateDto stateDto) {
        CountryDto country = findCountry(stateDto);
        StateEntity stateEntity = createStateEntity(stateDto, country);
        StateEntity savedState = stateRepository.save(stateEntity);
        return mapper.mapToDto(savedState);
    }

    private StateEntity createStateEntity(StateDto stateDto, CountryDto countryDto) {
        return mapper.mapToEntity(stateDto.update(countryDto));
    }

    private CountryDto findCountry(StateDto stateDto) {
        return countryFindService.findCountryByName(stateDto.country().name())
            .orElseThrow(() -> new NotFoundException(
                "countryName",
                "Country not found with name: " + stateDto.country().name()
            ));
    }
}

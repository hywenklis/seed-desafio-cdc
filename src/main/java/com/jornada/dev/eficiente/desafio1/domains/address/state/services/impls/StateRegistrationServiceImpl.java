package com.jornada.dev.eficiente.desafio1.domains.address.state.services.impls;

import com.jornada.dev.eficiente.desafio1.domains.address.country.dtos.CountryDto;
import com.jornada.dev.eficiente.desafio1.domains.address.country.services.CountryFindService;
import com.jornada.dev.eficiente.desafio1.domains.address.state.dtos.StateDto;
import com.jornada.dev.eficiente.desafio1.domains.address.state.entities.StateEntity;
import com.jornada.dev.eficiente.desafio1.domains.address.state.mappers.StateDomainMapper;
import com.jornada.dev.eficiente.desafio1.domains.address.state.repositories.StateRepository;
import com.jornada.dev.eficiente.desafio1.domains.address.state.services.StateRegistrationService;
import com.jornada.dev.eficiente.desafio1.domains.exceptions.NotFoundException;
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

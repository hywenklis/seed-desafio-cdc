package com.jornada.dev.eficiente.desafio1.units.domains.services.impls.address.state;

import static com.jornada.dev.eficiente.desafio1.builders.country.CountryBuilder.createCountryDto;
import static com.jornada.dev.eficiente.desafio1.builders.country.CountryBuilder.createCountryEntity;
import static com.jornada.dev.eficiente.desafio1.builders.state.StateBuilder.createStateDto;
import static com.jornada.dev.eficiente.desafio1.builders.state.StateBuilder.createStateEntity;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.jornada.dev.eficiente.desafio1.domains.address.country.services.CountryFindService;
import com.jornada.dev.eficiente.desafio1.domains.address.state.dtos.StateDto;
import com.jornada.dev.eficiente.desafio1.domains.address.state.mappers.StateDomainMapper;
import com.jornada.dev.eficiente.desafio1.domains.address.state.repositories.StateRepository;
import com.jornada.dev.eficiente.desafio1.domains.address.state.services.impls.StateRegistrationServiceImpl;
import com.jornada.dev.eficiente.desafio1.domains.exceptions.NotFoundException;
import com.jornada.dev.eficiente.desafio1.units.UnitTestAbstract;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

class StateRegistrationServiceTest extends UnitTestAbstract {

    @Mock
    private CountryFindService countryFindService;

    @Mock
    private StateRepository stateRepository;

    @Mock
    private StateDomainMapper mapper;

    @InjectMocks
    private StateRegistrationServiceImpl stateRegistrationService;

    @Test
    @DisplayName("Should return success when registering "
        + "an state thate does not exist in the database")
    void save_state_success() {
        // Given
        var countryDto = createCountryDto(randomAlphabetic(10));
        var countryEntity = createCountryEntity(randomAlphabetic(10));
        var stateDto = createStateDto(randomAlphabetic(10), countryDto);
        var stateEntity = createStateEntity(randomAlphabetic(10), countryEntity);

        // Mock
        when(mapper.mapToEntity(stateDto)).thenReturn(stateEntity);
        when(mapper.mapToDto(stateEntity)).thenReturn(stateDto);
        when(countryFindService.findCountryByName(stateDto.country().name()))
            .thenReturn(Optional.of(countryDto));
        when(stateRepository.save(stateEntity)).thenReturn(stateEntity);

        // When
        StateDto stateSaved = stateRegistrationService.save(stateDto);

        // Then
        assertThat(stateSaved).isNotNull();
        assertThat(stateSaved.name()).isEqualTo(stateDto.name());
        assertThat(stateSaved.country()).isEqualTo(countryDto);
        assertThat(stateSaved.createDate()).isEqualTo(stateDto.createDate());
        assertThat(stateSaved.updateDate()).isEqualTo(stateDto.updateDate());

        // Verify
        verify(stateRepository, times(1)).save(stateEntity);
    }

    @Test
    @DisplayName("Should not register a state "
        + "if you cannot find the country registered in the database")
    void save_state_failure() {
        // Given
        var countryDto = createCountryDto(randomAlphabetic(10));
        var countryEntity = createCountryEntity(randomAlphabetic(10));
        var stateDto = createStateDto(randomAlphabetic(10), countryDto);
        var stateEntity = createStateEntity(randomAlphabetic(10), countryEntity);

        // Mock
        when(countryFindService.findCountryByName(stateDto.country().name()))
            .thenReturn(Optional.empty());

        // When/Then
        assertThatThrownBy(() -> stateRegistrationService.save(stateDto))
            .isInstanceOf(NotFoundException.class)
            .hasMessageContaining("Country not found with name: " + stateDto.country().name());

        // Verify
        verify(stateRepository, times(0)).save(stateEntity);
    }
}

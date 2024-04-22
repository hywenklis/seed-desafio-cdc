package com.jornada.dev.eficiente.desafio1.units.domains.services.impls;

import static com.jornada.dev.eficiente.desafio1.builders.CountryBuilder.createCountryDto;
import static com.jornada.dev.eficiente.desafio1.builders.CountryBuilder.createCountryEntity;
import static com.jornada.dev.eficiente.desafio1.builders.StateBuilder.createStateDto;
import static com.jornada.dev.eficiente.desafio1.builders.StateBuilder.createStateEntity;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.jornada.dev.eficiente.desafio1.domains.dtos.StateDto;
import com.jornada.dev.eficiente.desafio1.domains.mappers.StateDomainMapper;
import com.jornada.dev.eficiente.desafio1.domains.repositories.StateRepository;
import com.jornada.dev.eficiente.desafio1.domains.services.impls.StateFindServiceImpl;
import com.jornada.dev.eficiente.desafio1.units.UnitTestAbstract;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

class StateFindServiceTest extends UnitTestAbstract {

    @Mock
    private StateRepository stateRepository;

    @Mock
    private StateDomainMapper mapper;

    @InjectMocks
    private StateFindServiceImpl stateFindService;

    @Test
    @DisplayName("Should return success when finding an state in the database")
    void shouldReturnState_WhenFoundInDatabase() {
        // Given
        var countryDto = createCountryDto(randomAlphabetic(10));
        var countryEntity = createCountryEntity(randomAlphabetic(10));
        var stateDto = createStateDto(randomAlphabetic(10), countryDto);
        var stateEntity = createStateEntity(randomAlphabetic(10), countryEntity);

        when(mapper.mapToDto(stateEntity)).thenReturn(stateDto);
        when(stateRepository.findByName(stateDto.name())).thenReturn(Optional.of(stateEntity));

        // When
        Optional<StateDto> state = stateFindService.findStateByName(stateDto.name());

        // Then
        assertThat(state).isPresent();
        assertThat(state.get().id()).isEqualTo(stateDto.id());
        assertThat(state.get().name()).isEqualTo(stateDto.name());
        assertThat(state.get().country()).isEqualTo(countryDto);
        assertThat(state.get().createDate()).isEqualTo(stateDto.createDate());
        assertThat(state.get().updateDate()).isEqualTo(stateDto.updateDate());

        // Verify
        verify(stateRepository, times(1)).findByName(stateDto.name());
    }

    @Test
    @DisplayName("Should Return empty when you cannot find an state in the database")
    void shouldReturnEmpty_WhenStateNotFoundInDatabase() {
        // Given
        var countryDto = createCountryDto(randomAlphabetic(10));
        var stateDto = createStateDto(randomAlphabetic(10), countryDto);

        when(stateRepository.findByName(stateDto.name())).thenReturn(Optional.empty());

        // When
        Optional<StateDto> state = stateFindService.findStateByName(stateDto.name());

        // then
        assertThat(state).isEmpty();

        // Verify
        verify(stateRepository, times(1)).findByName(stateDto.name());
    }
}

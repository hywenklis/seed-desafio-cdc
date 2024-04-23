package com.jornada.dev.eficiente.desafio1.units.domains.services.impls.address.country;

import static com.jornada.dev.eficiente.desafio1.builders.country.CountryBuilder.createCountryDto;
import static com.jornada.dev.eficiente.desafio1.builders.country.CountryBuilder.createCountryEntity;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.jornada.dev.eficiente.desafio1.domains.address.country.dtos.CountryDto;
import com.jornada.dev.eficiente.desafio1.domains.address.country.mappers.CountryDomainMapper;
import com.jornada.dev.eficiente.desafio1.domains.address.country.repositories.CountryRepository;
import com.jornada.dev.eficiente.desafio1.domains.address.country.services.impls.CountryFindServiceImpl;
import com.jornada.dev.eficiente.desafio1.units.UnitTestAbstract;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

class CountryFindServiceTest extends UnitTestAbstract {

    @Mock
    private CountryRepository countryRepository;

    @Mock
    private CountryDomainMapper mapper;

    @InjectMocks
    private CountryFindServiceImpl countryFindService;

    @Test
    @DisplayName("Should return success when finding an country in the database")
    void shouldReturnCountry_WhenFoundInDatabase() {
        // Given
        var countryDto = createCountryDto(randomAlphabetic(10));
        var countryEntity = createCountryEntity(countryDto.name());

        when(mapper.mapToDto(countryEntity)).thenReturn(countryDto);
        when(countryRepository.findByName(countryDto.name())).thenReturn(
            Optional.of(countryEntity));

        // When
        Optional<CountryDto> country = countryFindService.findCountryByName(countryDto.name());

        // Then
        assertThat(country).isPresent();
        assertThat(country.get().id()).isEqualTo(countryDto.id());
        assertThat(country.get().name()).isEqualTo(countryDto.name());
        assertThat(country.get().createDate()).isEqualTo(countryDto.createDate());
        assertThat(country.get().updateDate()).isEqualTo(countryDto.updateDate());

        // Verify
        verify(countryRepository, times(1)).findByName(countryDto.name());
    }

    @Test
    @DisplayName("Should Return empty when you cannot find an state in the database")
    void shouldReturnEmpty_WhenStateNotFoundInDatabase() {
        // Given
        var countryDto = createCountryDto(randomAlphabetic(10));

        when(countryRepository.findByName(countryDto.name())).thenReturn(Optional.empty());

        // When
        Optional<CountryDto> country = countryFindService.findCountryByName(countryDto.name());

        // then
        assertThat(country).isEmpty();

        // Verify
        verify(countryRepository, times(1)).findByName(countryDto.name());
    }
}

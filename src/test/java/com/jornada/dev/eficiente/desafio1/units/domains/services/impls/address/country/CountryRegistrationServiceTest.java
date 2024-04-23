package com.jornada.dev.eficiente.desafio1.units.domains.services.impls.address.country;

import static com.jornada.dev.eficiente.desafio1.builders.CountryBuilder.createCountryDto;
import static com.jornada.dev.eficiente.desafio1.builders.CountryBuilder.createCountryEntity;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.jornada.dev.eficiente.desafio1.domains.dtos.CountryDto;
import com.jornada.dev.eficiente.desafio1.domains.mappers.CountryDomainMapper;
import com.jornada.dev.eficiente.desafio1.domains.repositories.CountryRepository;
import com.jornada.dev.eficiente.desafio1.domains.services.impls.CountryRegistrationServiceImpl;
import com.jornada.dev.eficiente.desafio1.units.UnitTestAbstract;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

class CountryRegistrationServiceTest extends UnitTestAbstract {

    @Mock
    private CountryRepository countryRepository;

    @Mock
    private CountryDomainMapper mapper;

    @InjectMocks
    private CountryRegistrationServiceImpl countryRegistrationService;

    @Test
    @DisplayName("Should return success when registering "
        + "an country thate does not exist in the database")
    void save_country_success() {
        // Given
        var countryDto = createCountryDto(randomAlphabetic(10));
        var countryEntity = createCountryEntity(countryDto.name());

        // Mock
        when(mapper.mapToEntity(countryDto)).thenReturn(countryEntity);
        when(mapper.mapToDto(countryEntity)).thenReturn(countryDto);
        when(countryRepository.save(countryEntity)).thenReturn(countryEntity);

        // When
        CountryDto countrySaved = countryRegistrationService.save(countryDto);

        // Then
        assertThat(countrySaved).isNotNull();
        assertThat(countrySaved.name()).isEqualTo(countryDto.name());
        assertThat(countrySaved.createDate()).isEqualTo(countryDto.createDate());
        assertThat(countrySaved.updateDate()).isEqualTo(countryDto.updateDate());

        // Verify
        verify(countryRepository, times(1)).save(countryEntity);
    }
}

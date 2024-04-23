package com.jornada.dev.eficiente.desafio1.integrations.web.controllers.address.country;

import static com.jornada.dev.eficiente.desafio1.builders.country.CountryBuilder.createCountryRequest;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.jornada.dev.eficiente.desafio1.integrations.IntegrationTestAbstract;
import java.util.Locale;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.http.HttpStatus;

@DisplayName("POST /v1/countries/register")
class SaveCountryControllerTest extends IntegrationTestAbstract {

    @Test
    @DisplayName("Should register a new country successfully")
    void registration_NewCountry_Success() throws Exception {
        var request = createCountryRequest(randomAlphabetic(10).toLowerCase(Locale.ROOT));

        mockMvc
            .perform(post("/v1/countries/register")
                .contentType(APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$").isNotEmpty())
            .andExpect(jsonPath("$.id").isNotEmpty())
            .andExpect(jsonPath("$.name").value(request.name()))
            .andExpect(jsonPath("$.createDate").isNotEmpty())
            .andExpect(jsonPath("$.updateDate").isNotEmpty());
    }

    @Test
    @DisplayName("Should return 400 Bad Request when country name is not unique")
    void registration_NonUniqueCountryName_BadRequest() throws Exception {
        var country = dataInitializerComponent.initializeCountryData();
        var request = createCountryRequest(country.getName().toLowerCase(Locale.ROOT));

        mockMvc.perform(post("/v1/countries/register")
                .contentType(APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$.errors[0].field").value("name"))
            .andExpect(jsonPath("$.errors[0].message").value("Country name must be unique"))
            .andExpect(jsonPath("$.errors[0].httpStatus").value("BAD_REQUEST"))
            .andExpect(jsonPath("$.errors[0].errorCode").value(HttpStatus.BAD_REQUEST.value()))
            .andExpect(jsonPath("$.errors[0].timestamp").isNotEmpty());
    }

    @ParameterizedTest
    @MethodSource("provideNullOrEmptyValues")
    @DisplayName("Should return 400 Bad Request when country name is missing")
    void registration_MissingOrEmptyCountryName_BadRequest(String value) throws Exception {
        var request = createCountryRequest(value);

        mockMvc
            .perform(post("/v1/countries/register")
                .contentType(APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$.errors[0].field").value("name"))
            .andExpect(jsonPath("$.errors[0].message").value("Name is required"))
            .andExpect(jsonPath("$.errors[0].httpStatus").value("BAD_REQUEST"))
            .andExpect(jsonPath("$.errors[0].errorCode").value(HttpStatus.BAD_REQUEST.value()))
            .andExpect(jsonPath("$.errors[0].timestamp").isNotEmpty());
    }

    private static Stream<Arguments> provideNullOrEmptyValues() {
        return Stream.of(
            Arguments.of((String) null),
            Arguments.of("")
        );
    }
}

package com.jornada.dev.eficiente.desafio1.integrations.web.controllers.address.state;

import static com.jornada.dev.eficiente.desafio1.builders.country.CountryBuilder.createCountryRequest;
import static com.jornada.dev.eficiente.desafio1.builders.state.StateBuilder.createStateRequest;
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

@DisplayName("POST /v1/states/register")
class SaveStateControllerTest extends IntegrationTestAbstract {

    @Test
    @DisplayName("Should register a new state successfully")
    void registration_NewState_Success() throws Exception {

        var country = countryComponent.createCountry(randomAlphabetic(10));
        var countryRequest = createCountryRequest(country.getName());
        var request =
            createStateRequest(randomAlphabetic(10).toLowerCase(Locale.ROOT), countryRequest);

        mockMvc
            .perform(post("/v1/states/register")
                .contentType(APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$").isNotEmpty())
            .andExpect(jsonPath("$.id").isNotEmpty())
            .andExpect(jsonPath("$.name").value(request.name()))
            .andExpect(jsonPath("$.createDate").isNotEmpty())
            .andExpect(jsonPath("$.updateDate").isNotEmpty())
            .andExpect(jsonPath("$.country.id").isNotEmpty())
            .andExpect(jsonPath("$.country.name").value(request.country().name()))
            .andExpect(jsonPath("$.country.createDate").isNotEmpty())
            .andExpect(jsonPath("$.country.updateDate").isNotEmpty());
    }

    @Test
    @DisplayName("Should return 400 Bad Request when state name is not unique")
    void registration_NonUniqueTitle_BadRequest() throws Exception {
        var state = dataInitializerComponent.initializeStateData();
        var countryRequest = createCountryRequest(randomAlphabetic(10));
        var request = createStateRequest(state.getName(), countryRequest);

        mockMvc.perform(post("/v1/states/register")
                .contentType(APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$.errors[0].field").value("name"))
            .andExpect(jsonPath("$.errors[0].message").value("State name must be unique"))
            .andExpect(jsonPath("$.errors[0].httpStatus").value("BAD_REQUEST"))
            .andExpect(jsonPath("$.errors[0].errorCode").value(HttpStatus.BAD_REQUEST.value()))
            .andExpect(jsonPath("$.errors[0].timestamp").isNotEmpty());
    }

    @ParameterizedTest
    @MethodSource("provideNullOrEmptyValues")
    @DisplayName("Should return 400 Bad Request when state name is missing")
    void registration_MissingOrEmptyName_BadRequest(String value) throws Exception {
        var country = countryComponent.createCountry(randomAlphabetic(10));
        var countryRequest = createCountryRequest(country.getName());
        var request = createStateRequest(value, countryRequest);

        mockMvc
            .perform(post("/v1/states/register")
                .contentType(APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$.errors[0].field").value("name"))
            .andExpect(jsonPath("$.errors[0].message").value("Name is required"))
            .andExpect(jsonPath("$.errors[0].httpStatus").value("BAD_REQUEST"))
            .andExpect(jsonPath("$.errors[0].errorCode").value(HttpStatus.BAD_REQUEST.value()))
            .andExpect(jsonPath("$.errors[0].timestamp").isNotEmpty());
    }

    @Test
    @DisplayName("Should return 404 Not Found when country name is not found")
    void registration_NonExistentCountry_NotFound() throws Exception {
        var countryRequest = createCountryRequest(randomAlphabetic(10));
        var request = createStateRequest(randomAlphabetic(10), countryRequest);

        mockMvc.perform(post("/v1/states/register")
                .contentType(APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
            .andExpect(status().isNotFound())
            .andExpect(jsonPath("$.errors[0].field").value("countryName"))
            .andExpect(jsonPath("$.errors[0].message").value(
                "Country not found with name: " + request.country().name()))
            .andExpect(jsonPath("$.errors[0].httpStatus").value("NOT_FOUND"))
            .andExpect(jsonPath("$.errors[0].errorCode").value(HttpStatus.NOT_FOUND.value()))
            .andExpect(jsonPath("$.errors[0].timestamp").isNotEmpty());
    }

    private static Stream<Arguments> provideNullOrEmptyValues() {
        return Stream.of(
            Arguments.of((String) null),
            Arguments.of("")
        );
    }
}

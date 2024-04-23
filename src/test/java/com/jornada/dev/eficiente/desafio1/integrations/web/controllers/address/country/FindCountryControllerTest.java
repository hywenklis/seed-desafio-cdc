package com.jornada.dev.eficiente.desafio1.integrations.web.controllers.address.country;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.jornada.dev.eficiente.desafio1.integrations.IntegrationTestAbstract;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

@DisplayName("GET /v1/countries")
class FindCountryControllerTest extends IntegrationTestAbstract {

    @Test
    void test_success_find_state() throws Exception {
        var country = dataInitializerComponent.initializeCountryData();

        mockMvc.perform(get("/v1/countries")
                .param("name", country.getName())
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$").isNotEmpty())
            .andExpect(jsonPath("$.id").value(country.getId().toString()))
            .andExpect(jsonPath("$.name").value(country.getName()))
            .andExpect(jsonPath("$.createDate").isNotEmpty())
            .andExpect(jsonPath("$.updateDate").isNotEmpty());
    }

    @Test
    @DisplayName("shouldn't return anything if it doesn't find the name of the country")
    void test_success_country_empty() throws Exception {
        mockMvc.perform(get("/v1/countries")
                .param("name", randomAlphabetic(10))
                .contentType(APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$").doesNotExist());
    }
}

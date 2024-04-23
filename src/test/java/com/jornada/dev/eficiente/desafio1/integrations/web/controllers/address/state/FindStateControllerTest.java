package com.jornada.dev.eficiente.desafio1.integrations.web.controllers.address.state;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.jornada.dev.eficiente.desafio1.integrations.IntegrationTestAbstract;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

@DisplayName("GET /v1/states")
class FindStateControllerTest extends IntegrationTestAbstract {

    @Test
    void test_success_find_state() throws Exception {
        var state = dataInitializerComponent.initializeStateData();

        mockMvc.perform(get("/v1/states")
                .param("name", state.getName())
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$").isNotEmpty())
            .andExpect(jsonPath("$.id").value(state.getId().toString()))
            .andExpect(jsonPath("$.name").value(state.getName()))
            .andExpect(jsonPath("$.createDate").isNotEmpty())
            .andExpect(jsonPath("$.updateDate").isNotEmpty())
            .andExpect(jsonPath("$.country.id").value(state.getCountry().getId().toString()))
            .andExpect(jsonPath("$.country.name").value(state.getCountry().getName()))
            .andExpect(jsonPath("$.country.createDate").isNotEmpty())
            .andExpect(jsonPath("$.country.updateDate").isNotEmpty());
    }
}

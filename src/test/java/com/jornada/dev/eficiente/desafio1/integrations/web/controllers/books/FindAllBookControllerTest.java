package com.jornada.dev.eficiente.desafio1.integrations.web.controllers.books;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.jornada.dev.eficiente.desafio1.integrations.IntegrationTestAbstract;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("GET /v1/books")
class FindAllBookControllerTest extends IntegrationTestAbstract {


    @Test
    @DisplayName("Should return a list of books with details")
    void test_success() throws Exception {
        var book = dataInitializerComponent.initializeBookData();

        mockMvc.perform(get("/v1/books")
                .contentType(APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isNotEmpty())
            .andExpect(jsonPath("$.[0].id").value(book.getId().toString()))
            .andExpect(jsonPath("$.[0].title").value(book.getTitle()));
    }

    @Test
    @DisplayName("Should return an empty list")
    void test_success_empty() throws Exception {
        mockMvc.perform(get("/v1/books")
                .contentType(APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());
    }
}

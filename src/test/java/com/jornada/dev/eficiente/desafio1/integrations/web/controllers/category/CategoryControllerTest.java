package com.jornada.dev.eficiente.desafio1.integrations.web.controllers.category;

import static com.jornada.dev.eficiente.desafio1.builders.CategoryBuilder.createCategoryRequest;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.jornada.dev.eficiente.desafio1.integrations.IntegrationTestAbstract;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@DisplayName("POST /v1/categories/register")
class CategoryControllerTest extends IntegrationTestAbstract {

    @Test
    @DisplayName("Should register a new categoryName successfully")
    void register_new_category_success() throws Exception {
        var request = createCategoryRequest(randomAlphabetic(10));

        mockMvc
            .perform(post("/v1/categories/register")
                .contentType(APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.name").value(request.name()));
    }

    @Test
    @DisplayName("Should return an exception and prevent "
        + "registering an categoryName with an existing name in the database")
    void registration_ShouldReturnException_WhenExistsCategoryDuplicated() throws Exception {
        var request = createCategoryRequest(randomAlphabetic(10));

        categoryComponent.createCategory(request.name());

        mockMvc
            .perform(post("/v1/categories/register")
                .contentType(APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$.errors[0].field").value("name"))
            .andExpect(jsonPath("$.errors[0].message").value("Category name must be unique"))
            .andExpect(jsonPath("$.errors[0].httpStatus").value("BAD_REQUEST"))
            .andExpect(jsonPath("$.errors[0].errorCode").value(HttpStatus.BAD_REQUEST.value()))
            .andExpect(jsonPath("$.errors[0].timestamp").isNotEmpty());
    }

    @Test
    @DisplayName("Should return BadRequest when name is blank")
    void registration_ShouldReturnBadRequest_WhenNameIsBlank() throws Exception {
        // Given
        var request = createCategoryRequest("");

        // When
        mockMvc
            .perform(MockMvcRequestBuilders.post("/v1/categories/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
            // Then
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$.errors[0].field").value("name"))
            .andExpect(jsonPath("$.errors[0].message").value("Name is required"));
    }
}

package com.jornada.dev.eficiente.desafio1.integrations.web.controllers;

import static com.jornada.dev.eficiente.desafio1.builders.AuthorBuilder.createAuthorRequest;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.jornada.dev.eficiente.desafio1.integrations.IntegrationTestAbstract;
import com.jornada.dev.eficiente.desafio1.web.requests.AuthorRequest;
import java.time.LocalDateTime;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

class AuthorControllerTest extends IntegrationTestAbstract {

  @Test
  @DisplayName("Should register a new author successfully")
  void registration_NewAuthor_Success() throws Exception {
    var request =
        createAuthorRequest("Hywenklis", "hywenklis@email.com", "description");

    mockMvc
        .perform(post("/v1/authors/register")
                     .contentType(APPLICATION_JSON)
                     .content(objectMapper.writeValueAsString(request)))
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.name").value(request.name()))
        .andExpect(jsonPath("$.email").value(request.email()))
        .andExpect(jsonPath("$.description").value(request.description()));
  }

  @Test
  @DisplayName(
      "Should return an exception and prevent registering an author with an existing email in the database")
  void
  registration_ShouldReturnException_WhenExistsAuthorDuplicated()
      throws Exception {
    var request =
        createAuthorRequest("Rherbert", "rherbert@email.com", "description");

    authorComponent.createAuthor(request.name(), request.email(),
                                 request.description(), LocalDateTime.now(),
                                 LocalDateTime.now());

    mockMvc
        .perform(post("/v1/authors/register")
                     .contentType(APPLICATION_JSON)
                     .content(objectMapper.writeValueAsString(request)))
        .andExpect(status().isConflict())
        .andExpect(jsonPath("$.errors[0].field").value("email"))
        .andExpect(jsonPath("$.errors[0].message")
                       .value("Author with email " + request.email() +
                              " already exists"))
        .andExpect(jsonPath("$.errors[0].httpStatus").value("CONFLICT"))
        .andExpect(jsonPath("$.errors[0].errorCode").value(409))
        .andExpect(jsonPath("$.errors[0].timestamp").isNotEmpty());
  }

  @Test
  @DisplayName("Should return BadRequest when name is blank")
  void registration_ShouldReturnBadRequest_WhenNameIsBlank() throws Exception {
    // Given
    var request =
        createAuthorRequest("", "john.doe@example.com", "description");

    // When
    mockMvc
        .perform(MockMvcRequestBuilders.post("/v1/authors/register")
                     .contentType(MediaType.APPLICATION_JSON)
                     .content(objectMapper.writeValueAsString(request)))
        // Then
        .andExpect(status().isBadRequest())
        .andExpect(jsonPath("$.errors[0].field").value("name"))
        .andExpect(jsonPath("$.errors[0].message").value("Name is required"));
  }

  @Test
  @DisplayName("Should return BadRequest when email is blank")
  void registration_ShouldReturnBadRequest_WhenEmailIsBlank() throws Exception {
    // Given
    var request = createAuthorRequest("John Doe", "", "description");

    // When
    mockMvc
        .perform(MockMvcRequestBuilders.post("/v1/authors/register")
                     .contentType(MediaType.APPLICATION_JSON)
                     .content(objectMapper.writeValueAsString(request)))
        // Then
        .andExpect(status().isBadRequest())
        .andExpect(jsonPath("$.errors[0].field").value("email"))
        .andExpect(jsonPath("$.errors[0].message").value("Email is required"));
  }

  @Test
  @DisplayName("Should return BadRequest when invalid email format")
  void registration_ShouldReturnBadRequest_WhenInvalidEmailFormat()
      throws Exception {
    // Given
    var request =
        createAuthorRequest("John Doe", "invalid-email-format", "description");

    // When
    mockMvc
        .perform(MockMvcRequestBuilders.post("/v1/authors/register")
                     .contentType(MediaType.APPLICATION_JSON)
                     .content(objectMapper.writeValueAsString(request)))
        // Then
        .andExpect(status().isBadRequest())
        .andExpect(jsonPath("$.errors[0].field").value("email"))
        .andExpect(
            jsonPath("$.errors[0].message").value("Invalid email format"));
  }

  @Test
  @DisplayName("Should return BadRequest when description is blank")
  void registration_ShouldReturnBadRequest_WhenDescriptionIsBlank()
      throws Exception {
    // Given
    var request = createAuthorRequest("John Doe", "john.doe@example.com", "");

    // When
    mockMvc
        .perform(MockMvcRequestBuilders.post("/v1/authors/register")
                     .contentType(MediaType.APPLICATION_JSON)
                     .content(objectMapper.writeValueAsString(request)))
        // Then
        .andExpect(status().isBadRequest())
        .andExpect(jsonPath("$.errors[0].field").value("description"))
        .andExpect(
            jsonPath("$.errors[0].message").value("Description is required"));
  }

  @Test
  @DisplayName("Should return BadRequest when description exceeds max length")
  void registration_ShouldReturnBadRequest_WhenDescriptionExceedsMaxLength()
      throws Exception {
    // Given
    AuthorRequest request = createAuthorRequest(
        "John Doe", "john.doe@example.com", "a".repeat(401));

    // When
    mockMvc
        .perform(MockMvcRequestBuilders.post("/v1/authors/register")
                     .contentType(MediaType.APPLICATION_JSON)
                     .content(objectMapper.writeValueAsString(request)))
        // Then
        .andExpect(status().isBadRequest())
        .andExpect(jsonPath("$.errors[0].field").value("description"))
        .andExpect(jsonPath("$.errors[0].message")
                       .value("Description cannot exceed 400 characters"));
  }
}

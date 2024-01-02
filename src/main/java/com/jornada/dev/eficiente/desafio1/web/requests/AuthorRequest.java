package com.jornada.dev.eficiente.desafio1.web.requests;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;

@Builder
public record AuthorRequest(

    @NotBlank(message = "Name is required") String name,

    @Email(message = "Invalid email format")
    @NotBlank(message = "Email is required") String email,

    @NotBlank(message = "Description is required")
    @Size(max = 400, message = "Description cannot exceed 400 characters")
    String description) {}

package com.jornada.dev.eficiente.desafio1.web.requests;

import com.jornada.dev.eficiente.desafio1.domains.annotations.Unique;
import com.jornada.dev.eficiente.desafio1.domains.enuns.UniqueType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;

import static com.jornada.dev.eficiente.desafio1.domains.enuns.UniqueType.*;

@Builder
public record AuthorRequest(

        @NotBlank(message = "Name is required") String name,

        @Email(message = "Invalid email format")
        @NotBlank(message = "Email is required")
        @Unique(value = AUTHOR_EMAIL, message = "Email must be unique")
        String email,

        @NotBlank(message = "Description is required")
        @Size(max = 400, message = "Description cannot exceed 400 characters")
        String description) {
}

package com.jornada.dev.eficiente.desafio1.web.responses;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import java.time.LocalDateTime;

@JsonInclude(NON_NULL)
public record CategoryResponse(String name,
                               @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
                               LocalDateTime createDate) {
}

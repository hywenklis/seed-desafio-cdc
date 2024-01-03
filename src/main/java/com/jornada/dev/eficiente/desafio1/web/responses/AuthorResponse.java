package com.jornada.dev.eficiente.desafio1.web.responses;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;

public record AuthorResponse(String name,
                             String email,
                             String description,
                             @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
                             LocalDateTime createDate) {
}

package com.jornada.dev.eficiente.desafio1.web.responses;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;

public record CategoryResponse(String name,
                               @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
                               LocalDateTime createDate) {
}

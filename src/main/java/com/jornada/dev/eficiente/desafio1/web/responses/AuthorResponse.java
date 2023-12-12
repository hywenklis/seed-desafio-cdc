package com.jornada.dev.eficiente.desafio1.web.responses;

import java.time.LocalDateTime;

public record AuthorResponse(String name, String email, String description, LocalDateTime createDate) {

}

package com.jornada.dev.eficiente.desafio1.domains.dtos;

import java.time.LocalDateTime;

import lombok.Builder;

@Builder
public record CategoryDto(String name, LocalDateTime createDate, LocalDateTime updateDate) { }

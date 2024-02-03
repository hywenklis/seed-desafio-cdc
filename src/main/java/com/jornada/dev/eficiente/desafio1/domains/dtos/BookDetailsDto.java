package com.jornada.dev.eficiente.desafio1.domains.dtos;

import java.math.BigDecimal;
import lombok.Builder;

@Builder(toBuilder = true)
public record BookDetailsDto(String accessUpdates,
                             BigDecimal ebookAndPrintedBookPrice,
                             SocialMediaDto socialMedia) {
}

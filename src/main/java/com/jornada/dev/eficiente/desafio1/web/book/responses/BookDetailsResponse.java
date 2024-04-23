package com.jornada.dev.eficiente.desafio1.web.book.responses;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.math.BigDecimal;

@JsonInclude(NON_NULL)
public record BookDetailsResponse(String accessUpdates,
                                  BigDecimal ebookAndPrintedBookPrice,
                                  SocialMediaResponse socialMedia) {
}

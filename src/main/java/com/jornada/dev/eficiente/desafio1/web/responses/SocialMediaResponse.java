package com.jornada.dev.eficiente.desafio1.web.responses;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(NON_NULL)
public record SocialMediaResponse(String linkFacebook,
                                  String linkTwitter,
                                  String iconFacebookUrl,
                                  String iconTwitterUrl) {
}

package com.jornada.dev.eficiente.desafio1.domains.book.dtos;

import com.jornada.dev.eficiente.desafio1.domains.properties.SocialMediaProperty;
import lombok.Builder;

@Builder(toBuilder = true)
public record SocialMediaDto(String linkFacebook,
                             String linkTwitter,
                             String iconFacebookUrl,
                             String iconTwitterUrl) {

    /* TODO adicionar testes */
    public static SocialMediaDto fromConfig(SocialMediaProperty socialMediaProperty,
                                            BookDto bookDto) {
        return new SocialMediaDto(
            socialMediaProperty.getFacebookUrl(bookDto.title()),
            socialMediaProperty.getTwitterUrl(bookDto.title()),
            socialMediaProperty.facebook().icon(),
            socialMediaProperty.twitter().icon());
    }
}

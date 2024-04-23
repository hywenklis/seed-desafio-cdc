package com.jornada.dev.eficiente.desafio1.domains.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "social-media")
public record SocialMediaProperty(FacebookProperty facebook, TwitterProperty twitter) {

    public String getFacebookUrl(String bookTitle) {
        return String.format("%s?u=%s", facebook.url(), encodeUrl(bookTitle));
    }

    public String getTwitterUrl(String bookTitle) {
        return String.format("%s?u=%s", twitter.url(), encodeUrl(bookTitle));
    }

    private String encodeUrl(String value) {
        return value;
    }
}

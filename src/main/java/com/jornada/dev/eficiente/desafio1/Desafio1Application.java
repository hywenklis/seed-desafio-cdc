package com.jornada.dev.eficiente.desafio1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@ConfigurationPropertiesScan(basePackages = "com.jornada.dev.eficiente.desafio1.domains.properties")
@SpringBootApplication
public class Desafio1Application {

    public static void main(String[] args) {
        SpringApplication.run(Desafio1Application.class, args);
    }
}

package com.jornada.dev.eficiente.desafio1.integrations;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jornada.dev.eficiente.desafio1.components.AuthorComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public abstract class IntegrationTestAbstract {

  @Autowired
  protected MockMvc mockMvc;

  @Autowired
  protected ObjectMapper objectMapper;

  @Autowired
  protected AuthorComponent authorComponent;
}

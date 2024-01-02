package com.jornada.dev.eficiente.desafio1.domains.execptions;

public class CategoryAlreadyExistsException extends RuntimeException {

  public CategoryAlreadyExistsException(String message) { super(message); }
}

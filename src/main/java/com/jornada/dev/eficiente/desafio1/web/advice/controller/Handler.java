package com.jornada.dev.eficiente.desafio1.web.advice.controller;

import com.jornada.dev.eficiente.desafio1.web.advice.error.AdviceError;
import com.jornada.dev.eficiente.desafio1.web.advice.error.ErrorResponse;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class Handler {

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ErrorResponse> handleValidationErrors(MethodArgumentNotValidException ex) {
    List<AdviceError> errors = ex.getBindingResult()
        .getFieldErrors()
        .stream()
        .map(fieldError -> new AdviceError(
                fieldError.getField(),
                fieldError.getDefaultMessage(),
                HttpStatus.BAD_REQUEST
            )
        ).toList();

    ErrorResponse errorResponse = new ErrorResponse(errors);
    return ResponseEntity.badRequest().body(errorResponse);
  }
}

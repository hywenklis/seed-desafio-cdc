package com.jornada.dev.eficiente.desafio1.web.advice.controller;

import com.jornada.dev.eficiente.desafio1.domains.exceptions.NotFoundException;
import com.jornada.dev.eficiente.desafio1.web.advice.error.ErrorDetails;
import com.jornada.dev.eficiente.desafio1.web.advice.error.ErrorResponse;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFoundException(
        NotFoundException ex) {
        ErrorDetails error = new ErrorDetails(
            ex.getFieldName(),
            ex.getMessage(),
            HttpStatus.NOT_FOUND,
            HttpStatus.NOT_FOUND.value(),
            LocalDateTime.now());
        return ResponseEntity.badRequest().body(new ErrorResponse(List.of(error)));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationErrors(MethodArgumentNotValidException ex
    ) {
        List<ErrorDetails> errors = mapValidationErrors(ex.getBindingResult());
        return ResponseEntity.badRequest().body(new ErrorResponse(errors));
    }

    private List<ErrorDetails> mapValidationErrors(BindingResult bindingResult) {
        return bindingResult.getFieldErrors()
            .stream()
            .map(fieldError -> new ErrorDetails(
                fieldError.getField(),
                fieldError.getDefaultMessage(),
                HttpStatus.BAD_REQUEST,
                HttpStatus.BAD_REQUEST.value(),
                LocalDateTime.now()))
            .toList();
    }
}

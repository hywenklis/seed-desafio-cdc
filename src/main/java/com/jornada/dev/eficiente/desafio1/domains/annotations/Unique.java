package com.jornada.dev.eficiente.desafio1.domains.annotations;

import com.jornada.dev.eficiente.desafio1.domains.annotations.validators.UniqueValidator;
import com.jornada.dev.eficiente.desafio1.domains.enuns.UniqueType;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Constraint(validatedBy = UniqueValidator.class)
@Target({FIELD, PARAMETER})
@Retention(RUNTIME)
public @interface Unique {

    String message() default "Value must be unique";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    UniqueType value();
}

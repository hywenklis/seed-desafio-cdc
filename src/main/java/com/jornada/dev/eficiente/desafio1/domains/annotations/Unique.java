package com.jornada.dev.eficiente.desafio1.domains.annotations;

import com.jornada.dev.eficiente.desafio1.domains.annotations.validators.UniqueValidator;
import com.jornada.dev.eficiente.desafio1.domains.enuns.UniqueType;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Constraint(validatedBy = UniqueValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface Unique {

    String message() default "Value must be unique";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    UniqueType value();
}

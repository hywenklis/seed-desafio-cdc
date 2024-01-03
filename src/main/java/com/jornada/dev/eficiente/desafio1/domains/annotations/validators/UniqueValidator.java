package com.jornada.dev.eficiente.desafio1.domains.annotations.validators;

import com.jornada.dev.eficiente.desafio1.domains.annotations.Unique;
import com.jornada.dev.eficiente.desafio1.domains.enuns.UniqueType;
import com.jornada.dev.eficiente.desafio1.domains.repositories.AuthorRepository;
import com.jornada.dev.eficiente.desafio1.domains.repositories.CategoryRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

import static com.jornada.dev.eficiente.desafio1.domains.enuns.UniqueType.AUTHOR_EMAIL;
import static com.jornada.dev.eficiente.desafio1.domains.enuns.UniqueType.CATEGORY_NAME;

@Component
@RequiredArgsConstructor
public class UniqueValidator implements ConstraintValidator<Unique, String> {

    private final AuthorRepository authorRepository;
    private final CategoryRepository categoryRepository;

    @Setter
    @Getter
    private Function<String, Optional<?>> findByUniqueField;

    @Setter
    @Getter
    private Map<UniqueType, Function<String, Optional<?>>> repositoryMap;

    @Override
    public void initialize(Unique constraintAnnotation) {
        UniqueType type = constraintAnnotation.value();

        repositoryMap = Map.of(
                AUTHOR_EMAIL, authorRepository::findByEmail,
                CATEGORY_NAME, categoryRepository::findByName
        );

        findByUniqueField = repositoryMap.get(type);

        if (findByUniqueField == null) {
            throw new IllegalArgumentException("Unsupported validation type: " + type);
        }
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (StringUtils.isEmpty(value)) {
            return true;
        }

        Optional<?> existingEntity = findByUniqueField.apply(value);
        return !existingEntity.isPresent();
    }
}

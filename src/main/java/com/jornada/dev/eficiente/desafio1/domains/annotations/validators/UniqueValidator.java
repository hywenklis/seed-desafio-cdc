package com.jornada.dev.eficiente.desafio1.domains.annotations.validators;

import static com.jornada.dev.eficiente.desafio1.domains.enuns.UniqueType.AUTHOR_EMAIL;
import static com.jornada.dev.eficiente.desafio1.domains.enuns.UniqueType.BOOK_ISBN;
import static com.jornada.dev.eficiente.desafio1.domains.enuns.UniqueType.BOOK_TITLE;
import static com.jornada.dev.eficiente.desafio1.domains.enuns.UniqueType.CATEGORY_NAME;

import com.jornada.dev.eficiente.desafio1.domains.annotations.Unique;
import com.jornada.dev.eficiente.desafio1.domains.enuns.UniqueType;
import com.jornada.dev.eficiente.desafio1.domains.repositories.AuthorRepository;
import com.jornada.dev.eficiente.desafio1.domains.repositories.BookRepository;
import com.jornada.dev.eficiente.desafio1.domains.repositories.CategoryRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UniqueValidator implements ConstraintValidator<Unique, String> {

    private final AuthorRepository authorRepository;
    private final CategoryRepository categoryRepository;
    private final BookRepository bookRepository;

    private Map<UniqueType, Function<String, Optional<?>>> repositoryMap;
    private UniqueType type;

    @Override
    public void initialize(Unique constraintAnnotation) {
        type = constraintAnnotation.value();

        repositoryMap = Map.of(
            AUTHOR_EMAIL, authorRepository::findByEmail,
            CATEGORY_NAME, categoryRepository::findByName,
            BOOK_TITLE, bookRepository::findByTitle,
            BOOK_ISBN, bookRepository::findByIsbn
        );
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (StringUtils.isEmpty(value)) {
            return true;
        }

        Optional<?> existingEntity = repositoryMap.get(type).apply(value);
        return existingEntity.isEmpty();
    }
}

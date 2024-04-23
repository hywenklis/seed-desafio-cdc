package com.jornada.dev.eficiente.desafio1.domains.configuration.annotations.validators;

import static com.jornada.dev.eficiente.desafio1.domains.enuns.UniqueType.AUTHOR_EMAIL;
import static com.jornada.dev.eficiente.desafio1.domains.enuns.UniqueType.BOOK_ISBN;
import static com.jornada.dev.eficiente.desafio1.domains.enuns.UniqueType.BOOK_TITLE;
import static com.jornada.dev.eficiente.desafio1.domains.enuns.UniqueType.CATEGORY_NAME;
import static com.jornada.dev.eficiente.desafio1.domains.enuns.UniqueType.COUNTRY_NAME;
import static com.jornada.dev.eficiente.desafio1.domains.enuns.UniqueType.STATE_NAME;

import com.jornada.dev.eficiente.desafio1.domains.address.country.services.CountryFindService;
import com.jornada.dev.eficiente.desafio1.domains.address.state.services.StateFindService;
import com.jornada.dev.eficiente.desafio1.domains.author.services.AuthorFindService;
import com.jornada.dev.eficiente.desafio1.domains.book.services.BookFindService;
import com.jornada.dev.eficiente.desafio1.domains.category.services.CategoryFindService;
import com.jornada.dev.eficiente.desafio1.domains.configuration.annotations.Unique;
import com.jornada.dev.eficiente.desafio1.domains.enuns.UniqueType;
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

    private final AuthorFindService authorFindService;
    private final CategoryFindService categoryFindService;
    private final BookFindService bookFindService;
    private final CountryFindService countryFindService;
    private final StateFindService stateFindService;

    private Map<UniqueType, Function<String, Optional<?>>> repositoryMap;
    private UniqueType type;

    @Override
    public void initialize(Unique constraintAnnotation) {
        type = constraintAnnotation.value();

        repositoryMap = Map.of(
            AUTHOR_EMAIL, authorFindService::findAuthorByEmail,
            CATEGORY_NAME, categoryFindService::findCategoryByName,
            BOOK_TITLE, bookFindService::findBookByTitle,
            BOOK_ISBN, bookFindService::findBookByIsbn,
            COUNTRY_NAME, countryFindService::findCountryByName,
            STATE_NAME, stateFindService::findStateByName
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

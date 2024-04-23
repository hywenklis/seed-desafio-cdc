package com.jornada.dev.eficiente.desafio1.components.state;

import com.jornada.dev.eficiente.desafio1.builders.state.StateBuilder;
import com.jornada.dev.eficiente.desafio1.domains.address.country.entities.CountryEntity;
import com.jornada.dev.eficiente.desafio1.domains.address.state.entities.StateEntity;
import com.jornada.dev.eficiente.desafio1.domains.address.state.repositories.StateRepository;
import java.util.Locale;
import org.springframework.stereotype.Component;

@Component
public class StateComponent {

    private final StateRepository stateRepository;

    public StateComponent(StateRepository stateRepository) {
        this.stateRepository = stateRepository;
    }

    public StateEntity createState(final String name,
                                   final CountryEntity country) {
        return stateRepository.save(
            StateBuilder.createStateEntity(name.toLowerCase(Locale.ROOT), country));
    }
}

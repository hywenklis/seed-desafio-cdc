package com.jornada.dev.eficiente.desafio1.domains.address.state.services;

import com.jornada.dev.eficiente.desafio1.domains.address.state.dtos.StateDto;

public interface StateRegistrationService {
    StateDto save(StateDto stateDto);
}

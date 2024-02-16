package com.jornada.dev.eficiente.desafio1.domains.services;

import com.jornada.dev.eficiente.desafio1.domains.dtos.StateDto;

public interface StateRegistrationService {
    StateDto save(StateDto stateDto);
}

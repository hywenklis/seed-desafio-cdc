package com.jornada.dev.eficiente.desafio1.domains.address.state.services;

import com.jornada.dev.eficiente.desafio1.domains.address.state.dtos.StateDto;
import java.util.Optional;

public interface StateFindService {
    Optional<StateDto> findStateByName(String name);
}

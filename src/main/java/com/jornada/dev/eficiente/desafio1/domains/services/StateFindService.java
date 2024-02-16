package com.jornada.dev.eficiente.desafio1.domains.services;

import com.jornada.dev.eficiente.desafio1.domains.dtos.StateDto;
import java.util.Optional;

public interface StateFindService {
    Optional<StateDto> findStateByName(String name);
}

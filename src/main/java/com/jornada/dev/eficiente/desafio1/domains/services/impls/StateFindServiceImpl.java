package com.jornada.dev.eficiente.desafio1.domains.services.impls;

import com.jornada.dev.eficiente.desafio1.domains.dtos.StateDto;
import com.jornada.dev.eficiente.desafio1.domains.mappers.StateDomainMapper;
import com.jornada.dev.eficiente.desafio1.domains.repositories.StateRepository;
import com.jornada.dev.eficiente.desafio1.domains.services.StateFindService;
import java.util.Locale;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StateFindServiceImpl implements StateFindService {

    private final StateRepository stateRepository;
    private final StateDomainMapper mapper;

    @Override
    public Optional<StateDto> findStateByName(String name) {
        return stateRepository
            .findByName(name.toLowerCase(Locale.ROOT))
            .map(mapper::mapToDto);
    }
}

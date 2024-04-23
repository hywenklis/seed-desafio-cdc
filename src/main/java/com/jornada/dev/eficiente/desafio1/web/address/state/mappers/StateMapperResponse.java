package com.jornada.dev.eficiente.desafio1.web.address.state.mappers;

import com.jornada.dev.eficiente.desafio1.domains.address.state.dtos.StateDto;
import com.jornada.dev.eficiente.desafio1.web.address.state.responses.StateResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StateMapperResponse {

    StateResponse mapToDto(StateDto stateDto);
}

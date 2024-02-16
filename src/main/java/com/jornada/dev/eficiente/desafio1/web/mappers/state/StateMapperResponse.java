package com.jornada.dev.eficiente.desafio1.web.mappers.state;

import com.jornada.dev.eficiente.desafio1.domains.dtos.CategoryDto;
import com.jornada.dev.eficiente.desafio1.domains.dtos.StateDto;
import com.jornada.dev.eficiente.desafio1.web.responses.CategoryResponse;
import com.jornada.dev.eficiente.desafio1.web.responses.StateResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StateMapperResponse {

    StateResponse mapToDto(StateDto stateDto);
}

package com.jornada.dev.eficiente.desafio1.web.mappers.state;

import com.jornada.dev.eficiente.desafio1.domains.dtos.StateDto;
import com.jornada.dev.eficiente.desafio1.web.requests.StateRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface StateMapperRequest {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "updateDate", ignore = true)
    @Mapping(target = "createDate", ignore = true)
    StateDto mapToDto(StateRequest stateRequest);
}

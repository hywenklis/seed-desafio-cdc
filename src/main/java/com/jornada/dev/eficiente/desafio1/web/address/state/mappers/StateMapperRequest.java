package com.jornada.dev.eficiente.desafio1.web.address.state.mappers;

import com.jornada.dev.eficiente.desafio1.domains.address.state.dtos.StateDto;
import com.jornada.dev.eficiente.desafio1.web.address.state.requests.StateRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface StateMapperRequest {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "updateDate", ignore = true)
    @Mapping(target = "createDate", ignore = true)
    StateDto mapToDto(StateRequest stateRequest);
}

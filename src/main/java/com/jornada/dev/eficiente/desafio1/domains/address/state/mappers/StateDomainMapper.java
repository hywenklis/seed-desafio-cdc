package com.jornada.dev.eficiente.desafio1.domains.address.state.mappers;

import com.jornada.dev.eficiente.desafio1.domains.address.state.dtos.StateDto;
import com.jornada.dev.eficiente.desafio1.domains.address.state.entities.StateEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StateDomainMapper {

    StateDto mapToDto(StateEntity stateEntity);

    StateEntity mapToEntity(StateDto stateDto);
}

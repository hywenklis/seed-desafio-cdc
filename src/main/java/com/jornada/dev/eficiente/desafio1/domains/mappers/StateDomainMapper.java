package com.jornada.dev.eficiente.desafio1.domains.mappers;

import com.jornada.dev.eficiente.desafio1.domains.dtos.CategoryDto;
import com.jornada.dev.eficiente.desafio1.domains.dtos.StateDto;
import com.jornada.dev.eficiente.desafio1.domains.entities.CategoryEntity;
import com.jornada.dev.eficiente.desafio1.domains.entities.StateEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface StateDomainMapper {

    StateDto mapToDto(StateEntity stateEntity);

    StateEntity mapToEntity(StateDto stateDto);
}

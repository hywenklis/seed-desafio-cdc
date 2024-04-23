package com.jornada.dev.eficiente.desafio1.web.controllers.address.state;

import com.jornada.dev.eficiente.desafio1.domains.services.StateFindService;
import com.jornada.dev.eficiente.desafio1.web.mappers.state.StateMapperResponse;
import com.jornada.dev.eficiente.desafio1.web.responses.StateResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/states")
@RequiredArgsConstructor
@Tag(name = "State", description = "Endpoint related to state get")
public class FindStateController {

    private final StateFindService service;
    private final StateMapperResponse mapperResponse;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(
        summary = "Find state",
        description = "Find state by name"
    )
    public Optional<StateResponse> findStateByName(@RequestParam String name) {
        return service.findStateByName(name).map(mapperResponse::mapToDto);
    }
}

package com.algaworks.devcontainers.examples.meteorology.api.v1.controller;

import com.algaworks.devcontainers.examples.meteorology.api.v1.assembler.TemperatureModelAssembler;
import com.algaworks.devcontainers.examples.meteorology.api.v1.disassembler.TemperatureInputDisassembler;
import com.algaworks.devcontainers.examples.meteorology.api.v1.model.Input.TemperatureInput;
import com.algaworks.devcontainers.examples.meteorology.api.v1.model.TemperatureModel;
import com.algaworks.devcontainers.examples.meteorology.domain.model.Temperature;
import com.algaworks.devcontainers.examples.meteorology.domain.service.TemperatureService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/temperatures")
public class TemperatureController {

    private final TemperatureService temperatureService;
    private final TemperatureModelAssembler temperatureModelAssembler;
    private final TemperatureInputDisassembler temperatureInputDisassembler;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<TemperatureModel> listAll() {
        return temperatureModelAssembler.toCollectionModel(temperatureService.listAllTemperatures());
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public TemperatureModel add(@RequestBody @Valid TemperatureInput temperatureInput) {
        Temperature temperature = temperatureInputDisassembler.toDomainObject(temperatureInput);
        temperature.setCreatedAt(Instant.now());

        return temperatureModelAssembler.toModel(temperatureService.save(temperature));
    }

}

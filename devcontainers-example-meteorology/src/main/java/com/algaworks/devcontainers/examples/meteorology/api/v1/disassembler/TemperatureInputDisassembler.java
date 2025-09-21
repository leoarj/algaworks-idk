package com.algaworks.devcontainers.examples.meteorology.api.v1.disassembler;

import com.algaworks.devcontainers.examples.meteorology.api.v1.model.Input.TemperatureInput;
import com.algaworks.devcontainers.examples.meteorology.domain.model.Temperature;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class TemperatureInputDisassembler {

    private final ModelMapper modelMapper;

    public Temperature toDomainObject(TemperatureInput temperatureInput) {
        return modelMapper.map(temperatureInput, Temperature.class);
    }

}

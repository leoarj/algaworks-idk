package com.algaworks.devcontainers.examples.meteorology.api.v1.assembler;

import com.algaworks.devcontainers.examples.meteorology.api.v1.model.TemperatureModel;
import com.algaworks.devcontainers.examples.meteorology.domain.model.Temperature;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@AllArgsConstructor
@Component
public class TemperatureModelAssembler {

    private final ModelMapper modelMapper;

    public TemperatureModel toModel(Temperature temperature) {
        return modelMapper.map(temperature, TemperatureModel.class);
    }

    public List<TemperatureModel> toCollectionModel(List<Temperature> temperatures) {
        return temperatures.stream()
                .map(this::toModel)
                .toList();
    }

}

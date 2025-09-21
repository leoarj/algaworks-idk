package com.algaworks.devcontainers.examples.meteorology.api.v1.model.Input;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TemperatureInput {

    @NotNull
    private Float celsiusValue;
    @NotNull
    private Double latitude;
    @NotNull
    private Double longitude;
}

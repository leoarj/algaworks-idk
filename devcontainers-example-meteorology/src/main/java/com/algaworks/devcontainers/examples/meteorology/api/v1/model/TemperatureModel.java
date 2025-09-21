package com.algaworks.devcontainers.examples.meteorology.api.v1.model;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
public class TemperatureModel {

    private Long id;
    private Float celsiusValue;
    private Double latitude;
    private Double longitude;
    private Instant createdAt;
}

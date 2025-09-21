package com.algaworks.devcontainers.examples.meteorology.domain.service;

import com.algaworks.devcontainers.examples.meteorology.domain.model.Temperature;
import com.algaworks.devcontainers.examples.meteorology.domain.repository.TemperatureRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@RequiredArgsConstructor
@Service
public class TemperatureService {
    private final TemperatureRepository repository;

    public List<Temperature> listAllTemperatures() {
        return repository.findAll();
    }

    public Temperature save(Temperature temperature) {
        temperature.setCreatedAt(Instant.now());

        return repository.save(temperature);
    }
}

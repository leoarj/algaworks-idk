package com.algaworks.devcontainers.examples.meteorology.domain.repository;

import com.algaworks.devcontainers.examples.meteorology.domain.model.Temperature;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TemperatureRepository extends JpaRepository<Temperature, Long> {
}

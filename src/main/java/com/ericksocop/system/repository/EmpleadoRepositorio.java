package com.ericksocop.system.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ericksocop.system.model.Empleados;

public interface EmpleadoRepositorio extends JpaRepository<Empleados, Integer> {
    boolean existsByDpi(int dpi);
}

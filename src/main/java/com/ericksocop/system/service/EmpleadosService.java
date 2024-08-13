package com.ericksocop.system.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ericksocop.system.exception.EmpleadosException;
import com.ericksocop.system.model.Empleados;
import com.ericksocop.system.repository.EmpleadoRepositorio;

@Service

public class EmpleadosService implements IEmpleadosService {
    @Autowired
    private EmpleadoRepositorio empleadoRepositorio;
    @Override
    public List<Empleados> listarEmpleados() {
        return empleadoRepositorio.findAll();
    }

    @Override
    public Empleados buscarEmpleados(Integer idEmpleado) {
        Empleados empleados = empleadoRepositorio.findById(idEmpleado).orElse(null);
        return empleados;
    }

    @Override
    public Empleados guardarEmpleados(Empleados empleados) {
        if(empleadoRepositorio.existsByDpi(empleados.getDpi())){
            throw new EmpleadosException("El DPI ya existe en el sistema");
        }
        return empleadoRepositorio.save(empleados);
    }

    @Override
    public void eliminarEmpleados(Empleados empleados) {
        empleadoRepositorio.delete(empleados);
    }
    
    
}

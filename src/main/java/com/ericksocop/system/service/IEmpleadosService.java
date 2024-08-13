package com.ericksocop.system.service;

import java.util.List;

import com.ericksocop.system.model.Empleados;

public interface IEmpleadosService {

    public List<Empleados> listarEmpleados();

    public Empleados buscarEmpleados(Integer idEmpleado);
    
    public Empleados guardarEmpleados(Empleados empleados);

    public void eliminarEmpleados(Empleados empleados);
}

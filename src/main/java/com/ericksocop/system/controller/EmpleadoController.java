package com.ericksocop.system.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ericksocop.system.exception.EmpleadosException;
import com.ericksocop.system.model.Empleados;
import com.ericksocop.system.service.IEmpleadosService;

@RestController //http://localhost:8087/rh-empleado

@RequestMapping("rh-empleado")

public class EmpleadoController {

    private static final Logger logger = LoggerFactory.getLogger(EmpleadoController.class);
    
    @Autowired
    private IEmpleadosService iEmpleadosService;

    //http://localhost:8087/rh-empleado/empleados
    @GetMapping("/empleados")

    public List<Empleados> obtenerEmpleado(){
        var empleado = iEmpleadosService.listarEmpleados();
        empleado.forEach((empleado2 -> logger.info(empleado.toString())));
        return empleado;

    }

    
    @PostMapping("/empleadosA")
    public Empleados agregarEmpleados(@RequestBody Empleados empleados) {
        if(iEmpleadosService.listarEmpleados().stream().anyMatch(e -> e.getDpi() == empleados.getDpi())){
            throw new EmpleadosException("El Dpi ya existe");
        }
        logger.info("Empleado agregado");
        return iEmpleadosService.guardarEmpleados(empleados);

    }

    @GetMapping("/empleados/{id}")

    public ResponseEntity<Empleados> buscarEmpleado(@PathVariable Integer id) {
        Empleados empleado = iEmpleadosService.buscarEmpleados(id);
        if (empleado == null)
            throw new EmpleadosException("No se encontro el empleado");
        return ResponseEntity.ok(empleado);
    }
    
    @PutMapping("/empleados/{id}")
    public ResponseEntity<Empleados> editarEmpleado(@PathVariable Integer id, @RequestBody Empleados empleadoedit) {
        Empleados empleados = iEmpleadosService.buscarEmpleados(id);
        if (empleados == null)
            throw new EmpleadosException("El id no existe");
        
        if (iEmpleadosService.listarEmpleados().stream().anyMatch(e -> e.getDpi() == empleadoedit.getDpi() && e.getIdEmpleado() != id)) {
            throw new EmpleadosException("El Dpi ya existe");
        }
        
        empleados.setDpi(empleadoedit.getDpi());
        empleados.setNombreCompleto(empleadoedit.getNombreCompleto());
        empleados.setPuesto(empleadoedit.getPuesto());
        empleados.setSalario(empleadoedit.getSalario());
        iEmpleadosService.guardarEmpleados(empleados);
        return ResponseEntity.ok(empleados);
    }

    @DeleteMapping("/empleados/{id}")
    public ResponseEntity <Map<String, Boolean>> eliminarEmpleados(@PathVariable Integer id){
        Empleados empleados = iEmpleadosService.buscarEmpleados(id);
        if (empleados == null)
            throw new EmpleadosException("El id no existe");
            iEmpleadosService.eliminarEmpleados(empleados);

            //JSON (eliminado: true)

            Map<String, Boolean> respuesta = new HashMap<>();
            respuesta.put("Eliminado", true);

            return ResponseEntity.ok(respuesta);

    }
    
}

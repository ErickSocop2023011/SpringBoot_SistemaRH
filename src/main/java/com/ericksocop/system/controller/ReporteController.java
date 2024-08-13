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

import com.ericksocop.system.exception.ReporteException;
import com.ericksocop.system.model.Reportes;
import com.ericksocop.system.service.IReportesService;

@RestController //http://localhost:8087/rh-reporte

@RequestMapping("rh-reporte")

public class ReporteController {
    private static final Logger logger = LoggerFactory.getLogger(ReporteController.class);

    @Autowired
    private IReportesService iReportesService;

    //http://localhost:8087/rh-reporte/reportes
    @GetMapping("/reportes")

    public List<Reportes> obtenReportes() {
        var reporte = iReportesService.listarReportes();
        reporte.forEach((reporte2 -> logger.info(reporte.toString())));
        return reporte;
    }

    @PostMapping("reportesA")
    public Reportes agregaReportes(@RequestBody Reportes reportes) {
        logger.info("Reporte agregado");
        return iReportesService.guardarReporte(reportes);
    }

    @GetMapping("/reportes/{id}")
    public ResponseEntity<Reportes> buscarReporte(@PathVariable Integer id){
        Reportes reporte = iReportesService.buscarReporte(id);
        if(reporte == null)
            throw new ReporteException("no se encontro el reporte");
        return ResponseEntity.ok(reporte);
    }

    @PutMapping("/reportes/{id}")
    public ResponseEntity<Reportes> editarReporte(@PathVariable Integer id, @RequestBody Reportes reporteedit) {
        Reportes reportes = iReportesService.buscarReporte(id);
        if (reportes == null)
            throw new ReporteException("El id no existe");
        reportes.setDescripcion(reporteedit.getDescripcion());
        reportes.setHorasCompletadas(reporteedit.getHorasCompletadas());
        reportes.setHorasSolicitadas(reporteedit.getHorasSolicitadas());
        reportes.setTasaDeExito(reporteedit.getTasaDeExito());
        iReportesService.guardarReporte(reportes);
        return ResponseEntity.ok(reportes);
    }
    
    @DeleteMapping("/reportes/{id}")
    public ResponseEntity <Map<String, Boolean>> eliminarEmpleados(@PathVariable Integer id){
        Reportes reportes = iReportesService.buscarReporte(id);
        if (reportes == null)
            throw new ReporteException("El id no existe");
            iReportesService.eliminarReporte(reportes);

            //JSON (eliminado: true)

            Map<String, Boolean> respuesta = new HashMap<>();
            respuesta.put("Eliminado", true);

            return ResponseEntity.ok(respuesta);

    }
}

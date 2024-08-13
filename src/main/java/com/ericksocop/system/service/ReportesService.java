package com.ericksocop.system.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ericksocop.system.model.Reportes;
import com.ericksocop.system.repository.ReporteRepositorio;

@Service

public class ReportesService implements IReportesService {
    @Autowired
    private ReporteRepositorio reporteRepositorio;
    @Override
    public List<Reportes> listarReportes() {
        return reporteRepositorio.findAll();
    }

    @Override
    public Reportes buscarReporte(Integer idReporte) {
        Reportes reportes = reporteRepositorio.findById(idReporte).orElse(null);
        return reportes;
    }

    @Override
    public Reportes guardarReporte(Reportes reportes) {
        return reporteRepositorio.save(reportes);
    }

    @Override
    public void eliminarReporte(Reportes reportes) {
        reporteRepositorio.delete(reportes);
    }

}

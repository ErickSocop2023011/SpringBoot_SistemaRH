package com.ericksocop.system.service;

import java.util.List;

import com.ericksocop.system.model.Reportes;

public interface IReportesService {
    public List<Reportes> listarReportes();

    public Reportes buscarReporte(Integer idReporte);

    public Reportes guardarReporte(Reportes reportes);

    public void eliminarReporte(Reportes reportes);
}

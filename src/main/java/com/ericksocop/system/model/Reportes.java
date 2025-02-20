package com.ericksocop.system.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class Reportes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer idReporte;
    String Descripcion;
    Integer horasSolicitadas;
    Integer horasCompletadas;
    Integer tasaDeExito;
}

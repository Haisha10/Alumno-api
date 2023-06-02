package com.upc.Alumnoapi.model.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AlumnoEscuelaDTO implements Serializable {
    private Long Id;
    private String nombreCompleto;
    private String nombreEscuela;
    private String localidad;
}

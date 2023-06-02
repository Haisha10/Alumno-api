package com.upc.Alumnoapi.model.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AlumnoDTO implements Serializable {
    private Long Id;
    private String firstname;
    private String lastname;
    private Long schoolId;
}

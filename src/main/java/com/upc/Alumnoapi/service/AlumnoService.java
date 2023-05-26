package com.upc.Alumnoapi.service;

import com.upc.Alumnoapi.model.Alumno;
import com.upc.Alumnoapi.repository.AlumnoRepository;

import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import org.springdoc.api.OpenApiResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AlumnoService {

    @Autowired
    AlumnoRepository alumnoRepository;

    // Lectura de todos los registros
    // Leer todos
    @Transactional(readOnly = true)
    public List<Alumno> getAllAlumnos() {
        return alumnoRepository.findAll();
    }

    // Lectura por ID
    // Lectura por ID
    @Transactional(readOnly = true)
    public Alumno getAlumno(Long alumnoId) {
        return alumnoRepository.findById(alumnoId)
                .orElseThrow(() -> new OpenApiResourceNotFoundException("No existe registro con ID: " + alumnoId));
    }

    // Crear un alumno
    // Crear
    @Transactional
    public Alumno insertAlumno(Alumno alumnoDetalle) {
        return alumnoRepository.save(alumnoDetalle);
    }

    // Modificar un alumno
    // Lectura por ID
    // Verificar cambios en los campos
    // Actualizar los campos
    @Transactional
    public Alumno updateAlumno(Long alumnoId, Alumno alumnoDetalle) {
        Alumno alumno = alumnoRepository.findById(alumnoId)
                .orElseThrow(() -> new OpenApiResourceNotFoundException("No existe registro con ID: " + alumnoId));
        if(alumno.equals(alumnoDetalle)) {
            return alumno;
        }
        alumno.setFirstname(alumnoDetalle.getFirstname());
        alumno.setLastname(alumnoDetalle.getLastname());
        return alumnoRepository.save(alumno);
    }

    // Eliminar un alumno
    // Lectura por ID
    // Eliminar el registro
    @Transactional
    public Alumno deleteAlumno(Long alumnoId) {
        Alumno alumno = alumnoRepository.findById(alumnoId)
                .orElseThrow(() -> new OpenApiResourceNotFoundException("No existe registro con ID: " + alumnoId));
        alumnoRepository.deleteById(alumnoId);
        return alumno;
    }
}

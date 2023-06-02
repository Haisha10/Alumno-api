package com.upc.Alumnoapi.service;

import com.upc.Alumnoapi.model.dto.AlumnoDTO;
import com.upc.Alumnoapi.model.dto.AlumnoEscuelaDTO;
import com.upc.Alumnoapi.model.entity.Alumno;
import com.upc.Alumnoapi.model.entity.Escuela;
import com.upc.Alumnoapi.repository.AlumnoRepository;
import com.upc.Alumnoapi.repository.EscuelaRepository;

import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import org.springdoc.api.OpenApiResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AlumnoService {

    @Autowired
    AlumnoRepository alumnoRepository;

    @Autowired
    EscuelaRepository escuelaRepository;

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
    public Alumno insertAlumno(AlumnoDTO alumnoDetalle) {
        Alumno alumno = new Alumno();
        Escuela escuela = new Escuela();
        alumno.setFirstname(alumnoDetalle.getFirstname());
        alumno.setLastname(alumnoDetalle.getLastname());
        // Escuela
        escuela = escuelaRepository.findById(alumnoDetalle.getSchoolId())
                .orElse(escuela = null);
        alumno.setEscuela(escuela);
        return alumnoRepository.save(alumno);
    }

    // Modificar un alumno
    // Lectura por ID
    // Verificar cambios en los campos
    // Actualizar los campos
    @Transactional
    public Alumno updateAlumno(Long alumnoId, AlumnoDTO alumnoDetalle) {
        // Verificar si existe
        Alumno alumno = alumnoRepository.findById(alumnoId)
                .orElseThrow(() -> new OpenApiResourceNotFoundException("No existe registro con ID: " + alumnoId));
        Escuela escuela;
        // Verificar si hay cambios
        if (alumno.getEscuela() != null)
            if (alumno.getEscuela().getId().equals(alumnoDetalle.getSchoolId()) &&
                    alumno.getFirstname().equals(alumnoDetalle.getFirstname()) &&
                    alumno.getLastname().equals(alumnoDetalle.getLastname()))
                return alumno;
        // Actualizar los campos
        alumno.setFirstname(alumnoDetalle.getFirstname());
        alumno.setLastname(alumnoDetalle.getLastname());
        escuela = escuelaRepository.findById(alumnoDetalle.getSchoolId())
                .orElse(escuela = null);
        alumno.setEscuela(escuela);
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

    @Transactional(readOnly = true)
    public List<AlumnoEscuelaDTO> getAllAlumnosEscuela() {
        List<AlumnoEscuelaDTO> listaDTO = new ArrayList<AlumnoEscuelaDTO>();
        List<Alumno> alumnos = alumnoRepository.findAll();
        Escuela escuela;
        for (Alumno alumno : alumnos) {
            AlumnoEscuelaDTO dto = new AlumnoEscuelaDTO();
            escuela = escuelaRepository.findEscuelaByAlumnoId(alumno.getId());
            dto.setNombreCompleto(alumno.getLastname() + " , " + alumno.getFirstname());
            dto.setNombreEscuela("Sin Escuela");
            dto.setLocalidad("Virtual");
            if (escuela != null) {
                dto.setNombreEscuela(escuela.getDescription());
                dto.setLocalidad("Monterrico");
                if (escuela.getId() > 1)
                    dto.setLocalidad("San Isidro");
            }

            dto.setId(alumno.getId());
            listaDTO.add(dto);
        }
        return listaDTO;
    }
}

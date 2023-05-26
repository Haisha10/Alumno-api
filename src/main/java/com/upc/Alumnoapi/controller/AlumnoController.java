package com.upc.Alumnoapi.controller;

import com.upc.Alumnoapi.model.Alumno;
import com.upc.Alumnoapi.service.AlumnoService;

import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/prueba")
public class AlumnoController {
    
    @Autowired
    private AlumnoService alumnoService;

    @GetMapping("/alumnos")
    public ResponseEntity<List<Alumno>> getAllAlumnos() {
        return new ResponseEntity<List<Alumno>>(alumnoService.getAllAlumnos(), HttpStatus.OK);
    }

    @GetMapping("/alumnos/{id}")
    public ResponseEntity<Alumno> getAlumno(@PathVariable(value = "id") Long alumnoId) {
        return new ResponseEntity<Alumno>(alumnoService.getAlumno(alumnoId), HttpStatus.OK);
    }

    @PostMapping("/alumnos")
    public ResponseEntity<Alumno> createAlumno(Alumno alumno) {
        return new ResponseEntity<Alumno>(alumnoService.insertAlumno(alumno), HttpStatus.CREATED);
    }

    @PutMapping("/alumnos/{id}")
    public ResponseEntity<Alumno> updateAlumno(@PathVariable(value = "id") Long alumnoId, @RequestBody Alumno alumno) {
        return new ResponseEntity<Alumno>(alumnoService.updateAlumno(alumnoId, alumno), HttpStatus.OK);
    }

    @DeleteMapping("/alumnos/{id}")
    public ResponseEntity<Alumno> deleteAlumno(@PathVariable(value = "id") Long alumnoId) {
        return new ResponseEntity<Alumno>(alumnoService.deleteAlumno(alumnoId), HttpStatus.NO_CONTENT);
    }
}

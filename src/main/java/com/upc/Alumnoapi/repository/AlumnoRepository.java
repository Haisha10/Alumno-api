package com.upc.Alumnoapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.upc.Alumnoapi.model.entity.Alumno;

public interface AlumnoRepository extends JpaRepository<Alumno, Long> {
    
}

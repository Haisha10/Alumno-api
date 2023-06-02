package com.upc.Alumnoapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.upc.Alumnoapi.model.entity.Escuela;

public interface EscuelaRepository extends JpaRepository<Escuela, Long> {
    @Query (value = "SELECT E.* FROM schools e JOIN students s ON s.school_id = e.id WHERE s.id = :id", nativeQuery = true)
    Escuela findEscuelaByAlumnoId(Long id);
}

package com.anuar.foro_hub.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.anuar.foro_hub.domain.Curso;

public interface CursoRepository extends JpaRepository<Curso, Long>{

    Optional<Curso> findByNombre(String nombre);
    
}

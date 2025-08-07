package com.anuar.foro_hub.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.anuar.foro_hub.domain.Curso;
import com.anuar.foro_hub.domain.Topico;
import com.anuar.foro_hub.dto.request.TopicoDto;

public interface TopicoRepository extends
                JpaRepository<Topico, Long> {

        Optional<Curso> findByTitulo(String curso);

        @Query(value = "select new com.anuar.foro_hub.dto.request.TopicoDto" +
                        "(t.titulo, t.mensaje, t.autor.nombre , t.curso.nombre) " +
                        "FROM Topico t " +
                        "WHERE t.titulo = :titulo AND t.mensaje = :mensaje")
        Optional<TopicoDto> findByTituloAndMensaje(
                @Param(value = "titulo") String titulo,
                @Param(value = "mensaje") String mensaje);




}

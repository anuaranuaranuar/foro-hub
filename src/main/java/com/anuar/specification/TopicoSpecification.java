package com.anuar.specification;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import com.anuar.foro_hub.domain.Curso;
import com.anuar.foro_hub.domain.Topico;

import jakarta.persistence.criteria.Join;

public class TopicoSpecification {

    public static Specification<Topico> hasNombre(String nombre) {
        return (root, query, cb) -> {
            if (!StringUtils.hasText(nombre))
                return null;
            
            String lower = nombre.toLowerCase();
            Join<Topico, Curso> join = root.join("curso");

            return cb.like(
                    cb.lower(join.get("nombre")),
                    "%" + lower + "%");
        };
    }

    public static Specification<Topico> hasYear(Integer year) {
        if (year == null)
            return null;
        return (root, query, cb) -> cb.equal(cb.function("YEAR", Integer.class, root.get("fechaCreacion")), year);
    }

}

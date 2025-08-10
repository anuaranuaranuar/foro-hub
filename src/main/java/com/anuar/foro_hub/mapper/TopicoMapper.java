package com.anuar.foro_hub.mapper;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Component;

import com.anuar.foro_hub.domain.Curso;
import com.anuar.foro_hub.domain.Topico;
import com.anuar.foro_hub.domain.Usuario;
import com.anuar.foro_hub.dto.TopicoPutDto;
import com.anuar.foro_hub.dto.request.TopicoDto;
import com.anuar.foro_hub.dto.response.TopicoDtoRes;

@Component
public class TopicoMapper {

    public Topico fromTopicDto(TopicoDto dto, Usuario autor, Curso curso) {
        return new Topico(
                null,
                dto.titulo(),
                dto.mensaje(),
                LocalDateTime.now(),
                true,
                autor,
                curso,
                List.of());
    }

    public TopicoDtoRes toTopicoDtoRes(Topico topico) {
        return new TopicoDtoRes(
            topico.getId(),
            topico.getTitulo(),
            topico.getMensaje(),
            topico.getFechaCreacion(),
            topico.getStatus(),
            topico.getAutor().getNombre(),
            topico.getCurso().getNombre());    
    }

    public Topico setTopico(TopicoPutDto dto, Topico topico) {
        topico.setTitulo(dto.titulo());
        topico.setMensaje(dto.mensaje());

        return topico;
    }

}

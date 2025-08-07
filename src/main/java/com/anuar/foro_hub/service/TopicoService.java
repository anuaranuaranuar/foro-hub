package com.anuar.foro_hub.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.anuar.foro_hub.domain.Curso;
import com.anuar.foro_hub.domain.Topico;
import com.anuar.foro_hub.domain.Usuario;
import com.anuar.foro_hub.dto.request.TopicoDto;
import com.anuar.foro_hub.dto.response.TopicoDtoRes;
import com.anuar.foro_hub.exception.CursoNotFoundException;
import com.anuar.foro_hub.exception.TopicAlreadyExistException;
import com.anuar.foro_hub.exception.UsuarioNotFoundException;
import com.anuar.foro_hub.mapper.TopicoMapper;
import com.anuar.foro_hub.repository.CursoRepository;
import com.anuar.foro_hub.repository.TopicoRepository;
import com.anuar.foro_hub.repository.UsuarioRepository;

import jakarta.transaction.Transactional;

@Service
public class TopicoService {

    private final UsuarioRepository usuarioRepository;
    private final CursoRepository cursoRepository;
    private final TopicoMapper topicoMapper;
    private final TopicoRepository topicoRepository;

    public TopicoService(UsuarioRepository usuarioRepository, CursoRepository cursoRepository,
            TopicoMapper topicoMapper, TopicoRepository topicoRepository) {
        this.usuarioRepository = usuarioRepository;
        this.cursoRepository = cursoRepository;
        this.topicoMapper = topicoMapper;
        this.topicoRepository = topicoRepository;
    }

    @Transactional
    public TopicoDtoRes create(TopicoDto topicoDto) {
        Optional<TopicoDto> topicoDB = topicoRepository.findByTituloAndMensaje(topicoDto.titulo(), topicoDto.mensaje());

        if (topicoDB.isPresent()) {
            throw new TopicAlreadyExistException("El topico que se intenta crear ya existe");

        } else {
            Curso curso = cursoRepository.findByNombre(topicoDto.curso())
                    .orElseThrow(() -> new CursoNotFoundException("Curso no existe"));

            Usuario autor = usuarioRepository.findByNombre(topicoDto.autor())
                    .orElseThrow(() -> new UsuarioNotFoundException("Usuario no existe"));

            Topico topico = topicoMapper.fromTopicDto(topicoDto, autor, curso);

            topico = topicoRepository.save(topico);

            return topicoMapper.toTopicoDtoRes(topico);
        }

    }

}

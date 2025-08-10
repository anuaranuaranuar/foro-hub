package com.anuar.foro_hub.service;

import java.util.Optional;

import org.flywaydb.core.internal.util.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.anuar.foro_hub.domain.Curso;
import com.anuar.foro_hub.domain.Topico;
import com.anuar.foro_hub.domain.Usuario;
import com.anuar.foro_hub.dto.TopicoPutDto;
import com.anuar.foro_hub.dto.request.TopicoDto;
import com.anuar.foro_hub.dto.response.TopicoDtoRes;
import com.anuar.foro_hub.exception.CursoNotFoundException;
import com.anuar.foro_hub.exception.TopicAlreadyExistException;
import com.anuar.foro_hub.exception.TopicoNotFound;
import com.anuar.foro_hub.exception.UsuarioNotFoundException;
import com.anuar.foro_hub.mapper.TopicoMapper;
import com.anuar.foro_hub.repository.CursoRepository;
import com.anuar.foro_hub.repository.TopicoRepository;
import com.anuar.foro_hub.repository.UsuarioRepository;
import com.anuar.foro_hub.specification.TopicoSpecification;


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

    
    public TopicoDtoRes create(TopicoDto topicoDto) {

        Optional<TopicoDto> topicoDB = topicoRepository.findByTituloAndMensaje(topicoDto.titulo(), topicoDto.mensaje());

        if (topicoDB.isPresent()) {
            throw new TopicAlreadyExistException("El topico que se intenta crear ya existe");

        } else {
            Curso curso = cursoRepository.findById(topicoDto.curso())
                    .orElseThrow(() -> new CursoNotFoundException("Curso no existe"));

            Usuario autor = usuarioRepository.findById(topicoDto.autor())
                    .orElseThrow(() -> new UsuarioNotFoundException("Usuario no existe"));

            Topico topico = topicoMapper.fromTopicDto(topicoDto, autor, curso);

            topico = topicoRepository.save(topico);

            return topicoMapper.toTopicoDtoRes(topico);
        }
    }

    @Transactional(readOnly = true)
    public Page<TopicoDtoRes> getByPage(String curso, Integer year, Pageable pageable) {
        Specification<Topico> spec = (root, query, cb) -> cb.isTrue(root.get("status"));

            if(StringUtils.hasText(curso))
            spec = spec.and(TopicoSpecification.hasNombre(curso));

            if (year != null)
            spec = spec.and(TopicoSpecification.hasYear(year));    
            
        Page<Topico> topicos = topicoRepository.findAll(spec, pageable);

        return topicos.map(topicoMapper::toTopicoDtoRes);
    }

    @Transactional(readOnly = true)
    public TopicoDtoRes getById(Long id) {
        Topico topic = topicoRepository.findById(id)
        .orElseThrow(() -> new TopicoNotFound("no se encontro el topico con el id" + id));
        
        return topicoMapper.toTopicoDtoRes(topic);
    }
    @Transactional
    public TopicoDtoRes update(Long id, TopicoPutDto dto) {
        Topico topico = topicoRepository.findById(id)
        .orElseThrow(() -> new TopicoNotFound("El topico con el id " + id + "no existe"));
    
        Topico updated = topicoMapper.setTopico(dto, topico);

        updated = topicoRepository.save(updated);

        return topicoMapper.toTopicoDtoRes(updated);
    }

    @Transactional
    public void disable(Long id) {
        Topico topico = topicoRepository.findById(id)
        .orElseThrow(() -> new TopicoNotFound("El topico con el id " + id + "no existe"));

        topico.setStatus(false);

        topicoRepository.save(topico);
    }
}

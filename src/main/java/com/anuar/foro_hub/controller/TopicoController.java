package com.anuar.foro_hub.controller;

import java.net.URI;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.HeadersBuilder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.anuar.foro_hub.dto.TopicoPutDto;
import com.anuar.foro_hub.dto.request.TopicoDto;
import com.anuar.foro_hub.dto.response.TopicoDtoRes;
import com.anuar.foro_hub.service.TopicoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    private final TopicoService topicoService;

    public TopicoController(TopicoService topicoService) {
        this.topicoService = topicoService;
    }

    @PostMapping
    public ResponseEntity<TopicoDtoRes> create(@RequestBody @Valid TopicoDto topicDto) {
        TopicoDtoRes topicoRes = topicoService.create(topicDto);

        URI url = URI.create("/topicos");

        return ResponseEntity.created(url).body(topicoRes);
    }

    @GetMapping
    public ResponseEntity<Page<TopicoDtoRes>> getByPage(
            @RequestParam(required = false) String curso,
            @RequestParam(required = false) Integer year,
            @PageableDefault(size = 10, sort = "fechaCreacion", direction = Direction.ASC) 
            Pageable pageable) {
        Page<TopicoDtoRes> topicos = topicoService.getByPage(curso, year, pageable);
        return ResponseEntity.ok(topicos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TopicoDtoRes>  getById(@PathVariable Long id){
        TopicoDtoRes res = topicoService.getById(id);
        return ResponseEntity.ok(res);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TopicoDtoRes> update(
        @PathVariable Long id,
        @RequestBody @Valid TopicoPutDto bodyRequired){

        return ResponseEntity.ok(topicoService.update(id, bodyRequired));
    }

    @PutMapping("/{id}")
    public HeadersBuilder<?> disable(@PathVariable Long id){
      topicoService.disable(id);

        return ResponseEntity.noContent();
    }


}

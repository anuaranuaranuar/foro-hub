package com.anuar.foro_hub.controller;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}

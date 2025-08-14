package com.anuar.foro_hub.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.anuar.foro_hub.dto.request.UsuarioDto;
import com.anuar.foro_hub.dto.response.UsuarioDtoRes;
import com.anuar.foro_hub.service.UsuarioService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping
    public ResponseEntity<UsuarioDtoRes> registarse(@RequestBody @Valid UsuarioDto dto) {
        
        return ResponseEntity.ok(usuarioService.create(dto));
    }
}

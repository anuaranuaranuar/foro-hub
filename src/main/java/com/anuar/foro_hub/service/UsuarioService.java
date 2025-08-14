package com.anuar.foro_hub.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.anuar.foro_hub.dto.request.UsuarioDto;
import com.anuar.foro_hub.dto.response.UsuarioDtoRes;
import com.anuar.foro_hub.mapper.UsuarioMapper;
import com.anuar.foro_hub.repository.UsuarioRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioMapper usuarioMapper;
    private final PasswordEncoder passwordEncoder;

    public UsuarioDtoRes create(UsuarioDto dto) {
        var usuario = usuarioMapper.fromUsuarioDto(dto);

        usuario.setContrasena(passwordEncoder.encode(usuario.getContrasena()));
        
        var usuarioRes = usuarioRepository.save(usuario);

        return usuarioMapper.toUsuarioDtoRes(usuarioRes);
    }

}

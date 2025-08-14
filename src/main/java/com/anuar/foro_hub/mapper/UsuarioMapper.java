package com.anuar.foro_hub.mapper;

import org.springframework.stereotype.Component;

import com.anuar.foro_hub.domain.Usuario;
import com.anuar.foro_hub.dto.request.UsuarioDto;
import com.anuar.foro_hub.dto.response.UsuarioDtoRes;

@Component
public class UsuarioMapper {

    public Usuario fromUsuarioDto(UsuarioDto dto) {
        return new Usuario(
                null,
                dto.nombre(),
                dto.correoElectronico(),
                dto.contrasena(),
                null);
    }

    public UsuarioDtoRes toUsuarioDtoRes(Usuario usuario) {
        return new UsuarioDtoRes(
            usuario.getId(),
            usuario.getNombre(),
            usuario.getCorreoElectronico(),
            usuario.getPassword()
            );    
    }

}

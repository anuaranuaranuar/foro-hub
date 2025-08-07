package com.anuar.foro_hub.dto.request;

import java.util.Set;

import com.anuar.foro_hub.domain.Perfil;

public record UsuarioDto(

        String nombre,

        String correoElectronico,

        String contrasena,

        Set<Perfil> perfiles) {
}

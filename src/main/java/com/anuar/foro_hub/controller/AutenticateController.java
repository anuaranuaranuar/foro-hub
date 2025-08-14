package com.anuar.foro_hub.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.anuar.foro_hub.domain.Usuario;
import com.anuar.foro_hub.dto.request.UsuarioDto;
import com.anuar.foro_hub.dto.response.DatosToken;
import com.anuar.foro_hub.service.TokenService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/login")
@RequiredArgsConstructor
public class AutenticateController {

    private final AuthenticationManager manager;
    private final TokenService tokenService;



    @PostMapping
    public ResponseEntity<DatosToken> registrarse(@RequestBody @Valid UsuarioDto usuarioDto) {

        var token = new UsernamePasswordAuthenticationToken(
                usuarioDto.correoElectronico(),
                usuarioDto.contrasena());
        
        Authentication authentication = manager.authenticate(token);
    
        var tokenJWT = tokenService.generateToken((Usuario) authentication.getPrincipal());

        return ResponseEntity.ok(new DatosToken(tokenJWT));
    }

}

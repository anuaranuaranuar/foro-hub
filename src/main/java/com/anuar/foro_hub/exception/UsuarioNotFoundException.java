package com.anuar.foro_hub.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class UsuarioNotFoundException extends RuntimeException{

    public UsuarioNotFoundException(String message){
        super(message);
    }
}

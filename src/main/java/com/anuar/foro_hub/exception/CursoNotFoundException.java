package com.anuar.foro_hub.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class CursoNotFoundException extends RuntimeException{

    public CursoNotFoundException(String message){
        super(message);
    }
}
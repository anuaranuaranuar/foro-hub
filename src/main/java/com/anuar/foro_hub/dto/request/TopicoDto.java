package com.anuar.foro_hub.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record TopicoDto(
    @NotBlank
    String titulo,

    @NotBlank
    String mensaje,
    
    @NotNull
    Long autor,
    
    @NotNull
    Long curso
) {
} 


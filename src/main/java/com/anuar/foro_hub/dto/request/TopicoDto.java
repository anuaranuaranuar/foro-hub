package com.anuar.foro_hub.dto.request;

import jakarta.validation.constraints.NotBlank;

public record TopicoDto(
    @NotBlank
    String titulo,

    @NotBlank
    String mensaje,
    
    @NotBlank
    String autor,
    
    @NotBlank
    String curso
) {
} 


package com.anuar.foro_hub.dto.response;

import java.time.LocalDateTime;

public record TopicoDtoRes(
    Long id,
    
    String titulo,

    String mensaje,

    LocalDateTime fechaCreacion,

    Boolean status,
    
    String autor,
    
    String curso
) {
}
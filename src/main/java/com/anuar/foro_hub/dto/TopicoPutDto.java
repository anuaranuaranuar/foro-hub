package com.anuar.foro_hub.dto;

import jakarta.validation.constraints.NotBlank;

public record TopicoPutDto(
        @NotBlank 
        String titulo,
        @NotBlank 
        String mensaje) {
}
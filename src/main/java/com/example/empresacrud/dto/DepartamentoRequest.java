package com.example.empresacrud.dto;

import jakarta.validation.constraints.NotBlank;

public record DepartamentoRequest(
        @NotBlank(message = "El nombre no puede estar vacio")
        String nombre,
        String descripcion
) {
}

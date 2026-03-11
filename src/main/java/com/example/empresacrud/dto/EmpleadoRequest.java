package com.example.empresacrud.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record EmpleadoRequest(
        @NotBlank(message = "El nombre no puede estar vacio")
        String nombre,
        @NotBlank(message = "El apellido no puede estar vacio")
        String apellido,
        @NotBlank(message = "El correo no puede estar vacio")
        String correo,
        @Min(value = 1, message = "El salario debe ser mayor que cero")
        double salario,
        @NotNull(message = "El departamento es obligatorio")
        Long departamentoId
) {
}

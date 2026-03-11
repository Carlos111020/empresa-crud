package com.example.empresacrud.dto;

public record EmpleadoResumenDto(
        Long id,
        String nombre,
        String apellido,
        String correo
) {
}

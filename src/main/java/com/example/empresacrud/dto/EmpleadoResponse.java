package com.example.empresacrud.dto;

public record EmpleadoResponse(
        Long id,
        String nombre,
        String apellido,
        String correo,
        double salario,
        DepartamentoResumenDto departamento
) {
}

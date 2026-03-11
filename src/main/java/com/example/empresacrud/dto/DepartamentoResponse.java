package com.example.empresacrud.dto;

import java.util.List;

public record DepartamentoResponse(
        Long id,
        String nombre,
        String descripcion,
        List<EmpleadoResumenDto> empleados
) {
}

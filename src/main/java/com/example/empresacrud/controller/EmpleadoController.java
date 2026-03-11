package com.example.empresacrud.controller;
import com.example.empresacrud.dto.EmpleadoRequest;
import com.example.empresacrud.dto.EmpleadoResponse;
import com.example.empresacrud.service.EmpleadoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/empleados")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor

public class EmpleadoController {

    private final EmpleadoService empleadoService;

    @GetMapping("/all")
    public List<EmpleadoResponse> listarTodos(){
        return empleadoService.listarTodos();
    }

    @GetMapping("/{id}")
    public EmpleadoResponse buscarPorId (@PathVariable Long id){
        return empleadoService.buscarPorId(id);
    }

    @PostMapping("/crear")
    public EmpleadoResponse crearEmpleado (@Valid @RequestBody EmpleadoRequest empleado){
        return empleadoService.guardar(empleado);
    }

    @PutMapping("/{id}")
    public EmpleadoResponse actualizarEmpleado (@PathVariable Long id, @Valid @RequestBody EmpleadoRequest empleado){
        return empleadoService.actualizar(id, empleado);
    }

    @DeleteMapping("/{id}")
    public void eliminarEmpleado (@PathVariable Long id){
        empleadoService.eliminar(id);
    }
}

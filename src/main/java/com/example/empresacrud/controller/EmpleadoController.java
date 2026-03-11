package com.example.empresacrud.controller;
import com.example.empresacrud.model.Departamento;
import com.example.empresacrud.model.Empleado;
import com.example.empresacrud.service.DepartamentoService;
import com.example.empresacrud.service.EmpleadoService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/empleados")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor

public class EmpleadoController {

    private final EmpleadoService empleadoService;

    @GetMapping("/all")
    public List<Empleado> listarTodos(){
        return empleadoService.listarTodos();
    }

    @GetMapping("/{id}")
    public Optional<Empleado> buscarPorId (@PathVariable Long id){
        return empleadoService.buscarPorId(id);
    }

    @PostMapping("/crear")
    public Empleado crearEmpleado (@RequestBody Empleado empleado){
        return empleadoService.guardar(empleado);
    }

    @PutMapping("/{id}")
    public Empleado actualizarEmpleado (@PathVariable Long id, @RequestBody Empleado empleado){
        return empleadoService.actualizar(id, empleado);
    }

    @DeleteMapping("/{id}")
    public void eliminarEmpleado (@PathVariable Long id){
        empleadoService.eliminar(id);
    }
}

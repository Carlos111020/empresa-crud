package com.example.empresacrud.controller;

import com.example.empresacrud.dto.DepartamentoRequest;
import com.example.empresacrud.dto.DepartamentoResponse;
import com.example.empresacrud.service.DepartamentoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/departamentos")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor

public class DepartamentoController {


    private final DepartamentoService departamentoService;

    @GetMapping("/all")
    public List<DepartamentoResponse> listarTodos (){
        return departamentoService.listarTodos();
    }

    @GetMapping("/{id}")
    public DepartamentoResponse buscarPorId (@PathVariable Long id){
        return departamentoService.buscarporId(id);
    }

    @PostMapping("/crear")
    public DepartamentoResponse guardar (@Valid @RequestBody DepartamentoRequest departamento){
        return departamentoService.guardar(departamento);

    }

    @PutMapping("/{id}")
    public DepartamentoResponse actualizar (@PathVariable Long id, @Valid @RequestBody DepartamentoRequest departamento){
        return departamentoService.actualizar(id, departamento);
    }

    @DeleteMapping("/{id}")
    public  void eliminar (@PathVariable Long id){
        departamentoService.eliminar(id);
    }



}

package com.example.empresacrud.controller;

import com.example.empresacrud.model.Departamento;
import com.example.empresacrud.service.DepartamentoService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/departamentos")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor

public class DepartamentoController {


    private final DepartamentoService departamentoService;

    @GetMapping
    public List<Departamento> listarTodos (){
        return departamentoService.listarTodos();
    }

    @GetMapping("/{id}")
    public Optional<Departamento> buscarPorId (@PathVariable Long id){
        return departamentoService.buscarporId(id);
    }

    @PostMapping
    public Departamento guardar (@RequestBody Departamento departamento){
        return departamentoService.guardar(departamento);

    }

    @PutMapping("/{id}")
    public Departamento actualizar (@PathVariable Long id, @RequestBody Departamento departamento){
        return departamentoService.actualizar(id, departamento);
    }

    @DeleteMapping("/{id}")
    public  void eliminar (@PathVariable Long id){
        departamentoService.eliminar(id);
    }



}

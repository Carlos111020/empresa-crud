package com.example.empresacrud.service;


import com.example.empresacrud.model.Departamento;
import com.example.empresacrud.repository.DepartamentoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DepartamentoService {



    private final DepartamentoRepository departamentoRespository;

    public List<Departamento> listarTodos(){
        return departamentoRespository.findAll();
    }

    public Optional <Departamento> buscarporId (Long id){
        return departamentoRespository.findById(id);
    }

    public Departamento guardar(Departamento departamento){
        return departamentoRespository.save(departamento);
    }
    public Departamento actualizar (Long id, Departamento departamentoActualizado){
        Optional<Departamento> departamentoExistente = departamentoRespository.findById(id);

        if (departamentoExistente.isPresent()){
            Departamento departamento = departamentoExistente.get();
            departamento.setNombre(departamentoActualizado.getNombre());
            departamento.setEmpleados(departamentoActualizado.getEmpleados());
            departamento.setDescripcion(departamentoActualizado.getDescripcion());
            return departamentoRespository.save(departamento);
        }
        else {
            System.out.println("Error al actualizar el departamento");
            return null;
        }


    }
    public void eliminar (Long id){
        departamentoRespository.deleteById(id);
    }



}

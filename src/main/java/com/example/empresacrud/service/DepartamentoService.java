package com.example.empresacrud.service;


import com.example.empresacrud.dto.DepartamentoRequest;
import com.example.empresacrud.dto.DepartamentoResponse;
import com.example.empresacrud.model.Departamento;
import com.example.empresacrud.repository.DepartamentoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
@RequiredArgsConstructor
public class DepartamentoService {



    private final DepartamentoRepository departamentoRespository;

    public List<DepartamentoResponse> listarTodos(){
        List<Departamento> departamentos = departamentoRespository.findAll();
        List<DepartamentoResponse> respuesta = new ArrayList<>();

        for (Departamento departamento : departamentos) {
            respuesta.add(new DepartamentoResponse(
                    departamento.getId(),
                    departamento.getNombre(),
                    departamento.getDescripcion()
            ));
        }

        return respuesta;
    }

    public DepartamentoResponse buscarporId(Long id){
        Departamento departamento = departamentoRespository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Departamento no encontrado"));

        return new DepartamentoResponse(
                departamento.getId(),
                departamento.getNombre(),
                departamento.getDescripcion()
        );
    }

    public DepartamentoResponse guardar(DepartamentoRequest request){
        Departamento departamento = new Departamento();
        departamento.setNombre(request.nombre());
        departamento.setDescripcion(request.descripcion());
        Departamento guardado = departamentoRespository.save(departamento);

        return new DepartamentoResponse(
                guardado.getId(),
                guardado.getNombre(),
                guardado.getDescripcion()
        );
    }

    public DepartamentoResponse actualizar(Long id, DepartamentoRequest request){
        Departamento departamento = departamentoRespository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Departamento no encontrado"));

        departamento.setNombre(request.nombre());
        departamento.setDescripcion(request.descripcion());
        Departamento actualizado = departamentoRespository.save(departamento);

        return new DepartamentoResponse(
                actualizado.getId(),
                actualizado.getNombre(),
                actualizado.getDescripcion()
        );
    }

    public void eliminar (Long id){
        if (!departamentoRespository.existsById(id)) {
            throw new ResponseStatusException(NOT_FOUND, "Departamento no encontrado");
        }
        departamentoRespository.deleteById(id);
    }

}

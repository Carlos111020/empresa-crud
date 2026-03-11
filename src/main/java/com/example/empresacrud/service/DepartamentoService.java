package com.example.empresacrud.service;


import com.example.empresacrud.dto.DepartamentoRequest;
import com.example.empresacrud.dto.DepartamentoResponse;
import com.example.empresacrud.dto.EmpleadoResumenDto;
import com.example.empresacrud.model.Departamento;
import com.example.empresacrud.repository.DepartamentoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collections;
import java.util.List;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
@RequiredArgsConstructor
public class DepartamentoService {



    private final DepartamentoRepository departamentoRespository;

    public List<DepartamentoResponse> listarTodos(){
        return departamentoRespository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public DepartamentoResponse buscarporId(Long id){
        return departamentoRespository.findById(id)
                .map(this::toResponse)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Departamento no encontrado"));
    }

    public DepartamentoResponse guardar(DepartamentoRequest request){
        Departamento departamento = new Departamento();
        departamento.setNombre(request.nombre());
        departamento.setDescripcion(request.descripcion());
        return toResponse(departamentoRespository.save(departamento));
    }

    public DepartamentoResponse actualizar(Long id, DepartamentoRequest request){
        Departamento departamento = departamentoRespository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Departamento no encontrado"));

        departamento.setNombre(request.nombre());
        departamento.setDescripcion(request.descripcion());
        return toResponse(departamentoRespository.save(departamento));
    }

    public void eliminar (Long id){
        if (!departamentoRespository.existsById(id)) {
            throw new ResponseStatusException(NOT_FOUND, "Departamento no encontrado");
        }
        departamentoRespository.deleteById(id);
    }

    private DepartamentoResponse toResponse(Departamento departamento) {
        List<EmpleadoResumenDto> empleados = departamento.getEmpleados() == null
                ? Collections.emptyList()
                : departamento.getEmpleados()
                .stream()
                .map(empleado -> new EmpleadoResumenDto(
                        empleado.getId(),
                        empleado.getNombre(),
                        empleado.getApellido(),
                        empleado.getCorreo()
                ))
                .toList();

        return new DepartamentoResponse(
                departamento.getId(),
                departamento.getNombre(),
                departamento.getDescripcion(),
                empleados
        );
    }


}

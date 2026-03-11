package com.example.empresacrud.service;
import com.example.empresacrud.dto.EmpleadoRequest;
import com.example.empresacrud.dto.EmpleadoResponse;
import com.example.empresacrud.model.Departamento;
import com.example.empresacrud.model.Empleado;
import com.example.empresacrud.repository.DepartamentoRepository;
import com.example.empresacrud.repository.EmpleadoRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
@RequiredArgsConstructor
public class EmpleadoService {
    private final EmpleadoRepository empleadoRepository;

    private final DepartamentoRepository departamentoRepository;

    public List<EmpleadoResponse> listarTodos() {
        List<Empleado> empleados = empleadoRepository.findAll();
        List<EmpleadoResponse> respuesta = new ArrayList<>();

        for (Empleado empleado : empleados) {
            Long departamentoId = null;
            String departamentoNombre = null;

            if (empleado.getDepartamento() != null) {
                departamentoId = empleado.getDepartamento().getId();
                departamentoNombre = empleado.getDepartamento().getNombre();
            }

            respuesta.add(new EmpleadoResponse(
                    empleado.getId(),
                    empleado.getNombre(),
                    empleado.getApellido(),
                    empleado.getCorreo(),
                    empleado.getSalario(),
                    departamentoId,
                    departamentoNombre
            ));
        }

        return respuesta;
    }

    public EmpleadoResponse buscarPorId(Long id) {
        Empleado empleado = empleadoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Empleado no encontrado"));

        Long departamentoId = null;
        String departamentoNombre = null;

        if (empleado.getDepartamento() != null) {
            departamentoId = empleado.getDepartamento().getId();
            departamentoNombre = empleado.getDepartamento().getNombre();
        }

        return new EmpleadoResponse(
                empleado.getId(),
                empleado.getNombre(),
                empleado.getApellido(),
                empleado.getCorreo(),
                empleado.getSalario(),
                departamentoId,
                departamentoNombre
        );
    }

    public EmpleadoResponse guardar(EmpleadoRequest request) {
        Departamento departamento = buscarDepartamento(request.departamentoId());
        Empleado empleadoNuevo = new Empleado();
        empleadoNuevo.setNombre(request.nombre());
        empleadoNuevo.setApellido(request.apellido());
        empleadoNuevo.setCorreo(request.correo());
        empleadoNuevo.setSalario(request.salario());
        empleadoNuevo.setDepartamento(departamento);
        Empleado guardado = empleadoRepository.save(empleadoNuevo);

        return new EmpleadoResponse(
                guardado.getId(),
                guardado.getNombre(),
                guardado.getApellido(),
                guardado.getCorreo(),
                guardado.getSalario(),
                departamento.getId(),
                departamento.getNombre()
        );
    }

    public EmpleadoResponse actualizar(Long id, EmpleadoRequest request) {
        Empleado empleado = empleadoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Empleado no encontrado"));

        empleado.setNombre(request.nombre());
        empleado.setApellido(request.apellido());
        empleado.setCorreo(request.correo());
        empleado.setSalario(request.salario());
        Departamento departamento = buscarDepartamento(request.departamentoId());
        empleado.setDepartamento(departamento);

        Empleado actualizado = empleadoRepository.save(empleado);

        return new EmpleadoResponse(
                actualizado.getId(),
                actualizado.getNombre(),
                actualizado.getApellido(),
                actualizado.getCorreo(),
                actualizado.getSalario(),
                departamento.getId(),
                departamento.getNombre()
        );
    }

    public void eliminar(Long id){
        if (!empleadoRepository.existsById(id)) {
            throw new ResponseStatusException(NOT_FOUND, "Empleado no encontrado");
        }
        empleadoRepository.deleteById(id);
    }

    private Departamento buscarDepartamento(Long departamentoId) {
        return departamentoRepository.findById(departamentoId)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Departamento no encontrado"));
    }

}

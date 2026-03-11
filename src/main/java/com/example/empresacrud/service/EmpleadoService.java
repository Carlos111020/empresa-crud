package com.example.empresacrud.service;
import com.example.empresacrud.dto.DepartamentoResumenDto;
import com.example.empresacrud.dto.EmpleadoRequest;
import com.example.empresacrud.dto.EmpleadoResponse;
import com.example.empresacrud.model.Departamento;
import com.example.empresacrud.model.Empleado;
import com.example.empresacrud.repository.DepartamentoRepository;
import com.example.empresacrud.repository.EmpleadoRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
@RequiredArgsConstructor
public class EmpleadoService {
    private final EmpleadoRepository empleadoRepository;

    private final DepartamentoRepository departamentoRepository;

    public List<EmpleadoResponse> listarTodos() {
        return empleadoRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public EmpleadoResponse buscarPorId(Long id) {
        return empleadoRepository.findById(id)
                .map(this::toResponse)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Empleado no encontrado"));
    }

    public EmpleadoResponse guardar(EmpleadoRequest request) {
        Departamento departamento = buscarDepartamento(request.departamentoId());
        Empleado empleadoNuevo = new Empleado();
        empleadoNuevo.setNombre(request.nombre());
        empleadoNuevo.setApellido(request.apellido());
        empleadoNuevo.setCorreo(request.correo());
        empleadoNuevo.setSalario(request.salario());
        empleadoNuevo.setDepartamento(departamento);
        return toResponse(empleadoRepository.save(empleadoNuevo));
    }

    public EmpleadoResponse actualizar(Long id, EmpleadoRequest request) {
        Empleado empleado = empleadoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Empleado no encontrado"));

        empleado.setNombre(request.nombre());
        empleado.setApellido(request.apellido());
        empleado.setCorreo(request.correo());
        empleado.setSalario(request.salario());
        empleado.setDepartamento(buscarDepartamento(request.departamentoId()));

        return toResponse(empleadoRepository.save(empleado));
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

    private EmpleadoResponse toResponse(Empleado empleado) {
        Departamento departamento = empleado.getDepartamento();
        DepartamentoResumenDto departamentoDto = departamento == null
                ? null
                : new DepartamentoResumenDto(departamento.getId(), departamento.getNombre());

        return new EmpleadoResponse(
                empleado.getId(),
                empleado.getNombre(),
                empleado.getApellido(),
                empleado.getCorreo(),
                empleado.getSalario(),
                departamentoDto
        );
    }

}

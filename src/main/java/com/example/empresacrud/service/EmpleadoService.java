package com.example.empresacrud.service;
import com.example.empresacrud.model.Departamento;
import com.example.empresacrud.model.Empleado;
import com.example.empresacrud.repository.DepartamentoRepository;
import com.example.empresacrud.repository.EmpleadoRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmpleadoService {
    private final EmpleadoRepository empleadoRepository;

    private final DepartamentoRepository departamentoRepository;

    public List<Empleado> listarTodos() {
        return empleadoRepository.findAll();
    }

    public Optional<Empleado> buscarPorId(Long id) {
        return empleadoRepository.findById(id);
    }

    public Empleado guardar(Empleado empleadoNuevo) {
        if (empleadoNuevo.getDepartamento() != null && empleadoNuevo.getDepartamento().getId() != null) {
            Optional<Departamento> departamento = departamentoRepository.findById(empleadoNuevo.getDepartamento().getId());
            departamento.ifPresent(empleadoNuevo::setDepartamento);

        }else{
            System.out.println("No se pudo guardar el empleado");
        }
        System.out.println("Empleado creado con existo");
        return empleadoRepository.save(empleadoNuevo);
    }

    public Empleado actualizar(Long id, Empleado empleadoActualizado) {
        Optional<Empleado> empleadoExistente = empleadoRepository.findById(id);
        if (empleadoExistente.isPresent()) {
            Empleado empleado = empleadoExistente.get();

            empleado.setNombre(empleadoActualizado.getNombre());
            empleado.setDepartamento(empleadoActualizado.getDepartamento());
            empleado.setApellido(empleadoActualizado.getApellido());
            empleado.setSalario(empleadoActualizado.getSalario());
            empleado.setCorreo(empleadoActualizado.getCorreo());


            if (empleadoActualizado.getDepartamento() != null && empleadoActualizado.getDepartamento().getId() != null) {
                Optional <Departamento> departamento = departamentoRepository.findById(empleadoActualizado.getDepartamento().getId());
                departamento.ifPresent(empleado::setDepartamento);

            }
            System.out.println("Empleado actualizado con exito");
            return empleadoRepository.save(empleado);
        }
        else {
            System.out.println("Error al actualizar el empleado");
            return null;
        }
        }

        public void eliminar (Long id){
        departamentoRepository.deleteById(id);
        }

}
package com.example.clinicaOdontologica.service.Impl;

import com.example.clinicaOdontologica.model.Paciente;
import com.example.clinicaOdontologica.repository.PacienteRepository;
import com.example.clinicaOdontologica.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PacienteServiceImpl implements PacienteService {

    private final PacienteRepository pacienteRepository;

    @Autowired
    public PacienteServiceImpl(PacienteRepository pacienteRepository) {
        this.pacienteRepository = pacienteRepository;
    }

    @Override
    public Paciente registrarPaciente(Paciente paciente) {
        return pacienteRepository.save(paciente);
    }

    @Override
    public Optional<Paciente> buscar(Integer id) {
        return pacienteRepository.findById(id);
    }

    @Override
    public List<Paciente> buscarTodos() {
        return pacienteRepository.findAll();
    }

    @Override
    public void eliminar(Integer id) {
        pacienteRepository.deleteById(id);
    }

    @Override
    public Paciente actualizar(Paciente paciente) {
        Optional<Paciente> paciente1 = buscar(paciente.getId());

        paciente1.get().setNombre(paciente.getNombre());
        paciente1.get().setApellido(paciente.getApellido());
        paciente1.get().setDni(paciente.getDni());
        paciente1.get().setFechaIngreso(paciente.getFechaIngreso());
        paciente1.get().setDomicilio(paciente.getDomicilio());

        return pacienteRepository.save(paciente1.get());
    }
}

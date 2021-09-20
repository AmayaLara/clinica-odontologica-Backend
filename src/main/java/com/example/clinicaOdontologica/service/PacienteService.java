package com.example.clinicaOdontologica.service;

import com.example.clinicaOdontologica.model.Paciente;

import java.util.List;
import java.util.Optional;

public interface PacienteService {

    Paciente registrarPaciente(Paciente paciente);
    Optional<Paciente> buscar(Integer id);
    List<Paciente> buscarTodos();
    void eliminar(Integer id);
    Paciente actualizar(Paciente paciente);
}

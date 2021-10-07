package com.example.clinicaOdontologica.service;

import com.example.clinicaOdontologica.model.Turno;

import java.util.List;
import java.util.Optional;

public interface TurnoService {

    Turno agendarTurno(Turno turno);
    Optional<Turno> buscar(Integer id);
    List<Turno> listarTodos();
    void eliminar(Integer id);
    Turno actualizar(Turno turno);

}

package com.example.clinicaOdontologica.service;

import com.example.clinicaOdontologica.model.Domicilio;

import java.util.List;
import java.util.Optional;

public interface DomicilioService {

    Domicilio guardar(Domicilio d);
    Optional<Domicilio> buscar(Integer id);
    List<Domicilio> buscarTodos();
    void eliminar(Integer id);
    public Domicilio actualizar(Domicilio domicilio);
}

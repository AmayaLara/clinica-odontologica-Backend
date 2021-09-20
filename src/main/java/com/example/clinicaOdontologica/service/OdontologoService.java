package com.example.clinicaOdontologica.service;

import com.example.clinicaOdontologica.model.Odontologo;

import java.util.List;
import java.util.Optional;

public interface OdontologoService {

    Odontologo registrarOdontologo(Odontologo odontologo);
    Optional<Odontologo> buscar(Integer id);
    List<Odontologo> buscarTodos();
    void eliminar(Integer id);
    Odontologo actualizar(Odontologo odontologo);

}

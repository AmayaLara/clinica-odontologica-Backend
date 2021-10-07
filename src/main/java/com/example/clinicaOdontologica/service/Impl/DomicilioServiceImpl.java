package com.example.clinicaOdontologica.service.Impl;

import com.example.clinicaOdontologica.model.Domicilio;
import com.example.clinicaOdontologica.repository.DomicilioRepository;
import com.example.clinicaOdontologica.service.DomicilioService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class DomicilioServiceImpl implements DomicilioService {

    @Autowired
    private DomicilioRepository domicilioRepository;

    @Override
    public Domicilio guardar(Domicilio d) {
        return domicilioRepository.save(d);
    }

    @Override
    public Optional<Domicilio> buscar(Integer id) {
        return domicilioRepository.findById(id);
    }

    @Override
    public List<Domicilio> buscarTodos() {
        return domicilioRepository.findAll();
    }

    @Override
    public void eliminar(Integer id) {
        domicilioRepository.deleteById(id);
    }

    @Override
    public Domicilio actualizar(Domicilio domicilio) {
        Optional<Domicilio> domicilio1 = buscar(domicilio.getId());

        domicilio1.get().setCalle(domicilio.getCalle());
        domicilio1.get().setNumero(domicilio.getNumero());
        domicilio1.get().setLocalidad(domicilio.getLocalidad());
        domicilio1.get().setProvincia(domicilio.getProvincia());

        return domicilioRepository.save(domicilio1.get());
    }
}

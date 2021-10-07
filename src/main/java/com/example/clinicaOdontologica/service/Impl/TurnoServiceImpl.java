package com.example.clinicaOdontologica.service.Impl;

import com.example.clinicaOdontologica.model.Turno;
import com.example.clinicaOdontologica.repository.TurnoRepository;
import com.example.clinicaOdontologica.service.TurnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TurnoServiceImpl implements TurnoService {

    @Autowired
    private TurnoRepository turnoRepository;

    @Override
    public Turno agendarTurno(Turno turno) {
        return turnoRepository.save(turno);
    }

    @Override
    public Optional<Turno> buscar(Integer id) {
        return turnoRepository.findById(id);
    }

    @Override
    public List<Turno> listarTodos() {
        return turnoRepository.findAll();
    }

    @Override
    public void eliminar(Integer id) {
        turnoRepository.deleteById(id);
    }

    @Override
    public Turno actualizar(Turno turno) {
        Optional<Turno> turno1 = buscar(turno.getId());
        if(turno1 == null){
            return  null;
        }
        return turnoRepository.save(turno1.get());
    }
}

package com.example.clinicaOdontologica.service.Impl;

import com.example.clinicaOdontologica.model.Odontologo;
import com.example.clinicaOdontologica.repository.OdontologoRepository;
import com.example.clinicaOdontologica.service.OdontologoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OdontologoServiceImpl implements OdontologoService {

    @Autowired
    private OdontologoRepository odontologoRepository;

    @Override
    public Odontologo registrarOdontologo(Odontologo odontologo) {
        return odontologoRepository.save(odontologo);
    }

    @Override
    public Optional<Odontologo> buscar(Integer id) {
        return odontologoRepository.findById(id);
    }

    @Override
    public List<Odontologo> buscarTodos() {
        return odontologoRepository.findAll();
    }

    @Override
    public void eliminar(Integer id) {
        odontologoRepository.deleteById(id);
    }

    @Override
    public Odontologo actualizar(Odontologo odontologo) {
        Optional<Odontologo> odontologo1 = buscar(odontologo.getId());

        odontologo1.get().setMatricula(odontologo.getMatricula());
        odontologo1.get().setNombre(odontologo.getNombre());
        odontologo1.get().setApellido(odontologo.getApellido());
        return odontologoRepository.save(odontologo1.get());
    }
}

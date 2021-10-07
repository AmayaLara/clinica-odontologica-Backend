package com.example.clinicaOdontologica.service.Impl;

import com.example.clinicaOdontologica.model.Paciente;
import com.example.clinicaOdontologica.service.PacienteService;
import org.junit.FixMethodOrder;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringRunner.class)
@SpringBootTest
class PacienteServiceImplTest {

    @Autowired
    PacienteService pacienteService;

    Paciente paciente = new Paciente();

    @Test
    public void crearPaciente() {
        try {
            paciente.setNombre("Lara");
            paciente.setApellido("Amaya");
            paciente.setDni("435987");
            paciente.setFechaIngreso("2020-05-03");
            paciente.getDomicilio().setCalle("colombres");
            paciente.getDomicilio().setNumero("1234");
            paciente.getDomicilio().setLocalidad("rosario");
            paciente.getDomicilio().setProvincia("santa fe");

        pacienteService.registrarPaciente(paciente);
        Optional<Paciente> pacienteEncontrado = pacienteService.buscar(1);

        assertEquals(paciente.getNombre(), pacienteEncontrado.get().getNombre());
        assertEquals(paciente.getDomicilio().getCalle(), pacienteEncontrado.get().getDomicilio().getCalle());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Test
    public void eliminarPaciente(){
        try{
            pacienteService.eliminar(paciente.getId());
            assertTrue(paciente.getId() == null);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }


}
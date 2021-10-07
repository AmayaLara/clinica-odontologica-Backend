package com.example.clinicaOdontologica.service.Impl;

import com.example.clinicaOdontologica.model.Domicilio;
import com.example.clinicaOdontologica.model.Odontologo;
import com.example.clinicaOdontologica.model.Paciente;
import com.example.clinicaOdontologica.model.Turno;
import com.example.clinicaOdontologica.service.OdontologoService;
import com.example.clinicaOdontologica.service.PacienteService;
import com.example.clinicaOdontologica.service.TurnoService;
import org.junit.FixMethodOrder;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.Instant;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringRunner.class)
@SpringBootTest
class TurnoServiceImplTest {

    @Autowired
    TurnoService turnoService;

    @Autowired
    PacienteService pacienteService;

    @Autowired
    OdontologoService odontologoService;

    Turno turno = new Turno();

    @Test
    public void agendarTurno() {
        Domicilio domicilio = new Domicilio();
        domicilio.setCalle("colombres");
        domicilio.setNumero("1234");
        domicilio.setLocalidad("rosario");
        domicilio.setProvincia("santa fe");

        Paciente paciente = new Paciente();
        paciente.setNombre("Lara");
        paciente.setApellido("Amaya");
        paciente.setDni("435987");
        paciente.setFechaIngreso("2020-05-03");
        paciente.setDomicilio(domicilio);
        pacienteService.registrarPaciente(paciente);

        Odontologo odontologo = new Odontologo();
        odontologo.setNombre("Lara");
        odontologo.setApellido("Amaya");
        odontologo.setMatricula(1234);
        odontologoService.registrarOdontologo(odontologo);

        turno.setFecha(Date.from(Instant.now()));
        turno.setPaciente(paciente);
        turno.setOdontologo(odontologo);

        turnoService.agendarTurno(turno);
        List<Turno> turnosAgendados = turnoService.listarTodos();
        assertTrue(turnosAgendados.size() > 0);

    }

    @Test
    public void eliminarTurno(){
        try{
            odontologoService.eliminar(turno.getId());
            assertTrue(turno.getId() == null);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }


}
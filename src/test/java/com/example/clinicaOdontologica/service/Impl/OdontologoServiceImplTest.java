package com.example.clinicaOdontologica.service.Impl;

import com.example.clinicaOdontologica.model.Odontologo;
import com.example.clinicaOdontologica.service.OdontologoService;
import org.junit.FixMethodOrder;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringRunner.class)
@SpringBootTest
class OdontologoServiceImplTest {

    @Autowired
    OdontologoService odontologoService;

    Odontologo odontologo = new Odontologo();

    @Test
    public void crearOdontologo() {
        try {
            odontologo.setNombre("Lara");
            odontologo.setApellido("Amaya");
            odontologo.setMatricula(1234);

            odontologoService.registrarOdontologo(odontologo);
            Optional<Odontologo> odontologoEncontrado = odontologoService.buscar(1);

            assertEquals(odontologo.getNombre(), odontologoEncontrado.get().getNombre());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void eliminarOdontologo(){
        try{
            odontologoService.eliminar(odontologo.getId());
            assertTrue(odontologo.getId() == null);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}
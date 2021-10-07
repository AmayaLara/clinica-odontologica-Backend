package com.example.clinicaOdontologica.controller;

import com.example.clinicaOdontologica.model.Odontologo;
import com.example.clinicaOdontologica.model.Paciente;
import com.example.clinicaOdontologica.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.apache.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    private static final Logger logger = Logger.getLogger(PacienteController.class);

    private final PacienteService pacienteService;

    @Autowired
    public PacienteController(PacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }

    @GetMapping
    public ResponseEntity<List<Paciente>> buscarTodos(){
        return ResponseEntity.ok(pacienteService.buscarTodos());
    }

    @PostMapping("/crear")
    public ResponseEntity<Paciente> registrarPaciente(@RequestBody Paciente paciente) {
        Paciente pacienteRegistrado = pacienteService.registrarPaciente(paciente);
        if (pacienteRegistrado == null){
            logger.error("no se pudo registrar el paciente en el sistema");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        else
            logger.info("registrando un paciente en el sistema");
        return ResponseEntity.ok(pacienteRegistrado);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Paciente>> buscar(@PathVariable Integer id) {
        Optional<Paciente> paciente = pacienteService.buscar(id);
        if(paciente.isPresent()){
            logger.info("Trayendo un paciente del sistema");
            return ResponseEntity.ok(paciente);
        }
        else
            logger.error("Error en la peticion o el paciente indicado no existe");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }


    @PutMapping()
    public ResponseEntity<Paciente> actualizar(@RequestBody Paciente paciente) {
        ResponseEntity<Paciente> response = null;
        if (paciente.getId() != null && pacienteService.buscar(paciente.getId()).isPresent()) {
            logger.info("Se actualizaron los datos del paciente");
            response = ResponseEntity.ok(pacienteService.actualizar(paciente));
        } else {
            logger.error("Error en la peticion o el paciente indicado no existe");
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return response;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Integer id) {
        ResponseEntity<String> response = null;
        logger.debug("Eliminando un paciente");
        if (pacienteService.buscar(id).isPresent()) {
            pacienteService.eliminar(id);
            response = ResponseEntity.status(HttpStatus.NO_CONTENT).body("Eliminado");
        } else {
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return response;
    }


}

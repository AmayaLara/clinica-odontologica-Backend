package com.example.clinicaOdontologica.controller;

import com.example.clinicaOdontologica.model.Turno;
import com.example.clinicaOdontologica.service.TurnoService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/turnos")
public class TurnoController {

    private static final Logger logger = Logger.getLogger(TurnoController.class);

    private final TurnoService turnoService;

    @Autowired
    public TurnoController(TurnoService turnoService) {
        this.turnoService = turnoService;
    }

    @GetMapping
    public ResponseEntity<List<Turno>> buscarTodos(){
        return ResponseEntity.ok(turnoService.listarTodos());
    }

    @PostMapping("/crear")
    public ResponseEntity<Turno> agendarTurno(@RequestBody Turno turno) {
        Turno response = turnoService.agendarTurno(turno);
        if (response == null){
            logger.error("no se pudo registrar el turno en el sistema");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        else
            logger.info("registrando un turno en el sistema");
        return ResponseEntity.ok(response);

    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Turno>> buscar(@PathVariable Integer id) {
        Optional<Turno> turno = turnoService.buscar(id);
        if(turno.isPresent()){
            return ResponseEntity.ok(turno);
        }
        else
            logger.error("Error en la peticion o el turno indicado no existe");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }


    @PutMapping()
    public ResponseEntity<Turno> actualizar(@RequestBody Turno turno) {
        ResponseEntity<Turno> response = null;
        if (turno.getId() != null && turnoService.buscar(turno.getId()).isPresent()) {
            logger.info("El turno se actualizó correctamente");
            response = ResponseEntity.ok(turnoService.actualizar(turno));
        } else {
            logger.error("Error en la peticion o el turno indicado no existe");
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return response;
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Integer id) {
        ResponseEntity<String> response = null;
        logger.debug("Eliminando un turno");
        if (turnoService.buscar(id).isPresent()) {
            turnoService.eliminar(id);
            response = ResponseEntity.status(HttpStatus.NO_CONTENT).body("Eliminado");
        } else {
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return response;
    }

}

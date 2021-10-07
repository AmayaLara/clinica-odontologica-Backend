package com.example.clinicaOdontologica.controller;

import com.example.clinicaOdontologica.model.Odontologo;
import com.example.clinicaOdontologica.service.OdontologoService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/odontologos")
public class OdontologoController {

    private static final Logger logger = Logger.getLogger(OdontologoController.class);

    private final OdontologoService odontologoService;

    @Autowired
    public OdontologoController(OdontologoService odontologoService) {
        this.odontologoService = odontologoService;
    }

    @GetMapping
    public ResponseEntity<List<Odontologo>> buscarTodos(){
        return ResponseEntity.ok(odontologoService.buscarTodos());
    }

    @PostMapping()
    public ResponseEntity<Odontologo> registrarOdontologo(@RequestBody Odontologo odontologo) {
        Odontologo odontologoRegistrado = odontologoService.registrarOdontologo(odontologo);
        if (odontologoRegistrado == null){
            logger.error("no se pudo registrar el odontologo en el sistema");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        else
            logger.info("registrando un odontologo en el sistema");
            return ResponseEntity.ok(odontologoRegistrado);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Optional<Odontologo>> buscar(@PathVariable Integer id) {
        Optional<Odontologo> odontologo = odontologoService.buscar(id);
        if(odontologo.isPresent()){
            logger.info("Trayendo un odontologo del sistema");
            return ResponseEntity.ok(odontologo);
        }
        else
            logger.error("No se pudo traer el odontólogo solicitado");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @PutMapping()
    public ResponseEntity<Odontologo> actualizar(@RequestBody Odontologo odontologo) {
        ResponseEntity<Odontologo> response = null;
        if (odontologo.getId() != null && odontologoService.buscar(odontologo.getId()).isPresent()) {
            logger.info("Se actualizaron los datos del odontologo");
            response = ResponseEntity.ok(odontologoService.actualizar(odontologo));
        } else {
            logger.error("Error en la peticion o el odontólogo indicado no existe");
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return response;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Integer id) {
        ResponseEntity<String> response = null;
        logger.debug("Eliminando un odontologo");
        if (odontologoService.buscar(id).isPresent()) {
            odontologoService.eliminar(id);
            response = ResponseEntity.status(HttpStatus.NO_CONTENT).body("Eliminado");
        } else {
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return response;
    }


}

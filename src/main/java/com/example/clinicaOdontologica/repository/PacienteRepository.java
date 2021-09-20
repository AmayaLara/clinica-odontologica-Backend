package com.example.clinicaOdontologica.repository;

import com.example.clinicaOdontologica.model.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PacienteRepository extends JpaRepository<Paciente, Integer> {
}

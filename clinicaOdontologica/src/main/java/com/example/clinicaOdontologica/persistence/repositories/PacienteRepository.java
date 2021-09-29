package com.example.clinicaOdontologica.persistence.repositories;

import com.example.clinicaOdontologica.persistence.entities.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PacienteRepository extends JpaRepository<Paciente, Integer> {
    @Query("SELECT paciente FROM Paciente paciente WHERE paciente.dni =?1")
    public Paciente buscarPorDni(Integer dni);

}

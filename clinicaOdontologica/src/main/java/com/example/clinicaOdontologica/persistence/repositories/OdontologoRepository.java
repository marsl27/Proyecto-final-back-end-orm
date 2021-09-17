package com.example.clinicaOdontologica.persistence.repositories;

import com.example.clinicaOdontologica.persistence.entities.Odontologo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OdontologoRepository extends JpaRepository<Odontologo, Integer> {
}

package com.example.clinicaOdontologica.persistence.repositories;

import com.example.clinicaOdontologica.persistence.entities.Domicilio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface DomicilioRepository extends JpaRepository<Domicilio, Integer> {

    @Query("SELECT domicilio FROM Domicilio domicilio WHERE domicilio.calle = ?1 AND domicilio.numero = ?2 AND domicilio.localidad = ?3 AND domicilio.provincia =?4")
    Domicilio buscarDomicilioPorCalleNumeroLocalidadProvincia(String calle, String numero, String localidad, String provincia);
}

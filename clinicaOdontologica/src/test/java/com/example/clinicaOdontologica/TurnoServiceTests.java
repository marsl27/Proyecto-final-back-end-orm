package com.example.clinicaOdontologica;

import com.example.clinicaOdontologica.exceptions.ServiceBadRequestException;
import com.example.clinicaOdontologica.exceptions.ServiceNotFoundException;
import com.example.clinicaOdontologica.model.DomicilioDTO;
import com.example.clinicaOdontologica.model.OdontologoDTO;
import com.example.clinicaOdontologica.model.PacienteDTO;
import com.example.clinicaOdontologica.model.TurnoDTO;
import com.example.clinicaOdontologica.persistence.entities.Turno;
import com.example.clinicaOdontologica.service.OdontologoService;
import com.example.clinicaOdontologica.service.PacienteService;
import com.example.clinicaOdontologica.service.TurnoService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringRunner.class)
@SpringBootTest
public class TurnoServiceTests {

    @Autowired
    private PacienteService pacienteService;
    @Autowired
    private OdontologoService odontologoService;
    @Autowired
    private TurnoService turnoService;

    @Before
    public void cargarDatos() throws ServiceNotFoundException, ServiceBadRequestException {
        DomicilioDTO domicilio = new DomicilioDTO("Av Santa fe", "444", "CABA", "Buenos Aires");
        PacienteDTO p = pacienteService.guardar(new PacienteDTO("Santiago", "Paz", 88888888, domicilio));
        this.odontologoService.guardar(new OdontologoDTO("Santiago", "Paz", 3455647));
        this.turnoService.guardar(new TurnoDTO(1,1,LocalDateTime.now()));
    }

    @Test
    public void altaTurnoTest() throws ServiceNotFoundException, ServiceBadRequestException{
        turnoService.guardar(new TurnoDTO(1,1,LocalDateTime.now()));
        Assert.assertNotNull(turnoService.buscar(1));
    }
    @Test
    public void buscarTurnoTest() throws ServiceNotFoundException, ServiceBadRequestException{
        TurnoDTO turno = turnoService.buscar(1);
        Assert.assertTrue(turno.getId() != null);
    }
    @Test
    public void eliminarTurnoTest() throws ServiceNotFoundException, ServiceBadRequestException{
        turnoService.eliminar(1);
        Throwable exception = assertThrows(ServiceNotFoundException.class, () -> turnoService.buscar(1));
        assertEquals("No se encontró el turno con id 1", exception.getMessage());
    }

    @Test
    public void buscarTodosTest() throws ServiceNotFoundException, ServiceBadRequestException{
        List<TurnoDTO> turnos = turnoService.buscarTodos();
        Assert.assertTrue(turnos.size() > 0);
        System.out.println(turnos);
    }
}

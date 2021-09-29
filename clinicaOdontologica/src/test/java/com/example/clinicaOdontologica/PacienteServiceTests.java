package com.example.clinicaOdontologica;

import com.example.clinicaOdontologica.exceptions.ServiceBadRequestException;
import com.example.clinicaOdontologica.exceptions.ServiceNotFoundException;
import com.example.clinicaOdontologica.model.DomicilioDTO;
import com.example.clinicaOdontologica.model.PacienteDTO;
import com.example.clinicaOdontologica.service.PacienteService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

//@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringRunner.class)
@SpringBootTest

public class PacienteServiceTests {
    @Autowired
    private PacienteService pacienteService;

    @Before
    public void cargarDatos() throws ServiceNotFoundException, ServiceBadRequestException {
        this.pacienteService.guardar(new PacienteDTO("Santiago", "Paz", 3455647, "santiago@digital.com", new DomicilioDTO("Rivadavia", "5467", "Caba", "Caba")));
    }

    @Test
    public void guardarPacienteTest() throws ServiceNotFoundException, ServiceBadRequestException {
        PacienteDTO paciente = pacienteService.guardar(new PacienteDTO("Juan", "Ramirez", 348971960, "juan@digital.com", new DomicilioDTO("Rivadavia", "5467", "Caba", "Caba")));
        Assert.assertTrue(paciente.getId() != null);
    }

    @Test
    public void buscarPacienteTest() throws ServiceNotFoundException, ServiceBadRequestException{
        PacienteDTO paciente = pacienteService.buscar(1);
        Assert.assertTrue(paciente.getId() != null);
    }
    @Test
    public void eliminarPacienteTest() throws ServiceNotFoundException, ServiceBadRequestException {
        pacienteService.eliminar(1);
        Throwable exception = assertThrows(ServiceNotFoundException.class, () -> pacienteService.buscar(1));
        assertEquals("No se encontró el paciente con id 1", exception.getMessage());
    }

    @Test
    public void buscarTodosTest() throws ServiceNotFoundException, ServiceBadRequestException{
        List<PacienteDTO> pacientes = pacienteService.buscarTodos();
        Assert.assertTrue(pacientes.size() > 0);
        System.out.println(pacientes);
    }
}

package com.example.clinicaOdontologica;

import com.example.clinicaOdontologica.exceptions.ServiceBadRequestException;
import com.example.clinicaOdontologica.exceptions.ServiceNotFoundException;
import com.example.clinicaOdontologica.model.OdontologoDTO;
import com.example.clinicaOdontologica.service.OdontologoService;
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

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringRunner.class)
@SpringBootTest
public class OdontologoServiceTests {
    @Autowired
    private OdontologoService odontologoService;

    @Before
    public void cargarDatos() throws ServiceNotFoundException, ServiceBadRequestException {
        this.odontologoService.guardar(new OdontologoDTO("Santiago", "Paz", 3455647));
    }


    @Test
    public void guardarOdontologoTest() throws ServiceNotFoundException, ServiceBadRequestException {
        OdontologoDTO odontologo = odontologoService.guardar(new OdontologoDTO("Juan", "Ramirez", 348971960));
        Assert.assertTrue(odontologo.getId() != null);
    }

    @Test
    public void buscarOdontologoTest() throws ServiceNotFoundException, ServiceBadRequestException{
        OdontologoDTO odontologo = odontologoService.buscar(1);
        Assert.assertTrue(odontologo.getId() != null);
    }
    @Test
    public void eliminarOdontologoTest() throws ServiceNotFoundException, ServiceBadRequestException {
        odontologoService.eliminar(1);
        Throwable exception = assertThrows(ServiceNotFoundException.class, () -> odontologoService.buscar(1));
        assertEquals("No se encontró el odontólogo con id 1", exception.getMessage());
    }

    @Test
    public void buscarTodosTest() throws ServiceNotFoundException, ServiceBadRequestException{
        List<OdontologoDTO> odontologos = odontologoService.buscarTodos();
        Assert.assertTrue(odontologos.size() > 0);
        System.out.println(odontologos);
    }


}

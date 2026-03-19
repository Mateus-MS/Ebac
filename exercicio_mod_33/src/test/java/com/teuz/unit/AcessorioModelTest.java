package com.teuz.unit;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

import com.teuz.domain.acessorio.model.Acessorio;
import com.teuz.domain.carro.model.Carro;
import com.teuz.domain.carro.model.Modelo;
import com.teuz.domain.marca.model.Marca;

public class AcessorioModelTest {

    @Test
    public void shouldCreateAcessorio() {
        Acessorio acessorio = new Acessorio("Airbag");
        assertNotNull(acessorio);
        assertEquals("Airbag", acessorio.getName());
    }

    @Test
    public void shouldSetAndGetName() {
        Acessorio acessorio = new Acessorio();
        acessorio.setName("Sunroof");
        assertEquals("Sunroof", acessorio.getName());
    }

    @Test
    public void shouldSetAndGetCarros() {
        // Marca and Carro
        Marca marca = new Marca("Honda");
        Carro c1 = new Carro(Modelo.SUV, 2021, marca);
        Carro c2 = new Carro(Modelo.SEDAN, 2022, marca);

        List<Carro> carros = new ArrayList<>();
        carros.add(c1);
        carros.add(c2);

        // Acessorio
        Acessorio acessorio = new Acessorio("Airbag");
        acessorio.setCarros(carros);

        assertNotNull(acessorio.getCarros());
        assertEquals(2, acessorio.getCarros().size());
        assertTrue(acessorio.getCarros().contains(c1));
        assertTrue(acessorio.getCarros().contains(c2));
    }

    @Test
    public void shouldAddRemoveCarro() {
        // Optional helper methods (if you implement addCarro/removeCarro in Acessorio)
        Acessorio acessorio = new Acessorio("GPS");
        Marca marca = new Marca("Toyota");
        Carro carro = new Carro(Modelo.HATCHBACK, 2020, marca);

        // simulate add
        List<Carro> carros = new ArrayList<>();
        carros.add(carro);
        acessorio.setCarros(carros);

        assertEquals(1, acessorio.getCarros().size());
        assertTrue(acessorio.getCarros().contains(carro));

        // simulate remove
        carros.remove(carro);
        acessorio.setCarros(carros);

        assertEquals(0, acessorio.getCarros().size());
    }
}
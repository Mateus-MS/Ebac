package com.teuz.unit;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import org.junit.Test;

import com.teuz.domain.carro.model.Carro;
import com.teuz.domain.carro.model.Modelo;
import com.teuz.domain.marca.model.Marca;

public class MarcaModelTest 
{
    @Test
    public void shouldNotBeNull() {
        Marca marca = new Marca();
        assertNotNull("Marca object should not be null", marca);
    }

    @Test
    public void shouldSetNameAndGetName() {
        Marca marca = new Marca();
        marca.setName("Citroen");

        assertEquals("Citroen", marca.getName());
    }

    @Test
    public void shouldUseConstructor() {
        Marca marca = new Marca("Toyota");
        assertEquals("Toyota", marca.getName());
        assertNull("List of carros should be null initially", marca.getCarros());
    }

    @Test
    public void shouldSetAndGetCarros() {
        Marca marca = new Marca("Honda");
        List<Carro> carros = new ArrayList<>();
        carros.add(new Carro(Modelo.SUV, 2020, marca));
        carros.add(new Carro(Modelo.SEDAN, 2022, marca));

        marca.setCarros(carros);

        assertNotNull(marca.getCarros());
        assertEquals(2, marca.getCarros().size());
        assertEquals(Modelo.SUV, marca.getCarros().get(0).getModelo());
        assertEquals(Modelo.SEDAN, marca.getCarros().get(1).getModelo());
    }
}

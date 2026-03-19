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

public class CarroModelTest {

    @Test
    public void shouldCreateCarro() {
        Marca marca = new Marca("Citroen");
        Carro carro = new Carro(Modelo.HATCHBACK, 2020, marca);

        assertNotNull(carro);
        assertEquals(Modelo.HATCHBACK, carro.getModelo());
        assertEquals(Integer.valueOf(2020), carro.getAno());
        assertEquals("Citroen", carro.getMarca().getName());
    }

    @Test
    public void shouldSetAndGetFields() {
        Marca marca = new Marca();
        marca.setName("Toyota");

        Carro carro = new Carro();
        carro.setModelo(Modelo.SEDAN);
        carro.setAno(2022);
        carro.setMarca(marca);

        assertEquals(Modelo.SEDAN, carro.getModelo());
        assertEquals(Integer.valueOf(2022), carro.getAno());
        assertEquals("Toyota", carro.getMarca().getName());
    }

    @Test
    public void shouldHandleAcessorios() {
        Marca marca = new Marca("Honda");
        Carro carro = new Carro(Modelo.SUV, 2021, marca);

        Acessorio a1 = new Acessorio("Airbag");
        Acessorio a2 = new Acessorio("Sunroof");

        List<Acessorio> acessorios = new ArrayList<>();
        acessorios.add(a1);
        acessorios.add(a2);

        carro.setAcessorios(acessorios);

        assertNotNull(carro.getAcessorios());
        assertEquals(2, carro.getAcessorios().size());
        assertTrue(carro.getAcessorios().contains(a1));
        assertTrue(carro.getAcessorios().contains(a2));
    }
}
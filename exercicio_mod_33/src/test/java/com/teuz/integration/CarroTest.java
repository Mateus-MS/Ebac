package com.teuz.integration;

import java.util.List;
import java.util.Optional;

import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.teuz.domain.acessorio.model.Acessorio;
import com.teuz.domain.acessorio.repo.AcessorioDAO;
import com.teuz.domain.carro.model.Carro;
import com.teuz.domain.carro.model.Modelo;
import com.teuz.domain.carro.repo.CarroDAO;
import com.teuz.domain.marca.model.Marca;
import com.teuz.domain.marca.repo.MarcaDAO;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class CarroTest {

    static EntityManagerFactory emf;
    CarroDAO carroDAO;
    MarcaDAO marcaDAO;
    AcessorioDAO acessorioDAO;

    @BeforeClass
    public static void setupFactory(){
        emf = Persistence.createEntityManagerFactory("ExemploJPA-Test");
    }

    @Before
    public void setup(){
        carroDAO = new CarroDAO(emf);
        marcaDAO = new MarcaDAO(emf);
        acessorioDAO = new AcessorioDAO(emf);
    }

    @Test
    public void shouldPersistAndFindCarro() {
        Marca marca = new Marca("citroen");
        marcaDAO.Register(marca);

        Carro carro = new Carro(Modelo.HATCHBACK, 2017, marca);
        carroDAO.Register(carro);

        Optional<Carro> found = carroDAO.FindById(carro.getId());

        assertTrue(found.isPresent());
        assertEquals(Modelo.HATCHBACK, found.get().getModelo());
        assertEquals(2017, found.get().getAno().intValue());
    }

    @Test
    public void shouldPersistCarroWithAcessorios() {
        Marca marca = new Marca("citroen");
        marcaDAO.Register(marca);

        Acessorio ac = new Acessorio("Ar condicionado");
        acessorioDAO.Register(ac);

        Carro carro = new Carro(Modelo.HATCHBACK, 2017, marca);
        carro.setAcessorios(List.of(ac));
        carroDAO.Register(carro);

        Optional<Carro> found = carroDAO.FindByIdWithAcessorios(carro.getId());
        assertTrue(found.isPresent());
        assertEquals(1, found.get().getAcessorios().size());
        assertEquals("Ar condicionado", found.get().getAcessorios().getFirst().getName());
    }

    @AfterClass
    public static void closeFactory() {
        emf.close();
    }
}
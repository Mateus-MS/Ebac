package com.teuz.unit;

import java.util.Optional;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.junit.MockitoJUnitRunner;

import com.teuz.domain.carro.model.Carro;
import com.teuz.domain.carro.model.Modelo;
import com.teuz.domain.carro.repo.CarroDAO;
import com.teuz.domain.marca.model.Marca;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;

@RunWith(MockitoJUnitRunner.class)
public class CarroTest {

    @Mock EntityManagerFactory emf;
    @Mock EntityManager manager;
    @Mock EntityTransaction transaction;

    CarroDAO carroDAO;

    @Before
    public void setup() {
        when(emf.createEntityManager()).thenReturn(manager);
        when(manager.getTransaction()).thenReturn(transaction);
        carroDAO = new CarroDAO(emf);
    }

    @Test
    public void Register_ShouldPersistCarro() {
        Marca marca = new Marca("citroen");
        Carro carro = new Carro(Modelo.HATCHBACK, 2017, marca);

        carroDAO.Register(carro);

        verify(transaction).begin();
        verify(manager).persist(carro);
        verify(transaction).commit();
    }

    @Test
    public void FindById_ShouldReturnCarro_WhenExists() {
        Marca marca = new Marca("citroen");
        Carro carro = new Carro(Modelo.HATCHBACK, 2017, marca);
        UUID id = UUID.randomUUID();

        when(manager.find(Carro.class, id)).thenReturn(carro);

        Optional<Carro> result = carroDAO.FindById(id);

        assertTrue(result.isPresent());
        assertEquals(Modelo.HATCHBACK, result.get().getModelo());
    }

    @Test
    public void FindById_ShouldReturnEmpty_WhenNotExists() {
        UUID id = UUID.randomUUID();
        when(manager.find(Carro.class, id)).thenReturn(null);

        Optional<Carro> result = carroDAO.FindById(id);

        assertFalse(result.isPresent());
    }
}
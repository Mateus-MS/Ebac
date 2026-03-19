package com.teuz.domain.carro.repo;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.teuz.domain.carro.model.Carro;
import com.teuz.shared.generic.repo.GenericDAO;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

public class CarroDAO extends GenericDAO<Carro> implements ICarroDAO {
    private final EntityManagerFactory emf;

    public CarroDAO(EntityManagerFactory emf){
        super(emf);
        this.emf = emf;
    }

    @Override
    protected Class<Carro> getEntityClass() {
        return Carro.class;
    }

    public Optional<Carro> FindByIdWithAcessorios(UUID id) {
        try (EntityManager manager = getEmf().createEntityManager()) {
            List<Carro> result = manager.createQuery(
                "SELECT c FROM Carro c LEFT JOIN FETCH c.acessorios WHERE c.id = :id",
                Carro.class
            )
            .setParameter("id", id)
            .getResultList();
            
            return result.isEmpty() ? Optional.empty() : Optional.of(result.get(0));
        }
    }
}

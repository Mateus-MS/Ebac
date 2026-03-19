package com.teuz.domain.acessorio.repo;

import com.teuz.domain.acessorio.model.Acessorio;
import com.teuz.shared.generic.repo.GenericDAO;

import jakarta.persistence.EntityManagerFactory;

public class AcessorioDAO extends GenericDAO<Acessorio> implements IAcessorioDAO {
    private final EntityManagerFactory emf;

    public AcessorioDAO(EntityManagerFactory emf){
        super(emf);
        this.emf = emf;
    }

    @Override
    protected Class<Acessorio> getEntityClass() {
        return Acessorio.class;
    }
}

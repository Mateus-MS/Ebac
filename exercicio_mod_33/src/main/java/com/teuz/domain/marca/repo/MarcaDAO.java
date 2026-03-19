package com.teuz.domain.marca.repo;

import com.teuz.domain.marca.model.Marca;
import com.teuz.shared.generic.repo.GenericDAO;

import jakarta.persistence.EntityManagerFactory;

public class MarcaDAO extends GenericDAO<Marca> implements IMarcaDAO {
    private final EntityManagerFactory emf;

    public MarcaDAO(EntityManagerFactory emf){
        super(emf);
        this.emf = emf;
    }

    @Override
    protected Class<Marca> getEntityClass() {
        return Marca.class;
    }
}

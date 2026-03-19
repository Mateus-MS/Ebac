package com.teuz.shared.generic.repo;

import java.util.Optional;
import java.util.UUID;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

public abstract class GenericDAO<T> implements IGenericDAO<T> {

    private final EntityManagerFactory emf;
    protected EntityManagerFactory getEmf(){return this.emf;}

    public GenericDAO(EntityManagerFactory emf) {
        this.emf = emf;
    }

    protected abstract Class<T> getEntityClass();

    @Override
    public T Register(T entity){
        try(EntityManager manager = emf.createEntityManager()){
            manager.getTransaction().begin();
            manager.persist(entity);
            manager.getTransaction().commit();
            return entity;
        }
    }

    @Override
    public T Update(T entity){
        try(EntityManager manager = emf.createEntityManager()){
            manager.getTransaction().begin();
            T merged = manager.merge(entity);
            manager.getTransaction().commit();
            return merged;
        }
    }

    @Override
    public void Delete(T entity){
        try (EntityManager manager = emf.createEntityManager()){
            manager.getTransaction().begin();
            T managed = manager.merge(entity);
            manager.remove(managed);
            manager.getTransaction().commit();
        }
    }

    @Override
    public Optional<T> FindById(UUID id) {
        try (EntityManager manager = getEmf().createEntityManager()) {
            return Optional.ofNullable(manager.find(this.getEntityClass(), id));
        }
    }

}

package com.teuz.shared.generic.service;

import java.util.Optional;
import java.util.UUID;

import com.teuz.shared.generic.repo.IGenericDAO;

public class GenericService<T> implements IGenericService<T> {
    private final IGenericDAO<T> dao;

    public GenericService(IGenericDAO<T> dao){
        this.dao = dao;
    }

    @Override
    public T Register(T entity){
        return this.dao.Register(entity);
    }

    @Override
    public T Update(T entity){
        return this.dao.Update(entity);
    }

    @Override
    public void Delete(T entity){
        this.dao.Delete(entity);
    }

    @Override
    public Optional<T> FindById(UUID id){
        return this.dao.FindById(id);
    }
}

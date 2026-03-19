package com.teuz.shared.generic.repo;

import java.util.Optional;
import java.util.UUID;

public interface IGenericDAO<T> {
    T Register(T entity);
    T Update(T entity);
    void Delete(T entity);

    Optional<T> FindById(UUID id);
}

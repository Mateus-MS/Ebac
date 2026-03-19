package com.teuz.shared.generic.service;

import java.util.Optional;
import java.util.UUID;

public interface IGenericService<T> {
    T Register(T entity);
    T Update(T entity);
    void Delete(T entity);

    Optional<T> FindById(UUID id);
}

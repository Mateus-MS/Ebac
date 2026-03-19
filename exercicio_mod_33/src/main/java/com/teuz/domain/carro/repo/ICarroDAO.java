package com.teuz.domain.carro.repo;

import java.util.Optional;
import java.util.UUID;

import com.teuz.domain.carro.model.Carro;
import com.teuz.shared.generic.repo.IGenericDAO;

public interface ICarroDAO extends IGenericDAO<Carro>{
    Optional<Carro> FindByIdWithAcessorios(UUID id);
}

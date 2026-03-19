package com.teuz.domain.carro.service;

import com.teuz.domain.carro.model.Carro;
import com.teuz.domain.carro.repo.ICarroDAO;
import com.teuz.shared.generic.service.GenericService;

public class CarroService extends GenericService<Carro> implements ICarroService{
    private final ICarroDAO dao;

    public CarroService(ICarroDAO dao){
        super(dao);
        this.dao = dao;
    }
}

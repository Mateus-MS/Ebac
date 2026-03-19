package com.teuz.domain.acessorio.service;

import com.teuz.domain.acessorio.model.Acessorio;
import com.teuz.domain.acessorio.repo.IAcessorioDAO;
import com.teuz.shared.generic.service.GenericService;

public class AcessorioService extends GenericService<Acessorio> implements IAcessorioService{
    private final IAcessorioDAO dao;

    public AcessorioService(IAcessorioDAO dao){
        super(dao);
        this.dao = dao;
    }
}

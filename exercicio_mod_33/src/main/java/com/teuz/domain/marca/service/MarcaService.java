package com.teuz.domain.marca.service;

import com.teuz.domain.marca.model.Marca;
import com.teuz.domain.marca.repo.IMarcaDAO;
import com.teuz.shared.generic.service.GenericService;

public class MarcaService extends GenericService<Marca> implements IMarcaService {
    private final IMarcaDAO dao;

    public MarcaService(IMarcaDAO dao) {
        super(dao);
        this.dao = dao;
    }

}

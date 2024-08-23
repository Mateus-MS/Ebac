package main.java.generics.service;

import main.java.client.Client;
import main.java.generics.DAO.IGenericDAO;

public class GenericService<T, DAO extends IGenericDAO<T>> implements IGenericService<T>{

    /**
     * Recebe uma instancia do dao externamente
     *
     * configurando uma dependenci injection <br>
     * "Quem usa não instancia" - Mateus 2024.
     */
    private final DAO genericDAO;
    public GenericService(DAO genericDAO){
        this.genericDAO = genericDAO;
    }

    @Override
    public boolean register(T entity){
        return this.genericDAO.register(entity);
    }

    @Override
    public T pesquisa(Double code){
        return this.genericDAO.pesquisa(code);
    }

    @Override
    public boolean atualiza(T entity){
        return this.genericDAO.atualiza(entity);
    }

    @Override
    public boolean excluir(Double code){
        return this.genericDAO.excluir(code);
    }

}

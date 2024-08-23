package main.java.funcionario.service;

import main.java.funcionario.Funcionario;
import main.java.generics.DAO.IGenericDAO;
import main.java.product.Product;

public class FuncionarioService implements IFuncionarioService{

    private IGenericDAO<Funcionario> productDAO;
    public FuncionarioService(IGenericDAO<Funcionario> productDAO){
        this.productDAO = productDAO;
    }

    @Override
    public boolean register(Funcionario entity) {
        return this.productDAO.register(entity);
    }

    @Override
    public Funcionario pesquisa(Double code) {
        return this.productDAO.pesquisa(code);
    }

    @Override
    public boolean atualiza(Funcionario entity) {
        return this.productDAO.atualiza(entity);
    }

    @Override
    public boolean excluir(Double code) {
        return this.productDAO.excluir(code);
    }
}

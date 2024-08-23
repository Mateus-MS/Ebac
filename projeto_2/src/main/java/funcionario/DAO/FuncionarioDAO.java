package main.java.funcionario.DAO;

import main.java.funcionario.Funcionario;
import main.java.generics.DAO.IGenericDAO;

public class FuncionarioDAO implements IGenericDAO<Funcionario> {
    @Override
    public boolean register(Funcionario entity) {
        return false;
    }

    @Override
    public Funcionario pesquisa(Double code) {
        return null;
    }

    @Override
    public boolean atualiza(Funcionario entity) {
        return false;
    }

    @Override
    public boolean excluir(Double code) {
        return false;
    }
}

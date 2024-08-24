package main.test.service;

import main.java.funcionario.DAO.FuncionarioDAOMock;
import main.java.funcionario.Funcionario;
import main.java.funcionario.service.FuncionarioService;
import main.java.generics.DAO.IGenericDAO;
import main.java.generics.service.IGenericService;
import main.test.generic.GenericServiceTest;

public class FuncionarioServiceTest extends GenericServiceTest<Funcionario> {
    @Override
    protected IGenericDAO<Funcionario> createDAO() {
        return FuncionarioDAOMock.getInstance();
    }

    @Override
    protected IGenericService<Funcionario> createService(IGenericDAO<Funcionario> dao) {
        return new FuncionarioService(dao);
    }

    @Override
    protected Funcionario createTestEntity(Double code) {
        return new Funcionario(
                "Test",
                code,
                20,
                "Tester",
                1400
        );
    }

}

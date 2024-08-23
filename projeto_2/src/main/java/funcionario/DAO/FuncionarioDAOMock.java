package main.java.funcionario.DAO;

import main.java.exceptions.DataAlreadyInDBException;
import main.java.exceptions.DataNotPresentInDBException;
import main.java.funcionario.Funcionario;
import main.java.generics.DAO.IGenericDAO;
import main.java.product.DAO.ProductDAOMock;
import main.java.product.Product;

import java.util.HashMap;
import java.util.Map;

/**
 * Em uma aplicação real o <code>FuncionarioDAO</code> seria a parte real da aplicação <br>
 * <br>
 * Mas nesse exemplo o <code>FuncionarioDAOMock</code> será usado como parte real aqui. <br>
 * Já que não temos acesso ao banco de dados.
 */
public class FuncionarioDAOMock implements IGenericDAO<Funcionario> {

    private  static FuncionarioDAOMock instance;
    private FuncionarioDAOMock(){}

    public static FuncionarioDAOMock getInstance(){
        if(instance == null){
            instance = new FuncionarioDAOMock();
        }
        return instance;
    }

    //Talvez seja bem mais inteligente
    //Ao invés de usar um Map usar um LinkedHashSet
    //Já que uma das caracteristicas do register e não ter funcionarios duplicados
    //Mas para fins didaticos e de testes vou usar um Map mesmo :D
    private final Map<Double, Funcionario> funcionarios = new HashMap<>();

    /**
     * Salva um funcionario no banco de dados :D <br><br>
     * Levanta um <code>DataAlreadyInDB</code> caso tente registrar funcionario com o mesmo <code>codigo</code> <br>
     */
    @Override
    public boolean register(Funcionario funcionario) {
        //Tenta procurar um funcionario com o mesmo codigo no sistema
        Funcionario test = this.pesquisa(funcionario.getRegister());

        //Se encontrar algum, levanta o erro
        if(test != null){
            throw new DataAlreadyInDBException("CPF já registrado.");
        }

        //Salva no sistema
        this.funcionarios.put(funcionario.getRegister(), funcionario);
        return true;
    }

    @Override
    public Funcionario pesquisa(Double code) {
        return this.funcionarios.get(code);
    }

    /**
     * Atualiza as informações do funcionario recebido no banco de dados :D <br><br>
     * Levanta um <code>DataNotPresentInDBException</code> caso tente atualizar um funcionario que ainda não exista em sistema.<br>
     */
    @Override
    public boolean atualiza(Funcionario funcionario) {
        //Testa se o funcionario recebido já existe em sistema
        Funcionario test = this.pesquisa(funcionario.getRegister());

        //Se esse funcionario ainda não existir em sistema
        //Não é possível atualizar

        //Levanta a exception DataNotPresentInDB
        if(test == null){
            throw new DataNotPresentInDBException("Funcionario ainda não registrado no sistema");
        }

        //Atualiza as informações no DB
        this.funcionarios.replace(funcionario.getRegister(), funcionario);
        return true;
    }

    @Override
    public boolean excluir(Double code) {
        //Testa se o funcionario existe no banco de dados
        Funcionario test = this.pesquisa(code);

        //Se não existir
        if(test == null){
            throw new DataNotPresentInDBException("Impossível excluir funcionario não registrado no sistema");
        }

        //Se existir
        this.funcionarios.remove(code);
        return true;
    }
}

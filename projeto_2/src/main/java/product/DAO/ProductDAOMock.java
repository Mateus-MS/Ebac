package main.java.product.DAO;

import main.java.client.Client;
import main.java.client.DAO.ClientDAOMock;
import main.java.exceptions.DataAlreadyInDBException;
import main.java.exceptions.DataNotPresentInDBException;
import main.java.generics.DAO.IGenericDAO;
import main.java.product.Product;

import java.util.HashMap;
import java.util.Map;

/**
 * Em uma aplicação real o <code>ProductDAO</code> seria a parte real da aplicação <br>
 * <br>
 * Mas nesse exemplo o <code>ProductDAOMock</code> será usado como parte real aqui. <br>
 * Já que não temos acesso ao banco de dados.
 */
public class ProductDAOMock implements IGenericDAO<Product> {

    private  static ProductDAOMock instance;
    private ProductDAOMock(){}

    public static ProductDAOMock getInstance(){
        if(instance == null){
            instance = new ProductDAOMock();
        }
        return instance;
    }

    //Talvez seja bem mais inteligente
    //Ao invés de usar um Map usar um LinkedHashSet
    //Já que uma das caracteristicas do register e não ter produtos duplicados
    //Mas para fins didaticos e de testes vou usar um Map mesmo :D
    private final Map<Double, Product> products = new HashMap<>();

    /**
     * Salva um produto no banco de dados :D <br><br>
     * Levanta um <code>DataAlreadyInDB</code> caso tente registrar produto com o mesmo <code>codigo</code> <br>
     */
    @Override
    public boolean register(Product product){
        //Tenta procurar um produto com o mesmo codigo no sistema
        Product test = this.pesquisa(product.getCodigo());

        //Se encontrar algum, levanta o erro
        if(test != null){
            throw new DataAlreadyInDBException("CPF já registrado.");
        }

        //Salva no sistema
        this.products.put(product.getCodigo(), product);
        return true;
    }

    @Override
    public Product pesquisa(Double code){
        return this.products.get(code);
    }

    /**
     * Atualiza as informações do product recebido no banco de dados :D <br><br>
     * Levanta um <code>DataNotPresentInDBException</code> caso tente atualizar um product que ainda não exista em sistema.<br>
     */
    @Override
    public boolean atualiza(Product product){
        //Testa se o produto recebido já existe em sistema
        Product test = this.pesquisa(product.getCodigo());

        //Se esse produto ainda não existir em sistema
        //Não é possível atualizar

        //Levanta a exception DataNotPresentInDB
        if(test == null){
            throw new DataNotPresentInDBException("Produto ainda não registrado no sistema");
        }

        //Atualiza as informações no DB
        this.products.replace(product.getCodigo(), product);
        return true;
    }

    @Override
    public boolean excluir(Double code){
        //Testa se o produto existe no banco de dados
        Product test = this.pesquisa(code);

        //Se não existir
        if(test == null){
            throw new DataNotPresentInDBException("Impossível excluir produto não registrado no sistema");
        }

        //Se existir
        this.products.remove(code);
        return true;
    }

}

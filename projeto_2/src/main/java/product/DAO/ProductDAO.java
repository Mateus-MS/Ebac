package main.java.product.DAO;

import main.java.generics.DAO.IGenericDAO;
import main.java.product.Product;

/**
 * Singleton <br>
 *
 * Responsavel por conectar com o banco de dados
 * e realiza toda a logica para os metodos. <br>
 *
 * Nesse caso é apenas uma simulação então todas
 * as funções retornarão <code>false</code>, imitando uma falha
 * na conexão com o banco de dados
 */
public class ProductDAO implements IGenericDAO<Product> {

    private  static ProductDAO instance;
    private ProductDAO(){}

    public static ProductDAO getInstance(){
        if(instance == null){
            instance = new ProductDAO();
        }
        return instance;
    }

    @Override
    public boolean register(Product product){
        return true;
    }

    @Override
    public Product pesquisa(Double code){
        return null;
    }

    @Override
    public boolean atualiza(Product product){
        return false;
    }

    @Override
    public boolean excluir(Double code){
        return false;
    }

}

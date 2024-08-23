package main.java.product.service;

import main.java.generics.DAO.IGenericDAO;
import main.java.product.Product;

/**
 * <code>ProductService</code> atua como uma interface
 * jutando o acesso a todas as informações
 * e funções referente ao <code>Product</code> em um único lugar.
 */
public class ProductService implements IProductService{

    /**
     * Recebe uma instancia do dao externamente
     *
     * configurando uma dependenci injection <br>
     * "Quem usa não instancia" - Mateus 2024.
     */
    private IGenericDAO<Product> productDAO;
    public ProductService(IGenericDAO<Product> productDAO){
        this.productDAO = productDAO;
    }

    @Override
    public boolean register(Product product){
        return this.productDAO.register(product);
    }

    @Override
    public Product pesquisa(Double code){
        return this.productDAO.pesquisa(code);
    }

    @Override
    public boolean atualiza(Product product){
        return this.productDAO.atualiza(product);
    }

    @Override
    public boolean excluir(Double code){
        return this.productDAO.excluir(code);
    }

}

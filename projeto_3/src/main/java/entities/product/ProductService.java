package main.java.entities.product;

public class ProductService {

    private final ProductDAO dao;
    public ProductService(ProductDAO dao){
        this.dao = dao;
    }

    public boolean register(Product product){
        return this.dao.register(product);
    }

    public boolean delete(String name){
        return this.dao.delete(name);
    }

    public Product search(String name){
        return this.dao.search(name);
    }

}

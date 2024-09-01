package main.java.services;

import main.java.entities.product.Product;
import main.java.entities.product.ProductDAO;
import main.java.entities.z_generics.GenericService;
import main.java.entities.z_generics.IGenericDAO;
import org.junit.Assert;
import org.junit.Test;

public class ProductServiceTest {

    @Test
    public void test(){
        Product product = new Product(
            "PS4 - Slim",
            1500,
            true
        );

        IGenericDAO<Product> dao = ProductDAO.getInstance();
        GenericService<Product> service = new GenericService<>(dao);

        //Testa o metodo register
        Assert.assertTrue(service.register(product));

        //Testa se o metodo atualiza funciona
        Product newProduct = new Product(
            "PS4 - Slim",
            1900,
            true
        );
        Assert.assertTrue(service.update(newProduct));
        Assert.assertEquals(service.search(newProduct.getName()).getPrice(), newProduct.getPrice());

        //Testa o metodo search
        Assert.assertEquals(product.getName(), service.search(product.getName()).getName());

        //Testa o metodo delete
        Assert.assertTrue(service.delete(product.getName()));
    }

}

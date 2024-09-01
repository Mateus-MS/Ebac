package main.java.services;

import main.java.entities.client.ClientDAO;
import main.java.entities.product.Product;
import main.java.entities.product.ProductDAO;
import main.java.entities.product.ProductService;
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

        ProductDAO      dao = ProductDAO.getInstance();
        ProductService service = new ProductService(dao);

        //Testa o metodo register
        Assert.assertTrue(service.register(product));

        //Testa o metodo search
        Assert.assertEquals(product.getName(), service.search(product.getName()).getName());

        //Testa o metodo delete
        Assert.assertTrue(service.delete(product.getName()));
    }

}

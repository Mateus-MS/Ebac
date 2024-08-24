package main.test.service;

import main.java.generics.DAO.IGenericDAO;
import main.java.generics.service.IGenericService;
import main.java.product.DAO.ProductDAOMock;
import main.java.product.Product;
import main.java.product.service.ProductService;
import main.test.generic.GenericServiceTest;

public class ProductServiceTest extends GenericServiceTest<Product> {


    @Override
    protected IGenericDAO<Product> createDAO() {
        return ProductDAOMock.getInstance();
    }

    @Override
    protected IGenericService<Product> createService(IGenericDAO<Product> dao) {
        return new ProductService(dao);
    }

    @Override
    protected Product createTestEntity(Double code) {
        return new Product(
                "Test",
                code,
                1500,
                false
        );
    }
}

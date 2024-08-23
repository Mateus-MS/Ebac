package main.test.produto;

import main.java.exceptions.DataAlreadyInDBException;
import main.java.exceptions.DataNotPresentInDBException;
import main.java.generics.DAO.IGenericDAO;
import main.java.product.DAO.ProductDAOMock;
import main.java.product.Product;
import main.java.product.service.ProductService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ProductServiceTest {

    private ProductService cli;

    @Test
    @Before
    public void init(){
        IGenericDAO<Product> productDAO = ProductDAOMock.getInstance();
        Assert.assertNotNull(productDAO);

        this.cli = new ProductService(productDAO);
        Assert.assertNotNull(this.cli);
    }

    @Test
    public void testProductRegister(){
        Product prod = new Product(
                "PS4 - slim",
                1000145,
                1500,
                true
        );

       Assert.assertTrue(this.cli.register(prod));
    }

    @Test(expected = DataAlreadyInDBException.class)
    public void testRegisterClientDuplicated(){
        Product prod = new Product(
                "PS4 - slim",
                10001245,
                1500,
                true
        );

        this.cli.register(prod);
        this.cli.register(prod);
    }

    @Test
    public void testPesquisa(){
        Product prod = new Product(
                "PS4 - slim",
                10301245,
                1500,
                true
        );

        this.cli.register(prod);
        Product result = this.cli.pesquisa(prod.getCodigo());
        Assert.assertEquals(prod, result);
    }

    @Test
    public void testPesquisaNull(){
        Product prod = new Product(
                "PS4 - slim",
                1051241245,
                1500,
                true
        );

        Assert.assertNull(this.cli.pesquisa(prod.getCodigo()));
    }

    @Test
    public void testAtualiza(){
        Product oldProduct = new Product(
                "PS4 - slim",
                10341245,
                1500,
                true
        );

        this.cli.register(oldProduct);
        Product newProduct = new Product(
                "PS5",
                oldProduct.getCodigo(),
                2000,
                true
        );

        Assert.assertTrue(this.cli.atualiza(newProduct));
        Assert.assertEquals(this.cli.pesquisa(oldProduct.getCodigo()), newProduct);
    }

    @Test(expected = DataNotPresentInDBException.class)
    public void testAtualizaNull(){
        Product oldProduct = new Product(
                "PS5",
                124214D,
                2000,
                true
        );

        Product newProduct = new Product(
                "PS5",
                oldProduct.getCodigo(),
                2000,
                true
        );

        Assert.assertTrue(this.cli.atualiza(newProduct));
        Assert.assertEquals(this.cli.pesquisa(oldProduct.getCodigo()), newProduct);
    }

    @Test
    public void testExcluir(){
        Product prod = new Product(
                "PS5",
                12484D,
                2000,
                true
        );

        this.cli.register(prod);
        Assert.assertTrue(this.cli.excluir(prod.getCodigo()));
    }

    @Test(expected = DataNotPresentInDBException.class)
    public void testExcluirNull(){
        Product prod = new Product(
                "PS5",
                12784D,
                2000,
                true
        );

        Assert.assertFalse(this.cli.excluir(prod.getCodigo()));
    }

}

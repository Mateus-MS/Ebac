import domain.dao.IProductDAO;
import domain.dao.ProductDAO;
import domain.model.Product;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class ProductTests {

    private final IProductDAO productDao;

    public ProductTests(){
        this.productDao = new ProductDAO();
    }

    @Test
    public void RegisterTest(){
        Product prod = new Product("PlayStation 2", 500f, 15);
        Product registeredProd = this.productDao.Register(prod);
        assertNotNull(registeredProd);
    }

}

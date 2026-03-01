package domain.dao;

import domain.model.Product;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ProductDAO implements IProductDAO{

    public Product Register(Product prod){
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("ExemploJPA");
        EntityManager manager = factory.createEntityManager();

        manager.getTransaction().begin();
        manager.persist(prod);
        manager.getTransaction().commit();

        manager.close();
        factory.close();
        return prod;
    }

}

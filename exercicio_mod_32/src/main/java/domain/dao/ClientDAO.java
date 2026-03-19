package domain.dao;

import domain.dao.generic.GenericDAO;
import domain.model.Client;
import domain.model.Product;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class ClientDAO extends GenericDAO<Client> implements IClientDAO {

    @Override
    public List<Product> getCart(Client client) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("ExemploJPA");
        EntityManager manager = factory.createEntityManager();

        List<Product> cart = manager.createQuery(
                            "SELECT p FROM Product p WHERE p.client = :client", Product.class
                             )
                             .setParameter("client", client)
                             .getResultList();

        manager.close();
        factory.close();

        return cart;
    }

}

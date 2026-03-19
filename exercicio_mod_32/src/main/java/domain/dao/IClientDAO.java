package domain.dao;

import domain.dao.generic.IGenericDAO;
import domain.model.Client;
import domain.model.Product;

import java.util.List;

public interface IClientDAO extends IGenericDAO<Client> {
    List<Product> getCart(Client client);
}

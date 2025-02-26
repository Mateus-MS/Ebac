package generics.service;

import connection.DBConnection;
import generics.DAO.GenericDAO;
import generics.DAO.IGenericDAO;

import java.sql.Connection;

/**
 * This class is a generic service that allows only one class be used to interact with
 * the DAOs. So it acts more like a facade layer.
 *
 * @param <Entity> The service in which this class will be specialized in.
 */
public class GenericService<Entity, IdentifierType> implements IGenericService<Entity, IdentifierType> {

    private final Connection db = DBConnection.getInstance();

    // Constructor
    private final GenericDAO<Entity, IdentifierType> genericDAO;
    public GenericService(GenericDAO<Entity, IdentifierType> genericDAO){
        this.genericDAO = genericDAO;
    }

    // "Facading" the methods from the GenericService.
    public boolean register(Entity entity){
        return this.genericDAO.register(entity);
    }

    public Entity search(IdentifierType identifier){
        return this.genericDAO.search(identifier);
    }

    public boolean update(Entity entity){
        return this.genericDAO.update(entity);
    }

    public boolean delete(IdentifierType identifier){
        return this.genericDAO.delete(identifier);
    }

}

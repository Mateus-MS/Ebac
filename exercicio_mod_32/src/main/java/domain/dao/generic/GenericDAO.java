package domain.dao.generic;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class GenericDAO<T> implements IGenericDAO<T> {

    @Override
    public T Register(T entity){
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("ExemploJPA");
        EntityManager manager = factory.createEntityManager();

        manager.getTransaction().begin();
        manager.persist(entity);
        manager.getTransaction().commit();

        manager.close();
        factory.close();
        return entity;
    }

}

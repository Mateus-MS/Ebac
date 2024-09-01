package main.java.entities.z_generics;

public class GenericService<Entity> {

    private final IGenericDAO<Entity> dao;
    public GenericService(IGenericDAO<Entity> dao){
        this.dao = dao;
    }

    public boolean register(Entity entity){
        return this.dao.register(entity);
    }

    public boolean delete(String code){
        return this.dao.delete(code);
    }

    public Entity search(String code){
        return this.dao.search(code);
    }

    public boolean update(Entity entity){
        return this.dao.update(entity);
    }

}

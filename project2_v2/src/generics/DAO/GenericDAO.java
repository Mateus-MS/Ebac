package generics.DAO;

import annotations.Serviceidentifier;
import jdk.jfr.Experimental;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class GenericDAO<EntityType, IdentifierType> implements IGenericDAO<EntityType, IdentifierType>{
    @Override
    public boolean register(EntityType entity) {
        if (entity == null) {
            throw new IllegalArgumentException("Entity cannot be null");
        }

        try {
            Class<?> clazz = entity.getClass();

            // Get the method used to uniquely identify this object
            IdentifierType identifier = getIdentifier(entity);
            System.out.println("Registering entity with the identifier: " + identifier);

            // Iterate over all fields
            for(Field field : clazz.getDeclaredFields()){
                // Since the fields are private
                field.setAccessible(true);

                // Get the field name and the value
                String fieldName = field.getName();
                Object fieldValue = field.get(entity);

                System.out.println("Field: " + fieldName + ", Value: " + fieldValue);
            }

            // Here i will save the data in DB, but for now, we'll just print a success message.
            System.out.println("Entity registered successfully!");
            return true;
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public EntityType search(IdentifierType identifier) {
        System.out.println("Updating entity with the identifier: " + identifier);
        return null;
    }

    @Override
    public boolean update(EntityType entity) {
        System.out.println("Updating entity: " + entity);
        return false;
    }

    @Override
    public boolean delete(IdentifierType identifier) {
        System.out.println("Updating entity with the identifier: " + identifier);
        return false;
    }

    private IdentifierType getIdentifier(EntityType entity){
        try {
            Class<?> clazz = entity.getClass();

            //Find the methods annotated with @ServiceIdentifier
            for(Method method : clazz.getMethods()){
                if(method.isAnnotationPresent(Serviceidentifier.class)){
                    return (IdentifierType) method.invoke(entity);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}

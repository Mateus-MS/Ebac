package generics.DAO;

/**
 * Explicit the methods that must be implemented by the DAOs.
 *
 * @param <EntityType> is the entity that this DAO will be specialized in.
 */
public interface IGenericDAO<EntityType, IdentifierType> {
    boolean register(EntityType entity);
    EntityType search(IdentifierType identifier);
    boolean update(EntityType entity);
    boolean delete(IdentifierType identifier);
}
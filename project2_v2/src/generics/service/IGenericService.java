package generics.service;

/**
 * Explicitly tells which methods a service should implement.
 *
 * @param <EntityType> Is the entity that this service will be specialized in. <br>
 *                    Example: <code>Client</code> <code>Employee</code>
 */
public interface IGenericService<EntityType, IdentifierType> {
    boolean register(EntityType entity);
    EntityType search(IdentifierType identifier);
    boolean update(EntityType entity);
    boolean delete(IdentifierType identifier);
}

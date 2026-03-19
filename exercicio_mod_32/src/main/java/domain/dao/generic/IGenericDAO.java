package domain.dao.generic;

public interface IGenericDAO<T> {
    T Register(T entity);
}

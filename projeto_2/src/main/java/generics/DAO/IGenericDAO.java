package main.java.generics.DAO;

public interface IGenericDAO<T> {
    boolean register(T entity);
    T pesquisa(Double code);
    boolean atualiza(T entity);
    boolean excluir(Double code);
}

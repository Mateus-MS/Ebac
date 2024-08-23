package main.java.generics.service;

/**
 * Atua como molde para todos objetos que
 * vão iteragir com banco de dados
 * provendo metodos necessarios para isso.
 *
 * @param <T> é o tipo ao qual esse irá se especializar
 */
public interface IGenericService<T> {
    boolean register(T entity);
    T pesquisa(Double code);
    boolean atualiza(T entity);
    boolean excluir(Double code);
}

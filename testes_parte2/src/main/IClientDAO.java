package main;

public interface IClientDAO {
    boolean salvar(String client);
    boolean remove(String client);
    boolean atualiza(String oldClient, String newClient);
}

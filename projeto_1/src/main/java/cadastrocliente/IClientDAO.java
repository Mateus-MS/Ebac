package cadastrocliente;

public interface IClientDAO {
    boolean cadastrar(Client client);
    Client getClient(String cpf);
    void attClient(Client client);
    void removeClient(String cpf);
}

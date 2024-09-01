package main.java.client;

public class ClientService {

    private final ClientDAO dao;
    public ClientService(ClientDAO dao){
        this.dao = dao;
    }

    public boolean register(Client client){
        return this.dao.register(client);
    }

    public boolean delete(String cpf){
        return this.dao.delete(cpf);
    }

    public Client search(String cpf){
        return this.dao.search(cpf);
    }

}

package main;

public class ClientService {

    private final IClientDAO clientDAO;

    public ClientService(IClientDAO clientDAO){
        this.clientDAO = clientDAO;
    }

    public boolean salvar(String client){
        return this.clientDAO.salvar(client);
    }

    public boolean remove(String client){
        return this.clientDAO.remove(client);
    }

    public boolean atualiza(String oldCLient, String newClient){
        return this.clientDAO.atualiza(oldCLient, newClient);
    }

}

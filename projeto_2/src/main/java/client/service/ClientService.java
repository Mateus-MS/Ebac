package main.java.client.service;

import main.java.client.Client;
import main.java.generics.DAO.IGenericDAO;

/**
 * <code>ClientService</code> atua como um interface
 * jutando o acesso a todas as informações
 * e funções referente a <code>Client</code> em um unico lugar
 */
public class ClientService implements IClientService{

    /**
     * Recebe uma instancia do dao externamente
     *
     * configurando uma dependenci injection <br>
     * "Quem usa não instancia" - Mateus 2024.
     */
    private final IGenericDAO<Client> clientDAO;
    public ClientService(IGenericDAO<Client> clientDAO){
        this.clientDAO = clientDAO;
    }

    @Override
    public boolean register(Client client){
        return this.clientDAO.register(client);
    }

    @Override
    public Client pesquisa(Double cpf){
        return this.clientDAO.pesquisa(cpf);
    }

    @Override
    public boolean atualiza(Client client){
        return this.clientDAO.atualiza(client);
    }

    @Override
    public boolean excluir(Double cpf){
        return this.clientDAO.excluir(cpf);
    }

}

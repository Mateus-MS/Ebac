package main.java.client.DAO;

import main.java.client.Client;
import main.java.generics.DAO.IGenericDAO;
import main.java.product.DAO.ProductDAO;

/**
 * Singleton <br>
 *
 * Responsavel por conectar com o banco de dados
 * e realiza toda a logica para os metodos. <br>
 *
 */
public class ClientDAO implements IGenericDAO<Client> {

    private  static ClientDAO instance;
    private ClientDAO(){}

    public static ClientDAO getInstance(){
        if(instance == null){
            instance = new ClientDAO();
        }
        return instance;
    }

    @Override
    public boolean register(Client client){
        return false;
    }

    @Override
    public Client pesquisa(Double cpf){
        return null;
    }

    @Override
    public boolean atualiza(Client client){
        return false;
    }

    @Override
    public boolean excluir(Double cpf){
        return false;
    }

}

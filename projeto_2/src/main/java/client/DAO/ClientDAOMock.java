package main.java.client.DAO;

import main.java.client.Client;
import main.java.exceptions.DataAlreadyInDBException;
import main.java.exceptions.DataNotPresentInDBException;
import main.java.generics.DAO.IGenericDAO;

import java.util.HashMap;
import java.util.Map;

/**
 * Em uma aplicação real o <code>ClientDAO</code> seria a parte real da aplicação <br>
 * <br>
 * Mas nesse exemplo o <code>ClientDAOMock</code> será usado como parte real aqui. <br>
 * Já que não temos acesso ao banco de dados.
 */
public class ClientDAOMock implements IGenericDAO<Client> {

    private  static ClientDAOMock instance;
    private ClientDAOMock(){

    }

    public static ClientDAOMock getInstance(){
        if(instance == null){
            instance = new ClientDAOMock();
        }
        return instance;
    }

    //Talvez seja bem mais inteligente
    //Ao invés de usar um Map usar um LinkedHashSet
    //Já que uma das caracteristicas do register e não ter clientes duplicados
    //Mas para fins didaticos e de testes vou usar um Map mesmo :D
    private final Map<Double, Client> clients = new HashMap<>();

    /**
     * Salva um cliente no banco de dados :D <br><br>
     * Levanta um <code>DataAlreadyInDB</code> caso tente registrar clientes com o mesmo <code>CPF</code> <br>
     */
    @Override
    public boolean register(Client client){
        //Tenta procurar um cliente com o mesmo cpf no sistema
        Client test = this.clients.get(client.getCpf());

        //Se encontrar algum, levanta o erro
        if(test != null){
            throw new DataAlreadyInDBException("CPF já registrado.");
        }

        //Salva no sistema
        this.clients.put(client.getCpf(), client);
        return true;
    }

    /**
     * @return Se o <code>cpf</code> recebido existir no banco de dados retorna um <code>Client</code> se não retorna <code>null</code>
     */
    @Override
    public Client pesquisa(Double cpf){

        return this.clients.get(cpf);

    }

    /**
     * Atualiza as informações do cliente recebido no banco de dados :D <br><br>
     * Levanta um <code>DataNotPresentInDBException</code> caso tente atualizar um cliente que ainda não exista em sistema.<br>
     */
    @Override
    public boolean atualiza(Client client){
        //Testa se o client recebido já existe em sistema
        Client test = this.pesquisa(client.getCpf());

        //Se esse client ainda não existir em sistema
        //Não é possível atualizar

        //Levanta a exception DataNotPresentInDB
        if(test == null){
            throw new DataNotPresentInDBException("Cliente ainda não registrado no sistema");
        }

        //Atualiza as informações no DB
        this.clients.replace(client.getCpf(), client);
        return true;
    }

    @Override
    public boolean excluir(Double cpf){
        //Testa se o cliente existe no banco de dados
        Client test = this.pesquisa(cpf);

        //Se não existir
        if(test == null){
            throw new DataNotPresentInDBException("Impossível excluir cliente não registrado no sistema");
        }

        //Se existir
        this.clients.remove(cpf);
        return true;
    }

}

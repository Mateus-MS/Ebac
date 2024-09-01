package main.java.entities.client;

import main.java.domain.ConnectionSingleton;
import main.java.entities.z_generics.IGenericDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

/**
 * Funciona como um Singleton <br>
 * Responsável por toda a lógica relacionada ao Client.
 */
public class ClientDAO implements IGenericDAO<Client> {

    //Objeto estático
    private static ClientDAO instance;

    //Construtor privado
    private ClientDAO(){}

    //Método estático
    public static ClientDAO getInstance(){
        if(instance == null){
            instance = new ClientDAO();
        }
        return instance;
    }

    @Override
    public boolean register(Client client) {
        Connection connection = null;
        PreparedStatement stm = null;

        //Estabelece conexão com banco de dados
        connection = ConnectionSingleton.getInstance();

        //Cria a query a ser executada
        String sql = "INSERT INTO clients (name, cpf, idade, sexo, endereco) " +
                     "VALUES (?, ?, ?, ?, ?)";

        //TODO TRATAR MELHOR O ERRO!
        try {
            //Cria o objeto da query
            stm = connection.prepareStatement(sql);
            //Insere os parametros do cliente no lugar das interrogações
            addParams(stm, Arrays.asList(
                client.getName(),
                client.getCpf(),
                client.getIdade(),
                client.getSexo(),
                client.getEndereco())
            );
            //Executa a query
            //Quando não a query não retorna nada se usa esse método
            stm.executeUpdate();
        } catch (SQLException e){
            throw new RuntimeException("TRATAR MELHOR O ERRO!");
        } finally {
            closeConnection(connection, stm, null);
        }
        return true;
    }

    @Override
    public boolean delete(String cpf){
        Connection connection = null;
        PreparedStatement stm = null;
        boolean result = false;

        //Estabelece conexão com banco de dados
        connection = ConnectionSingleton.getInstance();

        //Query
        String sql = "DELETE FROM clients WHERE cpf = ?";

        try {
            //Valida a query
            stm = connection.prepareStatement(sql);
            //Adiciona o cpf a query
            addParams(stm, Arrays.asList(cpf));
            //Executa a query
            result = stm.executeUpdate() == 1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            closeConnection(connection, stm, null);
        }
        return result;
    }

    @Override
    public Client search(String _cpf){
        Connection connection    = null;
        PreparedStatement stm    = null;
        ResultSet         rs     = null;
        Client            client = null;

        //Estabelece conexão com banco de dados
        connection = ConnectionSingleton.getInstance();

        //Query
        String sql = "SELECT * FROM clients WHERE cpf = ?";
        try {
            //Valida a query
            stm = connection.prepareStatement(sql);
            //Adiciona o cpf a query
            addParams(stm, Arrays.asList(_cpf));
            //Executa a query
            rs = stm.executeQuery();
            //Se encontrar algo
            if(rs.next()){
                //Extrai os dados
                String    name     = rs.getString("NAME");
                String    cpf      = rs.getString("CPF");
                int       idade    = rs.getInt("IDADE");
                Character sexo     = rs.getString("SEXO").charAt(0);
                String    endereco = rs.getString("ENDERECO");
                //Cria um objeto com os dados
                client = new Client(name, cpf, idade, sexo, endereco);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            closeConnection(connection, stm, null);
        }
        return client;
    }

    @Override
    public boolean update(Client client){
        return false;
    }

}

package main.java.client;

import main.java.domain.ConnectionSingleton;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Funciona como um Singleton <br>
 * Responsável por toda a lógica relacionada ao Client.
 */
public class ClientDAO {

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
            addParamsRegister(stm, client);
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

    private void addParamsRegister(PreparedStatement stm, Client client) throws SQLException {
        stm.setString(1, client.getName());
        stm.setString(2, client.getCpf());
        stm.setInt   (3, client.getIdade());
        stm.setString(4, client.getSexo().toString());
        stm.setString(5, client.getEndereco());
    }

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
            addParamsDelete(stm, cpf);
            //Executa a query
            result = stm.executeUpdate() == 1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            closeConnection(connection, stm, null);
        }
        return result;
    }

    private void addParamsDelete(PreparedStatement stm, String cpf) throws SQLException {
        stm.setString(1, cpf);
    }

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
            addParamsSearch(stm, _cpf);
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

    private void addParamsSearch(PreparedStatement stm, String cpf) throws SQLException {
        stm.setString(1, cpf);
    }

    public static void closeConnection(Connection connection, PreparedStatement stm, ResultSet rs){
        //TODO TRATAR MELHOR O ERRO!
        try {
            if(connection != null && !connection.isClosed()){
                connection.close();
            }

            if(stm != null && !stm.isClosed()){
                stm.close();
            }

            if(rs != null && !rs.isClosed()){
                rs.close();
            }
        } catch (SQLException e){
            throw new RuntimeException("TRATAR MELHOR O ERRO!");
        }
    }

}

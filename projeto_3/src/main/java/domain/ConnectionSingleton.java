package main.java.domain;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Funciona como um Singleton mantendo apenas uma conexão com o banco de dados
 * aberta por vez.
 */
public class ConnectionSingleton {

    private static Connection connection;

    private ConnectionSingleton(){}

    public static Connection getInstance(){
        //TODO TRATAR MELHOR ESSE ERRO
        try{
            if(connection == null || connection.isClosed()){
                connection = initConnection();
            }
        } catch (SQLException e){
            throw new RuntimeException("TRATA MELHOR ESSE ERRO!");
        }
        return connection;
    }

    private static Connection initConnection(){
        String url  = "jdbc:postgresql://localhost:2345/cluster";
        String user = "adm";
        String pass = "adm";

        //TODO TRATAR MELHOR ESSE ERRO
        try{
            return DriverManager.getConnection(url, user, pass);
        } catch (SQLException e) {
            throw new RuntimeException("TRATA MELHOR ESSE ERRO!");
        }
    }

}

package connection;

import io.github.cdimascio.dotenv.Dotenv;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

    // Connection instance
    private static Connection connection;

    // Private constructor
    private DBConnection(){}

    public static Connection getInstance(){
        try {
            if(connection == null || connection.isClosed()) {
                connection = initConnection();
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return connection;
    }

    private static Connection initConnection(){
        Dotenv dotenv = Dotenv.load();

        String url = "jdbc:postgresql://localhost:5433/" + dotenv.get("DB_NAME");

        try {
            return DriverManager.getConnection(url, dotenv.get("DB_USER"), dotenv.get("DB_PASS"));
        } catch (Exception e){
            e.printStackTrace();
        }
        throw new RuntimeException("Something went wrong while initiating the connection!");
    }
}

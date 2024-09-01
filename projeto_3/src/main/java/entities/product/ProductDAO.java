package main.java.entities.product;

import main.java.domain.ConnectionSingleton;
import main.java.entities.z_generics.IGenericDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

public class ProductDAO implements IGenericDAO<Product> {

    //Objeto estático
    private static ProductDAO instance;

    //Construtor privado
    private ProductDAO(){}

    //Método estático
    public static ProductDAO getInstance(){
        if(instance == null){
            instance = new ProductDAO();
        }
        return instance;
    }

    @Override
    public boolean register(Product product){
        Connection        conn = null;
        PreparedStatement stm = null;

        //Estabelece conexão com o banco de dados
        conn = ConnectionSingleton.getInstance();

        //Cria a query a ser executada
        String sql = "INSERT INTO products (name, price, available) " +
                     "VALUES (?, ?, ?)";

        try {
            stm = conn.prepareStatement(sql);

            addParams(stm, Arrays.asList(
                    product.getName(),
                    product.getPrice(),
                    product.getAvailable()
            ));

            stm.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            closeConnection(conn, stm, null);
        }
        return true;
    }

    @Override
    public boolean delete(String code){
        Connection connection = null;
        PreparedStatement stm = null;
        boolean result = false;

        //Estabelece conexão com banco de dados
        connection = ConnectionSingleton.getInstance();

        //Query
        String sql = "DELETE FROM products WHERE name = ?";

        try {
            //Valida a query
            stm = connection.prepareStatement(sql);
            //Adiciona o cpf a query
            addParams(stm, Arrays.asList(code));
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
    public Product search(String code){
        Connection connection    = null;
        PreparedStatement stm    = null;
        ResultSet rs             = null;
        Product product          = null;

        //Estabelece conexão com banco de dados
        connection = ConnectionSingleton.getInstance();

        //Query
        String sql = "SELECT * FROM products WHERE name = ?";
        try {
            //Valida a query
            stm = connection.prepareStatement(sql);
            //Adiciona o cpf a query
            addParams(stm, Arrays.asList(code));
            //Executa a query
            rs = stm.executeQuery();
            //Se encontrar algo
            if(rs.next()){
                //Extrai os dados
                String  name      = rs.getString("NAME");
                int     price     = rs.getInt("PRICE");
                boolean available = rs.getBoolean("AVAILABLE");
                //Cria um objeto com os dados
                product = new Product(name, price, available);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            closeConnection(connection, stm, null);
        }
        return product;
    }

    @Override
    public boolean update(Product product){
        Connection connection    = null;
        PreparedStatement stm    = null;
        boolean result = false;

        //Estabelece conexão com banco de dados
        connection = ConnectionSingleton.getInstance();

        //Query
        String sql = "UPDATE products " +
                     "SET name = ?, price = ?, available = ? " +
                     "WHERE name = ?";
        try {
            //Valida a query
            stm = connection.prepareStatement(sql);
            addParams(stm, Arrays.asList(
                    product.getName(),
                    product.getPrice(),
                    product.getAvailable(),
                    product.getName()
            ));
            //Executa a query
            result = stm.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            closeConnection(connection, stm, null);
        }
        return result;
    }

}

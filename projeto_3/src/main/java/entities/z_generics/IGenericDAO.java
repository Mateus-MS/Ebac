package main.java.entities.z_generics;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface IGenericDAO<Entity> {

    boolean register(Entity entity);

    boolean delete(String cpf);

    Entity search(String cpf);

    boolean update(Entity entity);

    default void closeConnection(Connection conn, PreparedStatement stm, ResultSet rs){
        //TODO TRATAR MELHOR O ERRO!
        try {
            if(conn != null && !conn.isClosed()){
                conn.close();
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

    /**
     * Supported types
     * - String
     * - Integer
     * - Boolean
     * - Double
     * - Character
     */
    default void addParams(PreparedStatement stm, List<Object> params) throws SQLException {
        for(int i = 1; i <= params.size(); i++){
            Object param = params.get(i - 1);

            if(param instanceof String){
                stm.setString(i, (String) param);
                continue;
            }

            if(param instanceof Integer){
                stm.setInt(i, (Integer) param);
                continue;
            }

            if(param instanceof Boolean){
                stm.setBoolean(i, (Boolean) param);
                continue;
            }

            if(param instanceof Double){
                stm.setDouble(i, (Double) param);
                continue;
            }

            if(param instanceof Character){
                stm.setString(i, String.valueOf(param));
                continue;
            }

            throw new RuntimeException("Tipo passado inválido");

        }
    }

}

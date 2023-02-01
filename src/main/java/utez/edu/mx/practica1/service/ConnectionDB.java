package utez.edu.mx.practica1.service;

import java.sql.*;
import com.mysql.jdbc.Driver;
public class ConnectionDB {
    public static Connection getConnection() throws SQLException {
        String host = "127.0.0.1";
        String port = "3306";
        String database = "dbpersona";
        String useSSL = "false";
        String timezone = "UTC";
        String url = String.format("jdbc:mysql://%s:%s/%s?useSSL=%s&serverTimezone=%s", host, port, database, useSSL, timezone);
        DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
        return DriverManager.getConnection(url, "root", "root");
    }
    public static void closeConnections(Connection con, PreparedStatement pstm, ResultSet rs){
        try{
            if(rs != null){ rs.close(); }

            if(pstm != null){ pstm.close(); }

            if(con != null){ con.close(); }

        }catch(SQLException e){ }
    }

    public static void closeConnections(Connection con, PreparedStatement pstm){
        try{
            if(pstm != null){ pstm.close(); }

            if(con != null){ con.close(); }

        }catch(SQLException e){ }
    }

    public static void main(String[] args) {
        try{
            Connection con = ConnectionDB.getConnection();
            System.out.println("Conexion exitosa!");
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}

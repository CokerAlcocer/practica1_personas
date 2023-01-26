package utez.edu.mx.practica1.service;

import java.sql.*;
import com.mysql.jdbc.Driver;
public class ConnectionDB {
    public static Connection getConnection() throws SQLException {
        DriverManager.registerDriver(new Driver());
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/dbpersona?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC","root","root");
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

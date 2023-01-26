package utez.edu.mx.practica1.model.person;

import java.sql.*;

public class DaoPerson {
    //Variables globales
    Connection con;
    PreparedStatement pstm;
    Statement stm;
    ResultSet rs;
    String query = "";

    //Método de cierre de conexión
    public void closeConnection(){
        try{
            if(pstm != null){
                pstm.close();
            }
            if(stm != null){
                stm.close();
            }
            if(con != null){
                con.close();
            }
            if(rs != null){
                rs.close();
            }
        } catch (SQLException ex) {
            System.out.println("CC-ERR-001");
        }
    }

    // EncontrarTodos
    // TODO

    // EncontrarPorId
    // TODO

    // Create
    // TODO

    // Update
    // TODO

    // Delete
    // TODO
}

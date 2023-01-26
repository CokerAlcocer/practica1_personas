package utez.edu.mx.practica1.model.person;

import utez.edu.mx.practica1.service.ConnectionDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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
    public List<DaoPerson> findAll(){
        List<DaoPerson> personas = new ArrayList<>();

        try {
            query = "SELECT * FROM persona";
            con = ConnectionDB.getConnection();
            stm = con.createStatement();
            rs = stm.executeQuery(query);

            while(rs.next()){
                personas.add(new DaoPerson(
                        rs.getInt("idPerson"),
                        rs.getString("nombre"),
                        rs.getString("aPaterno");
                        rs.getString("aMaterno");
                        rs.getInt("edad");
                        rs.getString("sexo");
                        rs.getString()
                ));
            }
        } catch (SQLException ex) {
            System.out.println("FA-ERR-002");
        } finally {

        }

        return personas;
    }

    // EncontrarPorId
    // TODO

    // Create
    // TODO

    // Update
    // TODO

    // Delete
    // TODO
}

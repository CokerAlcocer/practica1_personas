package utez.edu.mx.practica1.model.person;

import utez.edu.mx.practica1.service.ConnectionDB;

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
    public boolean update(BeanPerson person){
        boolean flag = false;
        try{
            con = ConnectionDB.getConnection();
            pstm = con.prepareStatement("UPDATE persona SET nombre=?,aPaterno=?,aMaterno=?,edad=?,sexo=?,telefono=?,direccion=?,fechaNacimiento=?,estadoCivil=?,correo=?,contrasena=? WHERE idUser=?");

            pstm.setString(1,person.getNombre());
            pstm.setString(2,person.getaPaterno());
            pstm.setString(3,person.getaMaterno());
            pstm.setInt(4,person.getEdad());
            pstm.setString(5,person.getSexo());
            pstm.setString(6,person.getTelefono());
            pstm.setString(7,person.getDireccion());
            pstm.setString(8,person.getFechaNacimiento());
            pstm.setBoolean(2,person.isEstadoCivil());
            pstm.setString(2,person.getCorreo());
            pstm.setString(2,person.getContrasena());
            pstm.setInt(2,person.getIduser());

            flag = pstm.execute();
        }catch (SQLException e){
            e.getMessage();
        }finally {
            ConnectionDB.closeConnections(con,pstm);
        }
        return flag;

    }
    public void delete_logic(int id){
        try{
            con = ConnectionDB.getConnection();
            pstm = con.prepareCall("UPDATE persona SET estado=false WHERE idUser=?");

            pstm.setInt(1,id);

            pstm.executeUpdate();
        }catch(SQLException e){
            e.getMessage();
        }finally {
            ConnectionDB.closeConnections(con,pstm);
        }
    }
    // Delete
    public void delete(int id){
        try{
            con = ConnectionDB.getConnection();
            pstm = con.prepareCall("DELETE FROM personas idUser=?");

            pstm.setInt(1,id);

            pstm.executeUpdate();
        }catch(SQLException e){
           e.getMessage();
        }finally {
            ConnectionDB.closeConnections(con,pstm);
        }
    }
}

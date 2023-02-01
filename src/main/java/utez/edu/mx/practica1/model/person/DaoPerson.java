package utez.edu.mx.practica1.model.person;

import utez.edu.mx.practica1.service.ConnectionDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;



public class DaoPerson {
    //Variables globales
    Connection con;
    PreparedStatement pstm;
    CallableStatement cstm;
    Statement stm;
    ResultSet rs;
    // String query = "";

    // EncontrarTodos
    public List<BeanPerson> findAll(){
        List<BeanPerson> listPersons = new ArrayList<>();
        try{
            con = ConnectionDB.getConnection();
            pstm = con.prepareStatement("SELECT * FROM personas");
            rs = pstm.executeQuery();
            while (rs.next()) {
                BeanPerson person = new BeanPerson();

                person.setIduser(rs.getInt("idUser"));
                person.setNombre(rs.getString("nombre"));
                person.setaPaterno(rs.getString("aPaterno"));
                person.setaMaterno(rs.getString("aMaterno"));
                person.setEdad(rs.getInt("edad"));
                person.setSexo(rs.getString("sexo"));
                person.setTelefono(rs.getString("telefono"));
                person.setDireccion(rs.getString("direccion"));
                person.setFechaNacimiento(rs.getString("fechaNacimiento"));
                person.setEstadoCivil(rs.getBoolean("estadoCivil"));
                person.setCorreo(rs.getString("correo"));
                person.setContrasena(rs.getString("contrasena"));

                listPersons.add(person);
            }
        }catch (Exception e) {
            e.getMessage();
        }finally {
            ConnectionDB.closeConnections(con,pstm);
        }
        return listPersons;
    }

    // EncontrarPorId
    public BeanPerson findById(int id){
        BeanPerson person = null;
        try {
            con = ConnectionDB.getConnection();
            pstm = con.prepareStatement("SELECT * FROM personas WHERE idUser=?");
            pstm.setInt(1,id);
            rs = pstm.executeQuery();

            person = new BeanPerson();

            person.setIduser(rs.getInt("idUser"));
            person.setNombre(rs.getString("nombre"));
            person.setaPaterno(rs.getString("aPaterno"));
            person.setaMaterno(rs.getString("aMaterno"));
            person.setEdad(rs.getInt("edad"));
            person.setSexo(rs.getString("sexo"));
            person.setTelefono(rs.getString("telefono"));
            person.setDireccion(rs.getString("direccion"));
            person.setFechaNacimiento(rs.getString("fechaNacimiento"));
            person.setEstadoCivil(rs.getBoolean("estadoCivil"));
            person.setCorreo(rs.getString("correo"));
            person.setContrasena(rs.getString("contrasena"));

        } catch (Exception e) {
            e.getMessage();
        }finally {
            ConnectionDB.closeConnections(con,pstm);
        }

        return person;
    }

    // Create
    public boolean create(BeanPerson person){
        boolean flag = false;

        try{
            con = ConnectionDB.getConnection();
            pstm = con.prepareStatement("INSERT INTO personas(nombre,aPaterno,aMaterno,edad,sexo,telefono,direccion,fechaNacimiento,estadoCivil,correo,contrasena) VALUES (?,?,?,?,?,?,?,?,?,?,?);");
            pstm.setString(1,person.getNombre());
            pstm.setString(2,person.getaPaterno());
            pstm.setString(3,person.getaMaterno());
            pstm.setInt(4,person.getEdad());
            pstm.setString(5,person.getSexo());
            pstm.setString(6,person.getTelefono());
            pstm.setString(7,person.getDireccion());
            pstm.setString(8,person.getFechaNacimiento());
            pstm.setBoolean(9,person.isEstadoCivil());
            pstm.setString(10,person.getCorreo());
            pstm.setString(11,person.getContrasena());

            flag = pstm.execute();
            
        }catch (SQLException e){
            e.getMessage();
        }finally {
            ConnectionDB.closeConnections(con,pstm);
        }
        return flag;
    }

    // Update
    public boolean update(BeanPerson person){
        boolean flag = false;
        try{
            con = ConnectionDB.getConnection();
            pstm = con.prepareStatement("UPDATE personas SET nombre=?,aPaterno=?,aMaterno=?,edad=?,sexo=?,telefono=?,direccion=?,fechaNacimiento=?,estadoCivil=?,correo=?,contrasena=? WHERE idUser=?");

            pstm.setString(1,person.getNombre());
            pstm.setString(2,person.getaPaterno());
            pstm.setString(3,person.getaMaterno());
            pstm.setInt(4,person.getEdad());
            pstm.setString(5,person.getSexo());
            pstm.setString(6,person.getTelefono());
            pstm.setString(7,person.getDireccion());
            pstm.setString(8,person.getFechaNacimiento());
            pstm.setBoolean(9,person.isEstadoCivil());
            pstm.setString(10,person.getCorreo());
            pstm.setString(11,person.getContrasena());
            pstm.setInt(12,person.getIduser());

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
            pstm = con.prepareCall("UPDATE personas SET estado=false WHERE idUser=?");

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
            pstm = con.prepareCall("DELETE FROM personas WHERE idUser=?");

            pstm.setInt(1,id);

            pstm.executeUpdate();
        }catch(SQLException e){
           e.getMessage();
        }finally {
            ConnectionDB.closeConnections(con,pstm);
        }
    }
}

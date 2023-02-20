package utez.edu.mx.practica1.model.person;

import utez.edu.mx.practica1.service.ConnectionDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DaoPerson {
    //Variables globales
    Connection con;
    PreparedStatement pstm;
    ResultSet rs;

    // EncontrarTodos
    public List<BeanPerson> findAll(){
        List<BeanPerson> listPersons = new ArrayList<>();
        BeanPerson person = null;
        try{
            con = ConnectionDB.getConnection();
            pstm = con.prepareStatement("SELECT * FROM persona");
            rs = pstm.executeQuery();
            while (rs.next()) {
                person = new BeanPerson();

                person.setId(rs.getLong("id"));
                person.setNombre(rs.getString("nombre"));
                person.setaPaterno(rs.getString("a_paterno"));
                person.setaMaterno(rs.getString("a_materno"));
                person.setEdad(rs.getInt("edad"));
                person.setSexo(rs.getString("sexo"));
                person.setTelefono(rs.getString("telefono"));
                person.setDireccion(rs.getString("direccion"));
                person.setFechaNacimiento(rs.getString("fecha_nacimiento"));
                person.setEstadoCivil(rs.getString("estado_civil"));
                person.setCorreo(rs.getString("correo"));
                person.setContrasena(rs.getString("contrasena"));

                listPersons.add(person);
            }
        }catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("DP_ERR_01");
        }finally {
            ConnectionDB.closeConnections(con,pstm);
        }
        return listPersons;
    }


    
    // EncontrarPorId
    public BeanPerson findById(Long id){
        BeanPerson person = null;
        try {
            con = ConnectionDB.getConnection();
            pstm = con.prepareStatement("SELECT * FROM persona WHERE id = ?");
            pstm.setLong(1,id);
            rs = pstm.executeQuery();
            if(rs.next()){
                person = new BeanPerson();

                person.setId(rs.getLong("id"));
                person.setNombre(rs.getString("nombre"));
                person.setaPaterno(rs.getString("a_paterno"));
                person.setaMaterno(rs.getString("a_materno"));
                person.setEdad(rs.getInt("edad"));
                person.setSexo(rs.getString("sexo"));
                person.setTelefono(rs.getString("telefono"));
                person.setDireccion(rs.getString("direccion"));
                person.setFechaNacimiento(rs.getString("fecha_nacimiento"));
                person.setEstadoCivil(rs.getString("estado_civil"));
                person.setCorreo(rs.getString("correo"));
                person.setContrasena(rs.getString("contrasena"));
            }

        } catch (Exception e) {
            System.out.println("DP_ERR_02");
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
            pstm = con.prepareStatement("INSERT INTO persona(nombre, a_paterno, a_materno, edad, sexo, telefono, direccion, " +
                    "fecha_nacimiento, estado_civil, correo, contrasena) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)", PreparedStatement.RETURN_GENERATED_KEYS);
            pstm.setString(1,person.getNombre());
            pstm.setString(2,person.getaPaterno());
            pstm.setString(3,person.getaMaterno());
            pstm.setLong(4,person.getEdad());
            pstm.setString(5,person.getSexo());
            pstm.setString(6,person.getTelefono());
            pstm.setString(7,person.getDireccion());
            pstm.setString(8,person.getFechaNacimiento());
            pstm.setString(9,person.getEstadoCivil());
            pstm.setString(10,person.getCorreo());
            pstm.setString(11,person.getContrasena());

            rs = pstm.getGeneratedKeys();
            if(pstm.executeUpdate() == 1 && rs.first()){
                pstm = con.prepareStatement("SELECT * FROM person WHERE id = ?");
                pstm.setLong(1, rs.getLong(1));

                if(rs.next()){
                    //QUERY PARA GENERAR LA OPERACIÓN
                    flag = true;
                }
            }
        }catch (SQLException e){
            System.out.println("DP_ERR_03");
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
            pstm = con.prepareStatement("UPDATE persona SET nombre = ?, a_paterno = ?, a_materno = ?, edad = ?, sexo = ?, telefono = ?, " +
                    "direccion = ?, fecha_nacimiento = ?, estado_civil = ?, correo = ? WHERE id = ?");
            pstm.setString(1,person.getNombre());
            pstm.setString(2,person.getaPaterno());
            pstm.setString(3,person.getaMaterno());
            pstm.setInt(4,person.getEdad());
            pstm.setString(5,person.getSexo());
            pstm.setString(6,person.getTelefono());
            pstm.setString(7,person.getDireccion());
            pstm.setString(8,person.getFechaNacimiento());
            pstm.setString(9,person.getEstadoCivil());
            pstm.setString(10,person.getCorreo());
            pstm.setLong(11, person.getId());

            if(pstm.executeUpdate() == 1){
                // QUERY PARA REGISTRAR LA OPERACIÓN

                flag = true;
            }
        }catch (SQLException e){
            System.out.println("DP_ERR_04");
        }finally {
            ConnectionDB.closeConnections(con,pstm);
        }
        return flag;
    }


    public void delete_logic(int id){
        try{
            con = ConnectionDB.getConnection();
            pstm = con.prepareCall("UPDATE persona SET estado=false WHERE idPersona=?");
            pstm.setInt(1,id);
            pstm.executeUpdate();
        }catch(SQLException e){
            e.getMessage();
        }finally {
            ConnectionDB.closeConnections(con,pstm);
        }
    }

    // Delete
    public boolean delete(Long id){
        System.out.println(id);
        boolean flag=false;
        try{
            con = ConnectionDB.getConnection();
            pstm = con.prepareCall("DELETE FROM persona WHERE id=?");

            pstm.setLong(1,id);

            flag = pstm.executeUpdate() == 1;
        }catch(SQLException e){
            System.out.println("DP_ERR_05");
        }finally {
            ConnectionDB.closeConnections(con,pstm);
        }
        return flag;
    }
}

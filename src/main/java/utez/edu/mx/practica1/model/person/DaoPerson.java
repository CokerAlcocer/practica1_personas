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
            pstm = con.prepareStatement("SELECT * FROM persona");
            rs = pstm.executeQuery();
            while (rs.next()) {
                BeanPerson person = new BeanPerson();

                person.setIduser(rs.getInt("idPersona"));
                person.setNombre(rs.getString("nombre"));
                person.setaPaterno(rs.getString("aPaterno"));
                person.setaMaterno(rs.getString("aMaterno"));
                person.setEdad(rs.getInt("edad"));
                person.setSexo(rs.getString("sexo"));
                person.setTelefono(rs.getString("telefono"));
                person.setDireccion(rs.getString("direccion"));
                person.setFechaNacimiento(rs.getString("fechaNacimiento"));
                person.setEstadoCivil(rs.getString("estadoCivil"));
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
                        rs.getString("correo");
                ));
            }
        } catch (SQLException ex) {
            System.out.println("FA-ERR-002");
        } finally {

        }

        return personas;
    }
    
    // EncontrarPorId
    public BeanPerson findById(int id){
        BeanPerson person = null;
        try {
            con = ConnectionDB.getConnection();
            pstm = con.prepareStatement("SELECT * FROM persona WHERE idPersona=?");
            pstm.setInt(1,id);
            rs = pstm.executeQuery();

            person = new BeanPerson();

            person.setIduser(rs.getInt("idPersona"));
            person.setNombre(rs.getString("nombre"));
            person.setaPaterno(rs.getString("aPaterno"));
            person.setaMaterno(rs.getString("aMaterno"));
            person.setEdad(rs.getInt("edad"));
            person.setSexo(rs.getString("sexo"));
            person.setTelefono(rs.getString("telefono"));
            person.setDireccion(rs.getString("direccion"));
            person.setFechaNacimiento(rs.getString("fechaNacimiento"));
            person.setEstadoCivil(rs.getString("estadoCivil"));
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
            pstm = con.prepareStatement("INSERT INTO persona(nombre,aPaterno,aMaterno,edad,sexo,telefono,direccion,fechaNacimiento,estadoCivil,correo,contrasena) VALUES (?,?,?,?,?,?,?,?,?,?,?);");
            pstm.setString(1,person.getNombre());
            pstm.setString(2,person.getaPaterno());
            pstm.setString(3,person.getaMaterno());
            pstm.setInt(4,person.getEdad());
            pstm.setString(5,person.getSexo());
            pstm.setString(6,person.getTelefono());
            pstm.setString(7,person.getDireccion());
            pstm.setString(8,person.getFechaNacimiento());
            pstm.setString(9,person.isEstadoCivil());
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
            pstm = con.prepareStatement("UPDATE persona SET nombre=?,aPaterno=?,aMaterno=?,edad=?,sexo=?,telefono=?,direccion=?,fechaNacimiento=?,estadoCivil=?,correo=?,contrasena=? WHERE idPersona=?");

            pstm.setString(1,person.getNombre());
            pstm.setString(2,person.getaPaterno());
            pstm.setString(3,person.getaMaterno());
            pstm.setInt(4,person.getEdad());
            pstm.setString(5,person.getSexo());
            pstm.setString(6,person.getTelefono());
            pstm.setString(7,person.getDireccion());
            pstm.setString(8,person.getFechaNacimiento());
            pstm.setString(9,person.isEstadoCivil());
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
    public boolean delete(int id){
        boolean flag=false;
        try{
            con = ConnectionDB.getConnection();
            pstm = con.prepareCall("DELETE FROM persona WHERE idPersona=?");

            pstm.setInt(1,id);

            flag=pstm.executeUpdate() == 1;
        }catch(SQLException e){
           e.getMessage();
        }finally {
            ConnectionDB.closeConnections(con,pstm);
        }
        return flag;
    }

    public static void main(String[] args) throws SQLException {
        DaoPerson daoP = new DaoPerson();
        BeanPerson persona = new BeanPerson(0,"Sergio","Cortes","Popoca",25,
                "Masculino","7775957459","Las Rosas #13","2000-09-12","Soltero",
                "sergiocortes518@gmail.com","1234t",true);
        BeanPerson persona1 = new BeanPerson(1,"Sergio","Alonso","Uribe",25,
                "Masculino","7775957459","Las Rosas #13","2000-09-12","Soltero",
                "sergiocortes518@gmail.com","1234t",true);
        //daoP.create(persona);
        int idPersona = 8;
        //daoP.update(persona1);
        //daoP.delete(idPersona);
        System.out.println(daoP.findAll());

    }
}

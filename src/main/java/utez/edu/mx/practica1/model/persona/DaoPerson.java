package utez.edu.mx.practica1.model.persona;

import utez.edu.mx.practica1.service.ConnectionDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DaoPerson {
    //Variables globales
    private Connection con;
    private PreparedStatement pstm;
    private CallableStatement cstm;
    private Statement stm;
    private ResultSet rs;

    //Método de cierre de conexión
    public void closeConnections(){
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
    String find_personas = "SELECT p.* from persona p;";
    public List<BeanPerson> findAll() throws SQLException {
        List<BeanPerson> personas = new ArrayList<>();
        BeanPerson persona = new BeanPerson();
        try{
            con = ConnectionDB.getConnection();
            pstm = con.prepareCall(find_personas);
            rs = pstm.executeQuery();

            while (rs.next()){
                System.out.println(rs.getInt("idPersona"));
                System.out.println(rs.getString("nombre"));
                System.out.println(rs.getString("aPaterno"));
                persona.setIdPersona(rs.getInt("idPersona"));

                persona.setNombre(rs.getString("nombre"));

                persona.setaPaterno(rs.getString("aPaterno"));
                persona.setaMaterno(rs.getString("aMaterno"));
                persona.setEdad(rs.getInt("edad"));
                persona.setSexo(rs.getString("sexo"));
                persona.setTelefono(rs.getString("telefono"));
                persona.setDireccion(rs.getString("direccion"));
                persona.setFechaNacimiento(rs.getString("fechaNacimiento"));
                persona.setEstadoCivil(rs.getString("estadoCivil"));
                persona.setCorreo(rs.getString("correo"));
                persona.setTrabajo(rs.getBoolean("trabajo"));
                persona.setContrasena(rs.getString("contrasena"));
                persona.setEstado(rs.getBoolean("estado"));
                personas.add(persona);
            }

        }catch (SQLException e){
            System.err.println("DaoPersona (findAll)" + e.getMessage());
        }finally {
            closeConnections();
        }
        return personas;
    }
    // EncontrarPorId
    // TODO
    String find_persona = "SELECT p.* from persona p WHERE idPersona = ?;";

    public BeanPerson findById(int idPersona) throws SQLException{
        BeanPerson persona = new BeanPerson();
        try{
            con = ConnectionDB.getConnection();
            pstm = con.prepareStatement(find_persona);
            pstm.setInt(1,idPersona);
            rs = pstm.executeQuery();

            if (rs.next()){
                persona.setIdPersona(rs.getInt("idPersona"));
                persona.setNombre(rs.getString("nombre"));
                persona.setaPaterno(rs.getString("aPaterno"));
                persona.setaMaterno(rs.getString("aMaterno"));
                persona.setEdad(rs.getInt("edad"));
                persona.setSexo(rs.getString("sexo"));
                persona.setTelefono(rs.getString("telefono"));
                persona.setDireccion(rs.getString("direccion"));
                persona.setFechaNacimiento(rs.getString("fechaNacimiento"));
                persona.setEstadoCivil(rs.getString("estadoCivil"));
                persona.setCorreo(rs.getString("correo"));
                persona.setTrabajo(rs.getBoolean("trabajo"));
                persona.setContrasena(rs.getString("contrasena"));
                persona.setEstado(rs.getBoolean("estado"));
            }
            pstm.execute();
        }catch (SQLException e){
            System.err.println("DaoPersona (findById)" + e.getMessage());
        }finally {
            closeConnections();
        }
        return persona;
    }

    // Create
    // TODO
    String create_persona = "INSERT INTO persona values(null,?,?,?,?,?,?,?,?,?,?,?,?,?)";
    public int create(BeanPerson persona) throws SQLException{
        int flag = 0;
        try{
            con = ConnectionDB.getConnection();
            pstm = con.prepareStatement(create_persona);
            pstm.setString(1,persona.getNombre());
            pstm.setString(2,persona.getaPaterno());
            pstm.setString(3,persona.getaMaterno());
            pstm.setInt(4,persona.getEdad());
            pstm.setString(5,persona.getSexo());
            pstm.setString(6,persona.getTelefono());
            pstm.setString(7,persona.getDireccion());
            pstm.setString(8,persona.getFechaNacimiento());
            pstm.setString(9,persona.getEstadoCivil());
            pstm.setString(10,persona.getCorreo());
            pstm.setBoolean(11,persona.getTrabajo());
            pstm.setString(12,persona.getContrasena());
            pstm.setBoolean(13,persona.getEstado());
            flag = pstm.execute() != false ? 1 : 0;

            pstm.execute();
        }catch (SQLException e){
            System.err.println("DaoPersona (create)" + e.getMessage());
        }finally {
            closeConnections();
        }
        return flag;
    }

    // Update
    // TODO
    String update_persona = "UPDATE persona set nombre = ?," +
            "aPaterno = ?,aMaterno = ?, edad = ?, sexo = ?,telefono = ?," +
            "direccion = ?, fechaNacimiento = ?,estadoCivil = ?,correo = ?,trabajo = ?," +
            "contrasena = ?, estado = ? WHERE idPersona = ?;";
    public int update(BeanPerson persona) throws SQLException{
        int flag = 0;
        try{
            con = ConnectionDB.getConnection();
            pstm = con.prepareStatement(update_persona);
            pstm.setString(1,persona.getNombre());
            pstm.setString(2,persona.getaPaterno());
            pstm.setString(3,persona.getaMaterno());
            pstm.setInt(4,persona.getEdad());
            pstm.setString(5,persona.getSexo());
            pstm.setString(6,persona.getTelefono());
            pstm.setString(7,persona.getDireccion());
            pstm.setString(8,persona.getFechaNacimiento());
            pstm.setString(9,persona.getEstadoCivil());
            pstm.setString(10,persona.getCorreo());
            pstm.setBoolean(11,persona.getTrabajo());
            pstm.setString(12,persona.getContrasena());
            pstm.setBoolean(13,persona.getEstado());
            pstm.setInt(14,persona.getIdPersona());
            flag = pstm.executeUpdate() != 0 ? 1 : 0 ;
            pstm.execute();
        }catch (SQLException e){
            System.err.println("DaoPersona (update)" + e.getMessage());
        }finally {
            closeConnections();
        }
        return flag;
    }
    // Delete
    // TODO
    String delete_persona = "DELETE  FROM persona WHERE idPersona = ?;";
    String temporal_delete_persona = "UPDATE persona set estado = 0 WHERE idPersona = ?;";
    public int delete(int idPersona) throws SQLException{
        int flag = 0;
        try{
            con = ConnectionDB.getConnection();
            pstm = con.prepareStatement(delete_persona);
            pstm.setInt(1,idPersona);
            flag = pstm.executeUpdate() != 0 ? 1 : 0 ;
            pstm.execute();
        }catch (SQLException e){
            System.err.println("DaoPersona (delete)" + e.getMessage());
        }finally {
            closeConnections();
        }
        return flag;
    }
}

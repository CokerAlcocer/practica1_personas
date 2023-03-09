package utez.edu.mx.practica1.model.person;


import org.json.JSONArray;
import org.json.JSONObject;
import utez.edu.mx.practica1.model.transaction.BeanTransaction;
import utez.edu.mx.practica1.service.ConnectionDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DaoPerson {
    //Variables globales
    Connection con;
    PreparedStatement pstm;
    ResultSet rs;


    public int login(String correo,String contrasena){
       int flag = 0;
       JSONObject selPer = new JSONObject();
        try{
            con = ConnectionDB.getConnection();
            pstm = con.prepareStatement("SELECT * FROM persona WHERE correo = ? AND contrasena = ?;");
            pstm.setString(1,correo);
            pstm.setString(2,contrasena);
            rs = pstm.executeQuery();

            if(rs.next()){
                Long idPersona = rs.getLong("id");
                selPer.put("accion","login");
                //QUERY PARA GENERAR LA OPERACIÓN
                pstm = con.prepareStatement("INSERT INTO json_persona(datos,id_persona,tipoaccion) values(JSON_INSERT('{}','$.json',?),?,?);");
                pstm.setString(1,selPer.toString());
                pstm.setLong(2,idPersona);
                pstm.setString(3,"login");
                pstm.executeUpdate();
               flag =1 ;

            }
        }catch (Exception e){
            System.out.println("DP_ERR_000" + e.getMessage());
        }finally {
            ConnectionDB.closeConnections(con,pstm,rs);
        }
        return flag;
    }

    public boolean signUp(BeanPerson person){
        JSONObject newPerson = new JSONObject();
        Boolean flag =false;
        try{
            rs =null;
            con = ConnectionDB.getConnection();
            pstm = con.prepareStatement("INSERT INTO persona(nombre, a_paterno, a_materno, edad, sexo, telefono, direccion, " +
                    "fecha_nacimiento, estado_civil, correo, contrasena,estado) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?)", PreparedStatement.RETURN_GENERATED_KEYS);
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
            pstm.setBoolean(12,person.isEstado());

            //Creación de objeto json para bitacora
            newPerson.put("nombre",person.getNombre());
            newPerson.put("aPaterno",person.getaPaterno());
            newPerson.put("aMaterno",person.getaMaterno());
            newPerson.put("edad",person.getEdad());
            newPerson.put("sexo",person.getSexo());
            newPerson.put("telefono",person.getTelefono());
            newPerson.put("direccion",person.getDireccion());
            newPerson.put("fechaNacimiento",person.getFechaNacimiento());
            newPerson.put("estadoCivil",person.getEstadoCivil());
            newPerson.put("correo",person.getCorreo());
            newPerson.put("contrasena",person.getContrasena());
            newPerson.put("estado",person.isEstado());
            newPerson.put("accion","signup");

            if(pstm.executeUpdate() == 1){
                ResultSet lastInsert = pstm.getGeneratedKeys();
                lastInsert.next();
                Long lastId = lastInsert.getLong(1);
                pstm = con.prepareStatement("SELECT * FROM persona WHERE id = ?");
                pstm.setLong(1, lastId);
                rs = pstm.executeQuery();
                lastInsert.close();

                if(rs.next()){
                    //QUERY PARA GENERAR LA OPERACIÓN
                    pstm = con.prepareStatement("INSERT INTO json_persona(datos,id_persona,tipoaccion) values(JSON_INSERT('{}','$.json',?),?,?);");
                    pstm.setString(1, newPerson.toString());
                    pstm.setLong(2,lastId);
                    pstm.setString(3,"signup");
                    pstm.executeUpdate();
                }
                flag = true;
            }
        }catch (SQLException e){
            System.out.println("DP_ERR_000R" + e.getMessage());
        }finally{
               ConnectionDB.closeConnections(con,pstm);
        }
        return flag;
    }

//Agregar el insert para login y logout para bitacora y hacer vista de registro
    public void logout(Long id){
        JSONObject logout = new JSONObject();
        try{
            logout.put("accion","logout");
            con = ConnectionDB.getConnection();
            pstm = con.prepareStatement("INSERT INTO json_persona(datos,id_persona,tipoaccion) values(JSON_INSERT('{}','$.json',?),?,?);");
            pstm.setString(1,logout.toString());
            pstm.setLong(2,id);
            pstm.setString(3,"logout");
            pstm.executeUpdate();
        }catch (SQLException e){
            System.out.println("DP_ERR_LOGOUT");
        }finally {
            ConnectionDB.closeConnections(con,pstm);
        }

    }

    public BeanPerson getPersonByLogin(String correo,String contrasena){
        BeanPerson personLog = null;
        try{
            rs = null;
            con = ConnectionDB.getConnection();
            pstm = con.prepareStatement("SELECT * FROM persona WHERE correo = ? AND contrasena = ?;");
            pstm.setString(1,correo);
            pstm.setString(2,contrasena);
            rs = pstm.executeQuery();

            if(rs.next()){
                personLog = new BeanPerson(rs.getLong("id"),
                        rs.getString("nombre"),
                        rs.getString("a_paterno"),
                        rs.getString("a_materno"),
                        rs.getInt("edad"),
                        rs.getString("sexo"),
                        rs.getString("telefono"),
                        rs.getString("direccion"),
                        rs.getString("fecha_nacimiento"),
                        rs.getString("estado_civil"),
                        rs.getString("correo"),
                        rs.getString("contrasena"),
                        rs.getBoolean("estado"));

            }
        }catch (Exception e){
            System.out.println("DP_ERR_001" + e.getMessage());
        }finally {
            ConnectionDB.closeConnections(con,pstm,rs);
        }
        return personLog;
    }
    // EncontrarTodos
    public List<BeanPerson> findAll(Long idPersona){
        List<BeanPerson> listPersons = new ArrayList<>();
        BeanPerson person = null;
        JSONObject selPer = new JSONObject();
        try{
            rs = null;
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
                person.setEstado(rs.getBoolean("estado"));
                person.setContrasena(rs.getString("contrasena"));

                listPersons.add(person);
            }


            selPer.put("accion","findall");
            //QUERY PARA GENERAR LA OPERACIÓN
            pstm = con.prepareStatement("INSERT INTO json_persona(datos,id_persona,tipoaccion) values(JSON_INSERT('{}','$.json',?),?,?);");
            pstm.setString(1,selPer.toString());
            pstm.setLong(2,idPersona);
            pstm.setString(3,"findall");
            pstm.executeUpdate();
        }catch (Exception e) {
            System.out.println("DP_ERR_01" + e.getMessage());
        }finally {
            ConnectionDB.closeConnections(con,pstm);
        }
        return listPersons;
    }


    
    // EncontrarPorId
    public BeanPerson findById(Long id,Long idSeP){
        BeanPerson person = null;
        JSONObject selPer = new JSONObject();
        try {
            rs = null;
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
                person.setEstado(rs.getBoolean("estado"));
                person.setContrasena(rs.getString("contrasena"));


                selPer.put("id",rs.getLong("id"));
                selPer.put("nombre",rs.getString("nombre"));
                selPer.put("aPaterno",rs.getString("a_Paterno"));
                selPer.put("aMaterno",rs.getString("a_Materno"));
                selPer.put("correo",rs.getString("correo"));
                selPer.put("accion","findbyid");
                //QUERY PARA GENERAR LA OPERACIÓN
                pstm = con.prepareStatement("INSERT INTO json_persona(datos,id_persona,tipoaccion) values(JSON_INSERT('{}','$.json',?),?,?);");
                pstm.setString(1,selPer.toString());
                pstm.setLong(2,idSeP);
                pstm.setString(3,"findbyid");
                pstm.executeUpdate();

            }

        } catch (Exception e) {
            System.out.println("DP_ERR_002" + e.getMessage());
        }finally {
            ConnectionDB.closeConnections(con,pstm);
        }
        return person;
    }

    // Create
    public boolean create(BeanPerson person,Long idSesiP){
        boolean flag = false;
        JSONObject newPerson = new JSONObject();
        try{
            con = ConnectionDB.getConnection();
            pstm = con.prepareStatement("INSERT INTO persona(nombre, a_paterno, a_materno, edad, sexo, telefono, direccion, " +
                    "fecha_nacimiento, estado_civil, correo, contrasena,estado) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?)", PreparedStatement.RETURN_GENERATED_KEYS);
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
            pstm.setBoolean(12,person.isEstado());

            //Creación de objeto json para bitacora
            newPerson.put("nombre",person.getNombre());
            newPerson.put("aPaterno",person.getaPaterno());
            newPerson.put("aMaterno",person.getaMaterno());
            newPerson.put("edad",person.getEdad());
            newPerson.put("sexo",person.getSexo());
            newPerson.put("telefono",person.getTelefono());
            newPerson.put("direccion",person.getDireccion());
            newPerson.put("fechaNacimiento",person.getFechaNacimiento());
            newPerson.put("estadoCivil",person.getEstadoCivil());
            newPerson.put("correo",person.getCorreo());
            newPerson.put("contrasena",person.getContrasena());
            newPerson.put("estado",person.isEstado());

            if(pstm.executeUpdate() == 1){

                    //QUERY PARA GENERAR LA OPERACIÓN
                     pstm = con.prepareStatement("INSERT INTO json_persona(datos,id_persona,tipoaccion) values(JSON_INSERT('{}','$.json',?),?,?);");
                     pstm.setString(1, newPerson.toString());
                     pstm.setLong(2,idSesiP);
                     pstm.setString(3,"create");
                    flag =  pstm.executeUpdate() == 1;
            }
        }catch (SQLException e){
            System.out.println("DP_ERR_003" + e.getMessage());
        }finally {
            ConnectionDB.closeConnections(con,pstm);
        }
        return flag;
    }

    // Update
    public boolean update(BeanPerson person,Long idSesiP){
        boolean flag = false;
        JSONObject newPerson = new JSONObject();
        try{
            con = ConnectionDB.getConnection();
            pstm = con.prepareStatement("UPDATE persona SET nombre = ?, a_paterno = ?, a_materno = ?, edad = ?, sexo = ?, telefono = ?, " +
                    "direccion = ?, fecha_nacimiento = ?, estado_civil = ?, correo = ?,estado = ? WHERE id = ?");
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
            pstm.setBoolean(11,person.isEstado());
            pstm.setLong(12, person.getId());

            //Creación de objeto json para bitacora
            newPerson.put("id",person.getId());
            newPerson.put("nombre",person.getNombre());
            newPerson.put("aPaterno",person.getaPaterno());
            newPerson.put("aMaterno",person.getaMaterno());
            newPerson.put("edad",person.getEdad());
            newPerson.put("sexo",person.getSexo());
            newPerson.put("telefono",person.getTelefono());
            newPerson.put("direccion",person.getDireccion());
            newPerson.put("fechaNacimiento",person.getFechaNacimiento());
            newPerson.put("estadoCivil",person.getEstadoCivil());
            newPerson.put("correo",person.getCorreo());
            newPerson.put("contrasena",person.getContrasena());
            newPerson.put("estado",person.isEstado());

            if(pstm.executeUpdate() == 1){
                    //QUERY PARA GENERAR LA OPERACIÓN
                    pstm = con.prepareStatement("UPDATE json_persona SET datos = JSON_SET('{}','$.json',?),tipoaccion = ? WHERE id_persona = ?;");
                    pstm.setString(1,newPerson.toString());
                    pstm.setString(2,"update");
                pstm.setLong(3,idSesiP);
                    pstm.executeUpdate();
                flag = true;
            }
        }catch (SQLException e){
            System.out.println("DP_ERR_004" + e.getMessage());
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
    public boolean delete(Long id,Long idSesiP){
        boolean flag = false;
        if(id == idSesiP){
            flag = false;
        }else{
            System.out.println("Eliminando");
            try{
                System.out.println("Apunto de");
                String sql = "SET @idSesiP = ?";
                con = ConnectionDB.getConnection();
                pstm = con.prepareStatement(sql);
                pstm.setLong(1,idSesiP);
                pstm.executeUpdate();
                    pstm = con.prepareCall("DELETE FROM persona WHERE id=?;");
                    pstm.setLong(1,id);
                    if(pstm.executeUpdate() == 1){
                        System.out.println("Se elimino acciones de esa persona");
                      //  pstm.close();
                       pstm = con.prepareCall("DELETE FROM json_persona WHERE id_persona=?;");
                       pstm.setLong(1,id);
                       if(pstm.executeUpdate() == 1){
                           System.out.println("Se elimino a la persona");
                           flag = true;
                       }
                    }

            }catch(SQLException e){
                System.out.println("DP_ERR_005" + e.getMessage());
            }
        }

        return flag;
    }

    public List<BeanTransaction> getTransactions (Long idSesiP) {
        List<BeanTransaction> transactions = new ArrayList<>();
        BeanTransaction opera = null;
        try{
            rs = null;
            con = ConnectionDB.getConnection();
            pstm = con.prepareStatement("SELECT op.id,CONCAT(P.nombre, ' ', p.a_paterno, ' ', p.a_materno ) as nombre,op.fecha as fechamovimiento,t.nombre as tipo,JSON_EXTRACT(op.dato_viejo,'$.json') as dato_viejo,op.dato_nuevo\n" +
                    "FROM operacion op join json_persona jp on op.id_jsonpersona = jp.id\n" +
                    "                    join persona p on jp.id_persona = p.id\n" +
                    "                    join tipo_operacion t on op.id_tipo_operacion = t.id\n" +
                    "                    where p.id = ?\n" +
                    "union all\n" +
                    "SELECT op.id,CONCAT(P.nombre, ' ', p.a_paterno, ' ', p.a_materno ) as nombre,op.fecha as fechamovimiento,t.nombre as tipo,op.dato_viejo as dato_viejo,op.dato_nuevo\n" +
                    "FROM operacion op\n" +
                    "                    join persona p on op.idPersonaD = p.id\n" +
                    "                    join tipo_operacion t on op.id_tipo_operacion = t.id\n" +
                    "                    where idPersonaD = ?;");
            pstm.setLong(1,idSesiP);
            pstm.setLong(2,idSesiP);
            rs = pstm.executeQuery();

            while(rs.next()){
                opera = new BeanTransaction();
                opera.setId(rs.getInt("id"));
                opera.setNombrePersona(rs.getString("nombre"));
                opera.setFechaMovimiento(rs.getString("fechaMovimiento"));
                opera.setTipo(rs.getString("tipo"));
                opera.setDatoViejo(rs.getString("dato_viejo"));
                opera.setDatoNuevo(rs.getString("dato_nuevo"));
                transactions.add(opera);
            }

        }catch (SQLException e){
            System.out.println("DP_ERR_00TS" + e.getMessage());
        }finally {
            ConnectionDB.closeConnections(con,pstm);
        }
        return transactions;
    }

    public BeanTransaction transactionById(Long id){
        BeanTransaction opera= null;
        try {
            rs = null;
            con = ConnectionDB.getConnection();
            pstm = con.prepareStatement("SELECT op.id,CONCAT(P.nombre, ' ', p.a_paterno, ' ', p.a_materno ) as nombre,op.fecha as fechamovimiento,t.nombre as tipo,op.dato_viejo,op.dato_nuevo\n" +
                    "FROM operacion op join json_persona jp on op.id_jsonpersona = jp.id\n" +
                    "                    join persona p on jp.id_persona = p.id\n" +
                    "                    join tipo_operacion t on op.id_tipo_operacion = t.id WHERE op.id = ?\n" +
                    "union all\n" +
                    "SELECT op.id,CONCAT(P.nombre, ' ', p.a_paterno, ' ', p.a_materno ) as nombre,op.fecha as fechamovimiento,t.nombre as tipo,op.dato_viejo,op.dato_nuevo\n" +
                    "FROM operacion op join persona p on op.idPersonaD = p.id\n" +
                    "                    join tipo_operacion t on op.id_tipo_operacion = t.id WHERE op.id = ?;");
            pstm.setLong(1,id);
            pstm.setLong(2,id);
            rs = pstm.executeQuery();
            if(rs.next()){
                opera = new BeanTransaction();

                opera.setId(rs.getInt("id"));
                opera.setNombrePersona(rs.getString("nombre"));
                opera.setFechaMovimiento(rs.getString("fechaMovimiento"));
                opera.setTipo(rs.getString("tipo"));
                opera.setDatoViejo(rs.getString("dato_viejo"));
                opera.setDatoNuevo(rs.getString("dato_nuevo"));
                System.out.println("Transaccion " + opera);
            }

        } catch (Exception e) {
            System.out.println("DP_ERR_00FT" + e.getMessage());
        }finally {
            ConnectionDB.closeConnections(con,pstm);
        }
        return opera;
    }

}


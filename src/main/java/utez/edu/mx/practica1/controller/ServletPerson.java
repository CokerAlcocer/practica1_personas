package utez.edu.mx.practica1.controller;

import com.google.gson.Gson;
import utez.edu.mx.practica1.model.person.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "ServletPersona",value = "/ServletPersona")
public class ServletPerson extends HttpServlet{
    private Map map = new HashMap();
    final private Logger log = LoggerFactory.getLogger(ServletPerson.class);
    DaoPerson daoPersona = new DaoPerson();
    BeanPerson persona = new BeanPerson();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        //permitir la codificacion de caracteres

        request.setCharacterEncoding("UTF-8");
        //request.getRequestDispatcher("/views/index.jsp").forward(request, response);
        //obtener la ruta segun lo requerido
        //request.setAttribute("message", "hello");


        //varibles para manipulacion de persona
        Long idPersona;
        String nombre,aPaterno,aMaterno;
        int edad;
        String sexo;
        String telefono;
        String direccion;
        String fechaNacimiento;
        String estadoCivil;
        String correo;
        boolean trabajo;
        String contrasena;
        boolean estado;

        String action = request.getParameter("action");

        switch (action){
            case "findAll":
                List<BeanPerson> listPersonas = daoPersona.findAll();
               //map.put("ListPersonas",listPersonas);
                //write(response,map);
                //map.clear();
                request.setAttribute("ListPersonas",listPersonas);
                break;
            case "findById":
                idPersona = Long.parseLong(request.getParameter("txtidpersona"));
                persona = daoPersona.findById(idPersona);
                map.put("UniquePerson",persona);
                write(response,map);
                //request.setAttribute("UniquePerson",persona);
                map.clear();
                break;
            case "create":
                //idPersona = Long.parseLong(request.getParameter("txtidpersona"));
                nombre = request.getParameter("txtnombre");
                aPaterno = request.getParameter("txtapaterno");
                aMaterno = request.getParameter("txtamaterno");
                edad = Integer.parseInt(request.getParameter("txtedad"));
                sexo = request.getParameter("txtsexo");
                telefono = request.getParameter("txttelefono");
                direccion= request.getParameter("txtdireccion");
                fechaNacimiento = request.getParameter("txtfechanacimiento");
                estadoCivil = request.getParameter("txtestadocivil");
                correo = request.getParameter("txtcorreo");
                contrasena = request.getParameter("txtcontrasena");
                estado = Boolean.parseBoolean(request.getParameter("txtestado"));

                persona = new BeanPerson (nombre,aPaterno,aMaterno,edad,sexo,telefono,
                        direccion,fechaNacimiento,estadoCivil,correo,contrasena,estado);
                daoPersona.create(persona);
                request.getRequestDispatcher("ServletPersona?action=findAll").forward(request,response);
                break;
            case "update":
                try {
                    idPersona = Long.parseLong(request.getParameter("txtidpersonaU"));
                    nombre = request.getParameter("txtnombreU");
                    aPaterno = request.getParameter("txtapaternoU");
                    aMaterno = request.getParameter("txtamaternoU");
                    edad = Integer.parseInt(request.getParameter("txtedadU"));
                    sexo = request.getParameter("txtsexoU");
                    telefono = request.getParameter("txttelefonoU");
                    direccion= request.getParameter("txtdireccionU");
                    fechaNacimiento = request.getParameter("txtfechanacimientoU");
                    estadoCivil = request.getParameter("txtestadocivilU");
                    correo = request.getParameter("txtcorreoU");
                    contrasena = request.getParameter("txtcontrasenaU");
                    estado = Boolean.parseBoolean(request.getParameter("txtestadoU"));

                    persona = new BeanPerson(idPersona,nombre,aPaterno,aMaterno,edad,sexo,telefono,
                            direccion,fechaNacimiento,estadoCivil,correo,contrasena,estado);

                    daoPersona.update(persona);

                    request.getRequestDispatcher("ServletPersona?action=findAll").forward(request,response);
                }catch (Exception e){
                    System.out.println("Error al actualizar" + e.getMessage());
                }

                break;
            case "delete":
                try {

                    idPersona = Long.parseLong(request.getParameter("txtidpersonaD"));
                    daoPersona.delete(idPersona);

                    request.getRequestDispatcher("ServletPersona?action=findAll").forward(request,response);
                }catch (Exception e){
                    System.out.println("Error al eliminar" + e.getMessage());
                }
                break;


        }

    }
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            processRequest(request,response);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void write(HttpServletResponse response, Map<String, Object> map) throws IOException {
        response.setContentType("application/json");
        response.getWriter().write(new Gson().toJson(map));
    }
}

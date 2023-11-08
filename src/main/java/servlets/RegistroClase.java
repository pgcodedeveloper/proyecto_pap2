/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;
import javax.xml.datatype.XMLGregorianCalendar;
import publicadores.ActividadDeportiva;
import publicadores.Clase;
import publicadores.ControladorPublish;
import publicadores.ControladorPublishService;
import publicadores.SocioYaInscriptoException_Exception;

/**
 *
 * @author PC
 */
public class RegistroClase extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet RegistroClase</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet RegistroClase at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String consulta = request.getParameter("consultar");
                
        if(consulta.equals("instituciones")){
            List<String> inst = obtenerDatos(consulta, "", "");
            String[] list = new String[inst.size()];
            int i = 0;
            for(String s: inst){
                list[i] = s;
                i++;
            }
            
            response.setStatus(200);

            // Convertir el ArrayList a JSON
            ObjectMapper objectMapper = new ObjectMapper();
            String json = objectMapper.writeValueAsString(list);

            // Configurar el tipo de contenido de la respuesta a application/json
            response.setContentType("application/json");

            // Obtener el flujo de salida de la respuesta
            PrintWriter out = response.getWriter();

            // Escribir el JSON en la respuesta
            out.println(json);

            // Cerrar el flujo de salida
            out.close();
            
        }
        else if(consulta.equals("actividades")){
            String institucion = request.getParameter("inst");
            List<String> inst = obtenerDatos(consulta, institucion, "");
            String[] list = new String[inst.size()];
            int i = 0;
            for(String s: inst){
                list[i] = s;
                i++;
            }
            
            response.setStatus(200);

            // Convertir el ArrayList a JSON
            ObjectMapper objectMapper = new ObjectMapper();
            String json = objectMapper.writeValueAsString(list);

            // Configurar el tipo de contenido de la respuesta a application/json
            response.setContentType("application/json");

            // Obtener el flujo de salida de la respuesta
            PrintWriter out = response.getWriter();

            // Escribir el JSON en la respuesta
            out.println(json);

            // Cerrar el flujo de salida
            out.close();
            
        }
        else{
            String actividad = request.getParameter("act");
            List<String> inst = obtenerDatos(consulta, "", actividad);
            String[] list = new String[inst.size()];
            int i = 0;
            for(String s: inst){
                list[i] = s;
                i++;
            }
            
            response.setStatus(200);

            // Convertir el ArrayList a JSON
            ObjectMapper objectMapper = new ObjectMapper();
            String json = objectMapper.writeValueAsString(list);

            // Configurar el tipo de contenido de la respuesta a application/json
            response.setContentType("application/json");

            // Obtener el flujo de salida de la respuesta
            PrintWriter out = response.getWriter();

            // Escribir el JSON en la respuesta
            out.println(json);

            // Cerrar el flujo de salida
            out.close();
            
        }
    }

    public List<String> obtenerDatos(String dato, String inst, String act){
        ControladorPublishService cps = new ControladorPublishService();
        ControladorPublish cp = cps.getControladorPublishPort();
        List<String> list;
        
        if(dato.equals("instituciones")){
            list = cp.obtenerInstituciones().getItem();
        }
        else if(dato.equals("actividades")){
            list = cp.obtenerActividades(inst).getItem();
        }
        else{
            list = cp.obtenerClases(act).getItem();
        }
        return list;
    }
   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        String opcion = request.getParameter("opcion");
        String clase = request.getParameter("clase");
        String act = request.getParameter("actividad");
        String socio = request.getParameter("socio");
        ActividadDeportiva ac = null;
        ac = obtenerActividad(act);
        Clase c = null;
        c = obtenerInfoClase(clase);
        System.out.println(ac.getNombre());
        System.out.println(c.getNombre());
        
        if(opcion.equals("registro")){
            
            try {
                Date f = new Date();
                altaSocioClase(socio, clase, f);
                response.setStatus(200);
                response.setContentType("text/plain");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write("Socio incripto correctamente");
            } catch (SocioYaInscriptoException_Exception e) {
                response.setStatus(400);
                response.setContentType("text/plain");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write("Socio ya esta inscipto en esa clase");
            }
        }
        else{
           
            if( c != null && ac != null){
                request.setAttribute("clase", c);
                request.setAttribute("actividad", ac);
                RequestDispatcher disp = request.getRequestDispatcher("registroClaseFinal.jsp");
                disp.forward(request, response);
            }
        } 
    }
    
    public Clase obtenerInfoClase(String clase){
        ControladorPublishService cps = new ControladorPublishService();
        ControladorPublish cp = cps.getControladorPublishPort();
        return cp.obtenerInfoClase(clase);
    }
    
    public ActividadDeportiva obtenerActividad(String act){
        ControladorPublishService cps = new ControladorPublishService();
        ControladorPublish cp = cps.getControladorPublishPort();
        return cp.obtenerActividad(act);
    }
    
    public void altaSocioClase(String socio,String clase, Date fecha) throws SocioYaInscriptoException_Exception{
        ControladorPublishService cps = new ControladorPublishService();
        ControladorPublish cp = cps.getControladorPublishPort();
        XMLGregorianCalendar f = null;
        f.setDay(fecha.getDay());
        f.setMonth(fecha.getMonth());
        f.setYear(fecha.getYear());
        cp.altaSocioClase(socio, clase, f);
    }
}

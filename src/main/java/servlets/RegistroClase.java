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
import java.rmi.RemoteException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.rpc.ServiceException;
import publicadores.ActividadDeportiva;
import publicadores.Clase;
import publicadores.ControladorPublish;
import publicadores.ControladorPublishService;
import publicadores.ControladorPublishServiceLocator;
import publicadores.SocioYaInscriptoException;

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
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, RemoteException {
        String consulta = request.getParameter("consultar");
                
        if(consulta.equals("instituciones")){
            String[] list;
            try {
                list = obtenerDatos(consulta, "", "");
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
            } catch (ServiceException ex) {
                Logger.getLogger(RegistroClase.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else if(consulta.equals("actividades")){
            String institucion = request.getParameter("inst");
           
            String[] list;
            try {
                list = obtenerDatos(consulta, institucion, "");
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
            } catch (ServiceException ex) {
                Logger.getLogger(RegistroClase.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else{
            String actividad = request.getParameter("act");
            String[] list;
            try {
                list = obtenerDatos(consulta, "", actividad);
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
            } catch (ServiceException ex) {
                Logger.getLogger(RegistroClase.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public String[] obtenerDatos(String dato, String inst, String act) throws RemoteException, ServiceException{
        ControladorPublishService cps = new ControladorPublishServiceLocator();
        ControladorPublish port = cps.getControladorPublishPort();
        String[] list;
        
        if(dato.equals("instituciones")){
            list = port.obtenerInstituciones();
        }
        else if(dato.equals("actividades")){
            list = port.obtenerActividades(inst);
        }
        else{
            list = port.obtenerClases(act);
        }
        return list;
    }
   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException, RemoteException {
        String opcion = request.getParameter("opcion");
        String clase = request.getParameter("clase");
        String act = request.getParameter("actividad");
        String socio = request.getParameter("socio");
        ActividadDeportiva ac = null;
        try {
            ac = obtenerActividad(act);
        } catch (ServiceException ex) {
            Logger.getLogger(RegistroClase.class.getName()).log(Level.SEVERE, null, ex);
        }
        Clase c = null;
        try {
            c = obtenerInfoClase(clase);
        } catch (ServiceException ex) {
            Logger.getLogger(RegistroClase.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(ac.getNombre());
        //System.out.println(c.getNombre());
        
        if(opcion.equals("consultar")){
            
            try {
                Date f = new Date();
                altaSocioClase(socio, clase, f);
                response.setStatus(200);
                response.setContentType("text/plain");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write("Socio incripto correctamente");
            } catch (SocioYaInscriptoException e) {
                response.setStatus(400);
                response.setContentType("text/plain");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write("Socio ya esta inscipto en esa clase");
            } catch (ServiceException ex) {
                Logger.getLogger(RegistroClase.class.getName()).log(Level.SEVERE, null, ex);
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
  
    
    public Clase obtenerInfoClase(String clase) throws RemoteException, ServiceException{
        ControladorPublishService cps = new ControladorPublishServiceLocator();
        ControladorPublish port = cps.getControladorPublishPort();
        return port.obtenerInfoClase(clase);
    }
    
    public publicadores.ActividadDeportiva obtenerActividad(String arg0) throws ServiceException, RemoteException{
        ControladorPublishService cps = new ControladorPublishServiceLocator();
        ControladorPublish port = cps.getControladorPublishPort();
        return port.obtenerActividad(arg0);
    }
    
    public void altaSocioClase(String socio,String clase, Date fecha) throws SocioYaInscriptoException, RemoteException, ServiceException{
        ControladorPublishService cps = new ControladorPublishServiceLocator();
        ControladorPublish port = cps.getControladorPublishPort();
        Calendar c = new GregorianCalendar();
        c.set(fecha.getYear(), fecha.getMonth(), fecha.getDay());
        port.altaSocioClase(socio, socio, c);
    }
    
}

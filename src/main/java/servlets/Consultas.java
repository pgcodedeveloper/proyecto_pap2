/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import interfaces.Fabrica;
import interfaces.IControlador;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.rpc.ServiceException;
import logica.ActividadDeportiva;
import logica.Clase;
import publicadores.ControladorPublish;
import publicadores.ControladorPublishService;
import publicadores.ControladorPublishServiceLocator;


/**
 *
 * @author PC
 */
public class Consultas extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Consultas</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Consultas at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String consultar = request.getParameter("consultar");
       
        if(consultar.equals("registros")){
            try {
                String clase = request.getParameter("clase");
                
                String[] act = obtenerActividadClase(clase);
                response.setStatus(200);
                
                // Convertir el ArrayList a JSON
                ObjectMapper objectMapper = new ObjectMapper();
                String json = objectMapper.writeValueAsString(act);
                
                // Configurar el tipo de contenido de la respuesta a application/json
                response.setContentType("application/json");
                
                // Obtener el flujo de salida de la respuesta
                PrintWriter out = response.getWriter();
                
                // Escribir el JSON en la respuesta
                out.println(json);
                
                
                // Cerrar el flujo de salida
                out.close();
            } catch (ServiceException ex) {
                Logger.getLogger(Consultas.class.getName()).log(Level.SEVERE, null, ex);
            } catch (RemoteException ex) {
                Logger.getLogger(Consultas.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else if(consultar.equals("actividades")){
            try {
                String actividad = request.getParameter("act");
                
                
                List<String[]> datos = new ArrayList<>();
                String[] act = obtenerActividadDatos(actividad);
                String[] clases = obtenerActividadDatosClase(actividad);
                datos.add(act);
                datos.add(clases);
                
                response.setStatus(200);
                
                // Convertir el ArrayList a JSON
                ObjectMapper objectMapper = new ObjectMapper();
                String json = objectMapper.writeValueAsString(datos);
                
                // Configurar el tipo de contenido de la respuesta a application/json
                response.setContentType("application/json");
                
                // Obtener el flujo de salida de la respuesta
                PrintWriter out = response.getWriter();
                
                // Escribir el JSON en la respuesta
                out.println(json);
                
                // Cerrar el flujo de salida
                out.close();
            } catch (ServiceException ex) {
                Logger.getLogger(Consultas.class.getName()).log(Level.SEVERE, null, ex);
            } catch (RemoteException ex) {
                Logger.getLogger(Consultas.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if(consultar.equals("clases")){
            try {
                String actividad = request.getParameter("act");
             
                List<String[]> datos = new ArrayList<>();
                String[] clases = obtenerActividadDatosClase(actividad);
                datos.add(clases);
                
                response.setStatus(200);
                
                // Convertir el ArrayList a JSON
                ObjectMapper objectMapper = new ObjectMapper();
                String json = objectMapper.writeValueAsString(datos);
                
                // Configurar el tipo de contenido de la respuesta a application/json
                response.setContentType("application/json");
                
                // Obtener el flujo de salida de la respuesta
                PrintWriter out = response.getWriter();
                
                // Escribir el JSON en la respuesta
                out.println(json);
                
                // Cerrar el flujo de salida
                out.close();
            } catch (ServiceException ex) {
                Logger.getLogger(Consultas.class.getName()).log(Level.SEVERE, null, ex);
            } catch (RemoteException ex) {
                Logger.getLogger(Consultas.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    public String[] obtenerActividadDatos(String act) throws ServiceException, RemoteException{
        ControladorPublishService cps = new ControladorPublishServiceLocator();
        ControladorPublish port = cps.getControladorPublishPort();
        return port.obtenerActividadDatos(act);
    }
    
    public String[] obtenerActividadDatosClase(String act) throws ServiceException, RemoteException{
        ControladorPublishService cps = new ControladorPublishServiceLocator();
        ControladorPublish port = cps.getControladorPublishPort();
        return port.obtenerActividadClases(act);
    }
    
    public String[] obtenerActividadClase(String clase)throws ServiceException, RemoteException{
        ControladorPublishService cps = new ControladorPublishServiceLocator();
        ControladorPublish port = cps.getControladorPublishPort();
        return port.obtenerActividadClase(clase);
    }
}

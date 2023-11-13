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
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.rpc.ServiceException;
import logica.Clase;
import logica.Registro;
import logica.Socio;
import logica.Usuario;
import org.hibernate.Session;
import publicadores.ControladorPublish;
import publicadores.ControladorPublishService;
import publicadores.ControladorPublishServiceLocator;
import publicadores.DtSocio;
import publicadores.DtUsuario;

/**
 *
 * @author PC
 */
public class EliminarRegistro extends HttpServlet {

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
            out.println("<title>Servlet EliminarRegistro</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet EliminarRegistro at " + request.getContextPath() + "</h1>");
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
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, RemoteException {
        try {
            HttpSession session = request.getSession(false);
            String tipo = request.getParameter("tipo");
            String act = request.getParameter("act");
            DtUsuario socio = ((DtUsuario) session.getAttribute("usuario"));
            
            List<String[]> aRetornar = new ArrayList<>();
            String[] cl = obtenerClasesAct(act);
            String[] reg = obtenerRegistros(socio.getNickname());
            List<String[]> registrosSocio = new ArrayList<>();
            int cont = 1;
            
            if(tipo.equals("consultar")){
                for(String s: reg){
                    String[] res = s.split(",");
                    registrosSocio.add(res);
                }
                
                for(String[] s: registrosSocio){
                    s[0] = s[0].substring(1);
                    String nombre = s[0];
                    int index = s[2].length() - 1;
                    s[2] = s[2].substring(0,index);
                    for(String clase: cl){
                        if(nombre.equals(clase)){
                            aRetornar.add(s);
                        }
                    }
                }
                //aRetornar = obtenerRegistros(socio.getNickname());
                
                // Convertir el ArrayList a JSON
                ObjectMapper objectMapper = new ObjectMapper();
                String json = objectMapper.writeValueAsString(aRetornar);
                
                // Configurar el tipo de contenido de la respuesta a application/json
                response.setContentType("application/json");
                
                // Obtener el flujo de salida de la respuesta
                PrintWriter out = response.getWriter();
                
                // Escribir el JSON en la respuesta
                out.println(json);
                
                // Cerrar el flujo de salida
                out.close();
            }
        } catch (ServiceException ex) {
            Logger.getLogger(EliminarRegistro.class.getName()).log(Level.SEVERE, null, ex);
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        String clase = request.getParameter("clase");
        
        HttpSession session = request.getSession(false);
        DtUsuario u = ((DtUsuario) session.getAttribute("usuario"));

        try {
            eliminarRegistro(clase, u);
            response.setStatus(200);
            response.setContentType("text/plain");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write("Registro eliminado correctamente");
        } catch (Exception e) {
            response.setStatus(500);
            response.setContentType("text/plain");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(e.getMessage());
        }
        
        
    }

    public String[] obtenerRegistros(String socio) throws ServiceException, RemoteException{
        ControladorPublishService cps = new ControladorPublishServiceLocator();
        ControladorPublish port = cps.getControladorPublishPort();
        String [] s = port.obtenerRegistrosSocio(socio);
        return s;
    }
    
    public String[] obtenerClasesAct(String act) throws RemoteException, ServiceException{
        ControladorPublishService cps = new ControladorPublishServiceLocator();
        ControladorPublish port = cps.getControladorPublishPort();
        return port.obtenerClasesAct(act);
    }
    
    public void eliminarRegistro(String clase, DtUsuario s) throws ServiceException, RemoteException{
        ControladorPublishService cps = new ControladorPublishServiceLocator();
        ControladorPublish port = cps.getControladorPublishPort();
        port.eliminarSocioRegistro(clase, s);
    }
    
}

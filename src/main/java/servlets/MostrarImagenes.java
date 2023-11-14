/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlets;

import interfaces.Fabrica;
import interfaces.IControlador;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.rpc.ServiceException;
import logica.ActividadDeportiva;
import logica.Clase;
import logica.Usuario;
import publicadores.ControladorPublish;
import publicadores.ControladorPublishService;
import publicadores.ControladorPublishServiceLocator;
import publicadores.DtUsuario;

@WebServlet("/mostrarImagen")
public class MostrarImagenes extends HttpServlet {

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
            out.println("<title>Servlet Inicio</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Inicio at " + request.getContextPath() + "</h1>");
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
    protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        // Obtiene la parte de la URL que contiene el nombre de la imagen
        // Obtén la ruta de la imagen
        String tipo = request.getParameter("tipo");
        
        if(tipo.equals("usuarios")){
            HttpSession session = request.getSession(false);
            DtUsuario u = ((DtUsuario ) session.getAttribute("usuario"));
            String imagePath = u.getImagen();

            // Verifica si la ruta de la imagen existe
            File archivo = new File(imagePath);
            if (!archivo.exists()) {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                return;
            }

            // Configura el tipo de contenido de la respuesta como imagen
            response.setContentType("image/*"); // Cambia el tipo de contenido según el formato de la imagen

            // Lee la imagen y escribe su contenido en la respuesta
            try (FileInputStream fis = new FileInputStream(archivo)) {
                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = fis.read(buffer)) != -1) {
                    response.getOutputStream().write(buffer, 0, bytesRead);
                }
            }
        }
        else if(tipo.equals("clases")){
            try {
                String clase = request.getParameter("clase");
                String imagen = getImagenClase(clase);
                
                if(imagen != null){
                    // Verifica si la ruta de la imagen existe
                    File archivo = new File(imagen);
                    if (!archivo.exists()) {
                        response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                        return;
                    }
                    
                    // Configura el tipo de contenido de la respuesta como imagen
                    response.setContentType("image/*"); // Cambia el tipo de contenido según el formato de la imagen
                    
                    // Lee la imagen y escribe su contenido en la respuesta
                    try (FileInputStream fis = new FileInputStream(archivo)) {
                        byte[] buffer = new byte[1024];
                        int bytesRead;
                        while ((bytesRead = fis.read(buffer)) != -1) {
                            response.getOutputStream().write(buffer, 0, bytesRead);
                        }
                    }
                }
            } catch (ServiceException ex) {
                Logger.getLogger(MostrarImagenes.class.getName()).log(Level.SEVERE, null, ex);
            } catch (RemoteException ex) {
                Logger.getLogger(MostrarImagenes.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else if(tipo.equals("actividad")){
            try {
                String act = request.getParameter("act");
                String imagen = getImagenAct(act);
                
                if(imagen != null){
                    // Verifica si la ruta de la imagen existe
                    File archivo = new File(imagen);
                    if (!archivo.exists()) {
                        response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                        return;
                    }
                    
                    // Configura el tipo de contenido de la respuesta como imagen
                    response.setContentType("image/*"); // Cambia el tipo de contenido según el formato de la imagen
                    
                    // Lee la imagen y escribe su contenido en la respuesta
                    try (FileInputStream fis = new FileInputStream(archivo)) {
                        byte[] buffer = new byte[1024];
                        int bytesRead;
                        while ((bytesRead = fis.read(buffer)) != -1) {
                            response.getOutputStream().write(buffer, 0, bytesRead);
                        }
                    }
                }
            } catch (ServiceException ex) {
                Logger.getLogger(MostrarImagenes.class.getName()).log(Level.SEVERE, null, ex);
            } catch (RemoteException ex) {
                Logger.getLogger(MostrarImagenes.class.getName()).log(Level.SEVERE, null, ex);
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

    public String getImagenAct(String act) throws ServiceException, RemoteException{
        ControladorPublishService cps = new ControladorPublishServiceLocator();
        ControladorPublish port = cps.getControladorPublishPort();
        return port.obtenerActividad(act)[5];
        
    }
    
     public String getImagenClase(String clase) throws ServiceException, RemoteException{
        ControladorPublishService cps = new ControladorPublishServiceLocator();
        ControladorPublish port = cps.getControladorPublishPort();
        return port.obtenerInfoClase(clase)[5];
        
    }

}

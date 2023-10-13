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
import logica.ActividadDeportiva;
import logica.Clase;
import logica.Usuario;

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
            Usuario u = ((Usuario ) session.getAttribute("usuario"));
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
            String clase = request.getParameter("clase");
            Fabrica f = Fabrica.getInstancia();
            IControlador icon = f.getIControlador();
            Clase c = icon.obtenerInfoClase(clase);
            
            if(clase != null){
                // Verifica si la ruta de la imagen existe
                File archivo = new File(c.getImagen());
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
        }
        else if(tipo.equals("actividad")){
            String act = request.getParameter("act");
            Fabrica f = Fabrica.getInstancia();
            IControlador icon = f.getIControlador();
            ActividadDeportiva a = icon.obtenerActividad(act);
            
            if(a != null){
                // Verifica si la ruta de la imagen existe
                File archivo = new File(a.getImagen());
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

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

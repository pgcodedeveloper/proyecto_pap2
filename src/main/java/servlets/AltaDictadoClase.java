/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlets;

import exceptions.ClaseException;
import interfaces.Fabrica;
import interfaces.IControlador;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import logica.Usuario;

/**
 *
 * @author marti
 */
@MultipartConfig
public class AltaDictadoClase extends HttpServlet {

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
            out.println("<title>Servlet AltaDictadoClase</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AltaDictadoClase at " + request.getContextPath() + "</h1>");
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
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        System.out.println(request.getParameterNames());
        
        
        String inst = request.getParameter("institucion");
        String act = request.getParameter("actividad");
        String clase = request.getParameter("clase");
        String url = request.getParameter("url");
        String imagen = request.getParameter("imagen");
        
        System.out.println(imagen);
        ServletContext context = getServletContext();
        String pathToWebInf = context.getRealPath("/WEB-INF");
        Part img = request.getPart("imagen");
        
        String arch = img.getSubmittedFileName();
        String rutaCompleta = pathToWebInf + File.separator + arch;
        File uploadedFile = new File(rutaCompleta);
        img.write(uploadedFile.getPath());
        
        String fechaString = request.getParameter("fecha");
        
        Date fecha = new Date();
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            fecha = dateFormat.parse(fechaString);
            fecha.setTime(0);
        } catch (ParseException e) {
            // Manejar la excepción si la cadena de fecha no es válida
            e.printStackTrace();
        }
        
        Date fechaActual = new Date();
        HttpSession session = request.getSession(false);
        Usuario u = ((Usuario) session.getAttribute("usuario"));
        Fabrica fb = Fabrica.getInstancia();
        IControlador icon = fb.getIControlador();
        try {
            icon.altaClaseActividad(inst, act, clase, u.getNickName(), url, fecha, fechaActual,rutaCompleta);
            response.setStatus(200);
            response.setContentType("text/plain");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write("Clase creada correctamente");
        } catch (ClaseException e) {
            response.setStatus(400);
            response.setContentType("text/plain");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write("Clase ya existente");
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import exceptions.SocioYaInscriptoException;
import interfaces.Fabrica;
import interfaces.IControlador;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import logica.ActividadDeportiva;
import logica.Clase;
import logica.Controlador;
import logica.ManejadorClase;
import logica.ManejadorInstitucion;

/**
 *
 * @author PC
 */
public class RegistroClase extends HttpServlet {

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
            out.println("<title>Servlet RegistroClase</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet RegistroClase at " + request.getContextPath() + "</h1>");
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
        String consulta = request.getParameter("consultar");
        Fabrica fb = Fabrica.getInstancia();
        IControlador icon = fb.getIControlador();
        String[] list;
        
        if(consulta.equals("instituciones")){
            list = icon.obtenerInstituciones();
            
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
            list = icon.obtenerActividades(institucion);
            
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
            list = icon.obtenerClases(actividad);
            
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
        String opcion = request.getParameter("opcion");
        String clase = request.getParameter("clase");
        String act = request.getParameter("actividad");
        String socio = request.getParameter("socio");
        Fabrica fb = Fabrica.getInstancia();
        IControlador icon = fb.getIControlador();
        ActividadDeportiva ac = icon.obtenerActividad(act);
        Clase c = icon.obtenerInfoClase(clase);

        if(opcion.equals("registro")){
            
            try {
                Date f = new Date();
                System.out.println(f);
                icon.altaSocioClase(socio, clase, f);
                response.setStatus(200);
                response.setContentType("text/plain");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write("Socio incripto correctamente");
            } catch (SocioYaInscriptoException e) {
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

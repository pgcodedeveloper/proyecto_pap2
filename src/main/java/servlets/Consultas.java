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
import java.util.ArrayList;
import java.util.List;
import logica.ActividadDeportiva;
import logica.Clase;


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
        Fabrica fb = Fabrica.getInstancia();
        IControlador icon = fb.getIControlador();
        
        if(consultar.equals("registros")){
            String clase = request.getParameter("clase");
            
            List<String[]> act = icon.obtenerActividadClase(clase);
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
        }
        else if(consultar.equals("actividades")){
            String actividad = request.getParameter("act");
            ActividadDeportiva a = icon.obtenerActividad(actividad);
            
            List<String[]> datos = new ArrayList<>();
            String[] act = new String[5];
            act[0] = a.getDescripcion();
            act[1] = a.getFechaReg().toString();
            act[2] = a.getInst().getNombre();
            act[3] = "" + a.getCosto();
            act[4] = "" + a.getDuracion();
            datos.add(act);
            
            String[] clases = new String[a.getClases().size()];
            int cont = 0;
            for(Clase c: a.getClases()){
                clases[cont] = c.getNombre();
                cont ++;
            }
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

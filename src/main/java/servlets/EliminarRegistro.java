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
import java.util.ArrayList;
import java.util.List;
import logica.Clase;
import logica.Registro;
import logica.Socio;
import logica.Usuario;
import org.hibernate.Session;

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
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        String tipo = request.getParameter("tipo");
        String act = request.getParameter("act");
        Usuario socio = ((Usuario) session.getAttribute("usuario"));
        Fabrica fb = Fabrica.getInstancia();
        IControlador icon = fb.getIControlador();
        
        ArrayList<Registro> list = new ArrayList<>();
        List<String[]> aRetornar = new ArrayList<>();
        
        int cont = 1;

        if(tipo.equals("consultar")){
            list = icon.obtenerRegistrosSocio(socio.getId());
            ArrayList<Clase> clases = icon.obtenerClasesDeActividad(act);
            if(clases != null && !clases.isEmpty()){
                response.setStatus(200);
                if(list != null && !list.isEmpty()){
                    for(Registro r:list){
                        for(Clase c : clases){
                            if(r.getClaseId().equals(c)){
                                String[] datos = new String[3];
                                datos[0] = r.getClaseId().getNombre();
                                datos[1] = r.getFechaReg().toString();
                                datos[2]= r.getSocioId().getNombre() + " " + r.getSocioId().getApellido();
                                aRetornar.add(datos);
                            }
                        }
                    }
                }
            }
            
            
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
        Usuario u = ((Usuario) session.getAttribute("usuario"));
        Fabrica fb = Fabrica.getInstancia();
        IControlador icon = fb.getIControlador();
        
        try {
            
            icon.eliminarSocioRegistro(clase, ((Socio)u));
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

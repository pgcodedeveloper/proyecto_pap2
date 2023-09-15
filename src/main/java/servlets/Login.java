/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlets;

import interfaces.Fabrica;
import interfaces.IControlador;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import logica.ManejadorUsuario;
import logica.Profesor;
import logica.Socio;
import logica.Usuario;
import org.mindrot.jbcrypt.BCrypt;


/**
 *
 * @author PC
 */
public class Login extends HttpServlet {

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
            out.println("<title>Servlet Login</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Login at " + request.getContextPath() + "</h1>");
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        String tipo = request.getParameter("tipo");

        if(tipo.equals("login")){
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            Fabrica f = Fabrica.getInstancia();
            IControlador icon = f.getIControlador();
            ManejadorUsuario mju = ManejadorUsuario.getInstancia();
            Usuario u = mju.buscarUsuarioEmail(email);
            if(u != null){
                if(u.getPassword() != null){
                    if(BCrypt.checkpw(password, u.getPassword())){
                        HttpSession sesion = request.getSession();
                        sesion.setAttribute("usuario", u);
                        if(u instanceof Socio){
                            sesion.setAttribute("tipoUser", "socio");
                        }
                        else if(u instanceof Profesor){
                            sesion.setAttribute("tipoUser", "profesor");
                        }

                        response.setStatus(200);
                    }
                    else{
                        response.setStatus(400);
                        response.setContentType("text/plain");
                        response.setCharacterEncoding("UTF-8");
                        response.getWriter().write("Password incorrecto");
                    }
                }
                else{
                    response.setStatus(400);
                    response.setContentType("text/plain");
                    response.setCharacterEncoding("UTF-8");
                    response.getWriter().write("El usuario no tiene una cuenta aún, cree una primero");
                }
            }
            else{
                response.setStatus(400);
                response.setContentType("text/plain");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write("El usuario no existe");
            }
        }
        else{
            HttpSession sesion = request.getSession(false);
            if(sesion != null){
                sesion.invalidate();
                response.setStatus(200);
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

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlets;

import interfaces.Fabrica;
import interfaces.IControlador;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import logica.Clase;
import logica.Registro;

/**
 *
 * @author PC
 */
public class VerClase extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet VerClase</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet VerClase at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String clase = request.getParameter("clase");
        Fabrica fb = Fabrica.getInstancia();
        IControlador icon = fb.getIControlador();
        Clase c = icon.obtenerInfoClase(clase);
        List<String> list = new ArrayList<>();
        for(Registro r: c.getRegistros()){
            list.add(r.getSocioId().getNickName());
        }
        if(c != null){
            request.setAttribute("clase", c);
            request.setAttribute("registros", list);
            RequestDispatcher disp = request.getRequestDispatcher("verClase.jsp");
            disp.forward(request, response);
        }
    }


}

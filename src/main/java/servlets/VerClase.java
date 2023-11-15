/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlets;

import jakarta.servlet.RequestDispatcher;
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
import publicadores.ControladorPublish;
import publicadores.ControladorPublishService;
import publicadores.ControladorPublishServiceLocator;

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
        try {
            String clase = request.getParameter("clase");
            String[] c = obtenerInfoClase(clase);
            List<String> list = new ArrayList<>();
            String reg = c[6];
            System.out.println(reg);
            String registros[] = null;
            if(reg != "[]"){
                registros = (reg.substring(reg.indexOf("[") + 1, reg.lastIndexOf("]"))).split(",");
            }
            for(String s: registros){
                list.add(s);
            }
            if(c != null){
                request.setAttribute("clase", c);
                request.setAttribute("registros", list);
                RequestDispatcher disp = request.getRequestDispatcher("verClase.jsp");
                disp.forward(request, response);
            }
        } catch (ServiceException ex) {
            Logger.getLogger(VerClase.class.getName()).log(Level.SEVERE, null, ex);
        } catch (RemoteException ex) {
            Logger.getLogger(VerClase.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String[] obtenerInfoClase(String clase) throws ServiceException, RemoteException{
        ControladorPublishService cps = new ControladorPublishServiceLocator();
        ControladorPublish port = cps.getControladorPublishPort();
        return port.obtenerInfoClase(clase);
    }

}

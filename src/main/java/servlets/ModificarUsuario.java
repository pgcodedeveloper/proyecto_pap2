/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlets;

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
import java.rmi.RemoteException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.rpc.ServiceException;
import logica.Usuario;
import publicadores.ControladorPublish;
import publicadores.ControladorPublishService;
import publicadores.ControladorPublishServiceLocator;
import publicadores.DtUsuario;

/**
 *
 * @author PC
 */
@MultipartConfig
public class ModificarUsuario extends HttpServlet {

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
            out.println("<title>Servlet ModificarUsuario</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ModificarUsuario at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        String fechaString = request.getParameter("fecha");
        HttpSession session = request.getSession(false);
        DtUsuario user = ((DtUsuario) session.getAttribute("usuario"));
        System.out.println(fechaString);
        Date fecha = new Date();
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            fecha = dateFormat.parse(fechaString);
        } catch (ParseException e) {
            // Manejar la excepción si la cadena de fecha no es válida
            e.printStackTrace();
        }
        ServletContext context = getServletContext();
        String pathToWebInf = context.getRealPath("/WEB-INF");
        Part img = request.getPart("imagen");

        String arch = img.getSubmittedFileName();
        String rutaCompleta = pathToWebInf + File.separator + arch;
        File uploadedFile = new File(rutaCompleta);
        try {
            if (arch.length() > 0) {
                img.write(uploadedFile.getPath());
                if(request.getParameter("biografia") != null){

                    String biografia = request.getParameter("biografia");
                    String descripcion = request.getParameter("descripcion");
                    String url = request.getParameter("web");
                    actualizarProfe(user.getEmail(), user.getNickname(), nombre, apellido, fecha, rutaCompleta, biografia, descripcion, url);
                    response.setStatus(200);
                    response.setContentType("text/plain");
                    response.setCharacterEncoding("UTF-8");
                    response.getWriter().write("Usuario modificado correctamente");

                }
                actualizarUsuario(user.getEmail(), user.getNickname(), nombre, apellido, fecha, rutaCompleta);
                response.setStatus(200);
                response.setContentType("text/plain");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write("Usuario modificado correctamente");
            }
            else{
                if(request.getParameter("biografia") != null){
                    String biografia = request.getParameter("biografia");
                    String descripcion = request.getParameter("descripcion");
                    String url = request.getParameter("web");
                    actualizarProfe(user.getEmail(), user.getNickname(), nombre, apellido, fecha, user.getImagen(), biografia, descripcion, url);
                    response.setStatus(200);
                    response.setContentType("text/plain");
                    response.setCharacterEncoding("UTF-8");
                    response.getWriter().write("Usuario modificado correctamente");
                }
                actualizarUsuario(user.getEmail(), user.getNickname(), nombre, apellido, fecha, user.getImagen());
                response.setStatus(200);
                response.setContentType("text/plain");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write("Usuario modificado correctamente");
            }
            
            DtUsuario userReload = login(user.getEmail());
            session.setAttribute("usuario", userReload);
            
        } catch (ServiceException ex) {
            Logger.getLogger(ModificarUsuario.class.getName()).log(Level.SEVERE, null, ex);
        } catch (RemoteException ex) {
            Logger.getLogger(ModificarUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void actualizarUsuario(String email, String nick, String nombre, String apellido, Date fNac, String img) throws ServiceException, RemoteException{
        ControladorPublishService cps = new ControladorPublishServiceLocator();
        ControladorPublish port = cps.getControladorPublishPort();
        Calendar c = new GregorianCalendar();
        c.setTime(fNac);
        port.actualizarUsuario(email, nick, nombre, apellido, c, img);
    }
    
    public void actualizarProfe(String email, String nick, String nombre, String apellido, Date fNac, String img, String bio,String desc, String web) throws ServiceException, RemoteException{
        ControladorPublishService cps = new ControladorPublishServiceLocator();
        ControladorPublish port = cps.getControladorPublishPort();
        Calendar c = new GregorianCalendar();
        c.setTime(fNac);
        port.actualizarProfe(email, nick, nombre, apellido, c, img,bio,desc,web);
    }
    
    public DtUsuario login(String email) throws ServiceException, RemoteException{
        ControladorPublishService cps = new ControladorPublishServiceLocator();
        ControladorPublish port = cps.getControladorPublishPort();
        return port.loginUsuario(email);
    }
}


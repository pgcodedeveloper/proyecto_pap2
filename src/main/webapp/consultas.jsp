<%@page import="logica.Usuario" %>
<%@page import="logica.Socio" %>
<%@page import="logica.Clase" %>
<%@page import="logica.Registro" %>
<%@page import="java.util.List"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>EntrenamosUy - Mejor Lugar para entrenar</title>
</head>
<body>
    
    <% 
        String tipoR = request.getParameter("tipo") != null ? request.getParameter("tipo") : "error";
        String tipoUsuario = ((String) session.getAttribute("tipoUser")) != null ? ((String) session.getAttribute("tipoUser")) : null;
        Usuario user = ((Usuario) session.getAttribute("usuario")) != null ? ((Usuario) session.getAttribute("usuario")) : null;
        List<Registro> list = ((Socio)user).getRegistros() != null ? ((Socio)user).getRegistros() : null;
    %>
    <%@include file="header.jsp" %>
    
    <main class="main">
        
        <% if(user != null && tipoR.equals("usuario") && (tipoUsuario.equals("profesor") || tipoUsuario.equals("socio"))){ %>
            <h2 class="heading">Consultar <span>Perfil</span></h2>
            
            <div class="contenedor-datos">
                <div class="contenedor-imagen">
                    <img src="mostrarImagen" class="img-fluid rounded-start" alt="Imagen de perfil">
                </div>
                
                <div class="contenedor-info">
                    <div class="contenedor-info-texto">
                        <h5 class="nombre"> <%= user.getNombre()+ " " + user.getApellido() %> </h5>
                      
                        <div class="contenedor-info-textoextra">
                            <div class="datos">
                                <p class="datos-p"><%= user.getEmail() %></p>
                                <i class="fa-solid fa-at"></i>
                            </div>
                            <div class="datos">
                                <p class="datos-p"><%= user.getNickName() %></p>
                                <i class="fa-solid fa-user"></i>
                            </div>
                            <div class="datos">
                                <p class="datos-p"><%= user.getFecha() %></p>
                                <i class="fa-solid fa-calendar-days"></i>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            
                                
            <h2 class="heading">Registros a <span>Clases</span></h2>
            <% if(list != null && !list.isEmpty()){ %>
                <section class="contenedor-registros">
                    <div class="registros">
                        <h5>Total Registros <span><%= list.size() %></span></h5>
                        <ul class="list-group">
                            <% for(Registro r: list){ %>
                                <li class="list-group-item d-flex justify-content-between align-items-center">
                                    <%= r.getClaseId().getNombre() %>
                                    <span class="badge bg-primary rounded-pill info-extra" data-clase="<%= r.getClaseId().getNombre() %>">Info <i class="fa-solid fa-circle-info"></i></span>
                                </li>
                            <% }%>
                        </ul>
                    </div>

                    <div class="info-registros">
                        <h5>Información de <span id="clase"></span></h5>
                        
                        <p class="datos-registro">Hora de inicio: <span id="hora"></span></p>
                        <p class="datos-registro">Fecha de registro: <span id="fecha"></span></p>
                        <p class="datos-registro">Actividad deportiva: <span id="actividad"></span></p>
                        <div class="datos-extra">
                            <p class="datos-registro">Costo: <span id="costo"></span></p>
                            <p class="datos-registro">Duración: <span id="duracion"></span></p>
                        </div>
                    </div>
                </section>
            <% } else { %>
                <p class="no-registros">No tiene registros aún</p>
            <% } %>
            
        
            
            
            
            
            
        <% } else if(user != null && tipoR.equals("act") && (tipoUsuario.equals("profesor") || tipoUsuario.equals("socio"))){ %>
            <h2 class="heading">Consultar <span>Actividades</span></h2>
        <% } else if( user != null && tipoR.equals("clases") && tipoUsuario.equals("profesor")){ %>
            <h2 class="heading">Consultar <span>Clases</span></h2>
        <% } else { %>
            <%@include file="errorPagina.jsp" %>
        <% } %>
        
    </main>
    
    <%@include file="footer.jsp" %>
    
    <script type="text/javascript">
        document.addEventListener('DOMContentLoaded', async ()=>{
            const btnInfo = document.querySelectorAll(".info-extra");
            
            if(btnInfo !== null){
                btnInfo.forEach(btn => {
                   const clase = btn.getAttribute('data-clase');
                   
                   btn.addEventListener('click', (e) =>{
                       const c = document.querySelector("#clase");
                       if(c.textContent !== e.target.getAttribute('data-clase')){
                           c.textContent = e.target.getAttribute('data-clase');
                       }
                   });
                });
            }
        });
        
        async function obtenerDatos(){
            //Consultar al servlet para traer los datos de la actividad
        }
    </script>
</body>
</html>

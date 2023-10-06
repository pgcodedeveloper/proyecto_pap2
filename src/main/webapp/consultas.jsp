<%@page import="logica.Usuario" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>EntrenamosUy - Mejor Lugar para entrenar</title>
</head>
<body>
    
    <% 
        String tipoR = request.getParameter("tipo");
        String tipoUsuario = ((String) session.getAttribute("tipoUser"));
        Usuario user = ((Usuario) session.getAttribute("usuario"));
    %>
    <%@include file="header.jsp" %>
    
    <main class="main">
        
        <% if(tipoR.equals("usuario") && (tipoUsuario.equals("profesor") || tipoUsuario.equals("socio"))){ %>
            <h2 class="heading">Consultar <span>Perfil</span></h2>
            
            <div class="contenedor-datos">
                <div class="contenedor-imagen">
                    <img src="mostrarImagen" class="img-fluid rounded-start" alt="...">
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
            
        <% } else if(tipoR.equals("act") && (tipoUsuario.equals("profesor") || tipoUsuario.equals("socio"))){ %>
            <h2 class="heading">Consultar <span>Actividades</span></h2>
        <% } else if(tipoR.equals("clases") && tipoUsuario.equals("profesor")){ %>
            <h2 class="heading">Consultar <span>Clases</span></h2>
        <% } else { %>
            <%@include file="errorPagina.jsp" %>
        <% } %>
        
    </main>
    
    <%@include file="footer.jsp" %>
    
    <script type="text/javascript">
        document.addEventListener('DOMContentLoaded', async ()=>{
            
        });
    </script>
</body>
</html>

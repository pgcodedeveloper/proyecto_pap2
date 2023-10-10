<%@page import="logica.Clase" %>
<%@page import="logica.Registro" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html>
<head>
    <title>EntrenamosUy - Clase ${clase.getNombre()}</title>
</head>
<body>
    <%@include file="header.jsp" %>
    
    
    
    <% 
        Clase cl = (Clase) request.getAttribute("clase");
    
    %>
    
    <main class="main">
        <h2 class="heading">Información de la <span>Clase</span></h2>
        
        <div class="contenedor-datos">
            <div class="contenedor-info">
                <div class="contenedor-info-texto">
                    <h5 class="nombre">${clase.getNombre()}</h5>

                    <div class="contenedor-info-textoextra">
                        <div class="datos">
                            <p class="datos-p">${clase.getFechaReg()}</p>
                            <i class="fa-solid fa-calendar-days"></i>
                            
                        </div>
                        <div class="datos">
                            <p class="datos-p">${clase.getHoraInicio()}</p>
                            <i class="fa-solid fa-clock"></i>
                        </div>
                        <div class="datos">
                            <p class="datos-p">${clase.getUrl()}</p>
                            <i class="fa-solid fa-globe"></i>
                        </div>
                    </div>
                </div>
            </div>
                            
            <div class="contenedor-imagen">
                <img src="mostrarImagen" class="img-fluid rounded-start" alt="Imagen de Clase" />
            </div>
        </div>
        
        <h2 class="heading">Socios registrados a la <span>Clase</span></h2>      
        <div class="contenedor-registros-socio">
            <% if(cl.getRegistros() != null && !cl.getRegistros().isEmpty()){ %>
                <div class="registros">
                    <h5>Total Registros <span><%= cl.getRegistros().size() %></span></h5>
                    <ul class="list-group">
                        <% for(Registro r: cl.getRegistros()){ %>
                            <li class="list-group-item d-flex justify-content-between align-items-center">
                                <%= r.getSocioId().getNickName() %>
                            </li>
                        <% }%>
                    </ul>
                </div>
            <% } else { %>
                <div class="alert alert-info" role="alert">
                    <p class="no-registros">No hay socios registrados aún</p>
                </div>
            <% } %>
        </div>
    </main>
    
    <script type="text/javascript">
        document.addEventListener("DOMContentLoaded",async function(){
            
        });    
    </script>
    
    <%@include file="footer.jsp" %>
</body>
</html>


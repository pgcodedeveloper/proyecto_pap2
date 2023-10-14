<%@page import="logica.Usuario" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%  
    boolean errorI = false;

    if(((Usuario) session.getAttribute("usuario")) != null){
        errorI = false;
    }
    else{
        errorI = true;
    }
%>
<!DOCTYPE html>
<html>
<head>
    <title>EntrenamosUy - Mejor Lugar para entrenar</title>
</head>
<body>
    
    <%@include file="header.jsp" %>
    
    <% if(!error){ %>
        <main class="main">
            <h2 class="heading">Bienvenido <span><%= ((Usuario) session.getAttribute("usuario")).getNombre() %></span></h2>
            <p class="heading-texto">Disfrute de un servicio especializado con los mejores profesionales y equipamiento del País</p>
            <div id="carouselExampleAutoplaying" class="carousel slide mx-auto my-5 carusel-index" data-bs-ride="carousel">
                <div class="carousel-inner">
                    <div class="carousel-item active">
                        <img src="img/gimnasio1.jpg" class="d-block w-100 img-fluid" alt="Descripción de la primera imagen">
                    </div>
                    <div class="carousel-item">
                        <img src="img/gimnasio2.jpg" class="d-block w-100 img-fluid" alt="Descripción de la segunda imagen">
                    </div>
                    <div class="carousel-item">
                        <img src="img/gimnasio3.webp" class="d-block w-100 img-fluid" alt="Descripción de la tercera imagen">
                    </div>
                </div>
                <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleAutoplaying" data-bs-slide="prev">
                    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                    <span class="visually-hidden">Previous</span>
                </button>
                <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleAutoplaying" data-bs-slide="next">
                    <span class="carousel-control-next-icon" aria-hidden="true"></span>
                    <span class="visually-hidden">Next</span>
                </button>
            </div>
        </main>
    <% } else { %>
        <%@include file="errorPagina.jsp" %>
    <% } %>
    
    
    <%@include file="footer.jsp" %>
    
    <script type="text/javascript">
        document.addEventListener('DOMContentLoaded', async ()=>{
            
        });
    </script>

</body>
</html>

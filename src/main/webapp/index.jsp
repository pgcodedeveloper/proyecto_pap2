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
            <h2 class="heading">Bienvenido <%= ((Usuario) session.getAttribute("usuario")).getNombre() %></h2>
            
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

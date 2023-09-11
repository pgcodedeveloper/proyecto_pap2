<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>EntrenamosUy - Mejor Lugar para entrenar</title>
</head>
<body>
    <%@include file="header.jsp" %>

    <main class="main">
        <h2 class="heading">Iniciar Sesión</h2>
        <form class="formulario" method="POST">
            <div class="campo">
                <i class="fa-solid fa-envelope"></i>
                <input type="text" value="" name="email" placeholder="Tu Email">
            </div> 
            <div class="campo">
                <i class="fa-solid fa-lock"></i>
                <input type="password" value="" name="password" placeholder="Tu Password">
            </div> 
            
            <div class="contenedor_botones">
                <button type="submit" class="btn btn-primary">
                    Ingresar
                    <i class="fa-solid fa-right-to-bracket"></i>
                </button>
            </div>
            
            <div class="contenedor_acciones">
                <a href="registro.jsp">¿Aún no tienes una cuenta? Crear una</a>
            </div>
        </form>
    </main>
    
    <%@include file="footer.jsp" %>
</body>
</html>
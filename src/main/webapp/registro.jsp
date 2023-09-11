<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>EntrenamosUy - Create una cuenta</title>
</head>
<body>
    <%@include file="header.jsp" %>

    <main class="main">
        <h2 class="heading">Obtén una cuenta gratis</h2>
        <form class="formulario" id="formulario" action="RegistroUsuario" method="post">
            <div class="campo">
                <i class="fa-solid fa-user"></i>
                <input type="text" id="nick" value="" name="nickname" placeholder="Tu nombre de usuario">
            </div> 
            
            <div class="campo">
                <i class="fa-solid fa-envelope"></i>
                <input type="text" id="email" value="" name="email" placeholder="Tu Email">
            </div>
            
            <div class="campo">
                <i class="fa-solid fa-lock"></i>
                <input type="password" id="password" value="" name="password" placeholder="Tu Password">
            </div> 
            
            <div class="campo">
                <i class="fa-solid fa-lock"></i>
                <input type="password" id="passwordR" value="" name="passwordR" placeholder="Repetir Password">
            </div>
            
            <div class="campo">
                <i class="fa-solid fa-image"></i>
                <input class="form-control" id="imagen" name="imagen" type="file" id="formFile">
            </div>
            
            <div class="contenedor_botones">
                <button type="submit" class="btn btn-primary">
                    Registrar
                    <i class="fa-solid fa-floppy-disk"></i>
                </button>
            </div>
            
            <div class="contenedor_acciones">
                <a href="login.jsp">¿Ya tienes una cuenta? Inicia Sesión</a>
            </div>
        </form>
    </main>
    

    <%@include file="footer.jsp" %>
</body>
</html>
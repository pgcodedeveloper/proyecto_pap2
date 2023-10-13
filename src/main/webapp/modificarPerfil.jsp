<%@page import="logica.Usuario" %>
<%@page import="logica.Socio" %>
<%@page import="logica.Profesor" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
    <title>EntrenamosUy - Mejor Lugar para entrenar</title>
</head>
<body>
    
    <% 
        Usuario user = ((Usuario) session.getAttribute("usuario")) != null ? ((Usuario) session.getAttribute("usuario")) : null;
    %>
    
    <%@include file="header.jsp" %>
    <% if(!error){ %>
        <main class="main">
            <h2 class="heading">Modifica tu <span>Perfil</span></h2>

            <form class="formulario" action="AltaDictadoClase" method="POST" enctype="multipart/form-data">
                <fieldset>
                    <legend>Datos personales</legend>
                    <div class="input-group mb-3">
                        <span class="input-group-text"><i class="fa-solid fa-graduation-cap"></i></span>
                        <input type="text" class="form-control" name="nombre" id="nombre" value="<%= user.getNombre() %>" placeholder="Ingrese el nombre de usuario">
                    </div>
                    <div class="input-group mb-3">
                        <span class="input-group-text"><i class="fa-solid fa-graduation-cap"></i></span>
                        <input type="text" class="form-control" name="apellido" id="apellido" value="<%= user.getApellido() %>" placeholder="Ingrese el apellido de usuario">
                    </div>
                    <div class="input-group mb-3">
                        <span class="input-group-text"><i class="fa-solid fa-graduation-cap"></i></span>
                        <input type="email" class="form-control" name="email" id="email" value="<%= user.getEmail() %>" placeholder="Ingrese el email de usuario" disabled>
                    </div>
                    <div class="input-group mb-3">
                        <span class="input-group-text"><i class="fa-solid fa-graduation-cap"></i></span>
                        <input type="text" class="form-control" name="nick" id="nick" value="<%= user.getNickName() %>" placeholder="Ingrese el nickname de usuario" disabled>
                    </div>
                    <div class="input-group mb-3">
                        <span class="input-group-text"><i class="fa-solid fa-graduation-cap"></i></span>
                        <input type="date" class="form-control" name="fecha" id="fecha" value="<%= user.getFecha() %>">
                    </div>
                    <div class="input-group mb-3">
                        <span class="input-group-text"><i class="fa-solid fa-graduation-cap"></i></span>
                        <input type="file" class="form-control" name="imagen" id="imagen" value="<%= user.getImagen() %>" accept="image/*">
                    </div>
                </fieldset>
                
                <% if(user instanceof Profesor) {%>
                    <fieldset>
                        <legend>Datos del profesor</legend>
                        <div class="input-group mb-3">
                            <span class="input-group-text"><i class="fa-solid fa-graduation-cap"></i></span>
                            <textarea class="form-control" name="biografia" id="biografia" placeholder="Ingrese la biografia del profesor"><%= ((Profesor) user).getBiografia() %></textarea>
                        </div>
                        <div class="input-group mb-3">
                            <span class="input-group-text"><i class="fa-solid fa-graduation-cap"></i></span>
                            <input type="text" class="form-control" name="descripcion" id="descripcion" value="<%= ((Profesor) user).getDescripcion() %>" placeholder="Ingrese la descripción">
                        </div>
                        <div class="input-group mb-3">
                            <span class="input-group-text"><i class="fa-solid fa-graduation-cap"></i></span>
                            <input type="text" class="form-control" name="web" id="web" value="<%= ((Profesor) user).getSitioWeb() %>" placeholder="Ingrese la url del profesor">
                        </div>
                    </fieldset>
                <% } %>
                <div class="contenedor_botones">
                    <button type="submit" id="btn" class="btn btn-primary">
                        Registrar Clase
                        <i class="fa-solid fa-circle-right"></i>
                    </button>
                </div>
            </form>
        </main>
    <% } else { %>
    
    <% } %>
    
    <%@include file="footer.jsp" %>
    
    <script type="text/javascript">
        document.addEventListener("DOMContentLoaded",async function(){
            const form = document.querySelector(".formulario");
            
            form.addEventListener('submit',async (e) =>{
                e.preventDefault();
                form.fecha.value = form.fecha.value + "T" + form.hora.value;
                
                const response = await fetch("AltaDictadoClase", {
                    method: "POST",
                    body: new FormData(form), // Envía los datos del formulario
                })
                if (response.ok) {
                    // Maneja la respuesta del servidor aquí (por ejemplo, mostrar un mensaje)
                    const mensaje = await response.text();
                    mostrarMensaje(mensaje,"Registro exitoso","success");
                    e.reset();
                } else {
                    const mensaje = await response.text();
                    mostrarMensaje(mensaje,"Error","error");
                    throw new Error('Error en la solicitud AJAX');
                }
            });
        });  
        function mostrarMensaje(mensaje,titulo,tipo) {
            // Aquí puedes usar SweetAlert2 o cualquier otra biblioteca para mostrar un mensaje
            Swal.fire({ title: titulo, text: mensaje, icon: tipo });
        }
    </script>
</body>
</html> 

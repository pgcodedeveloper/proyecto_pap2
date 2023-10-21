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
    <% if(!error || user != null){ %>
        <main class="main">
            <h2 class="heading">Modifica tu <span>Perfil</span></h2>

            <form class="formulario" action="ModificarUsuario" method="POST" enctype="multipart/form-data">
                <fieldset>
                    <legend>Datos personales</legend>
                    <div class="input-group mb-3">
                        <span class="input-group-text"><i class="fa-solid fa-signature"></i></span>
                        <input type="text" class="form-control" name="nombre" id="nombre" value="<%= user.getNombre() %>" placeholder="Ingrese el nombre de usuario">
                    </div>
                    <div class="input-group mb-3">
                        <span class="input-group-text"><i class="fa-solid fa-signature"></i></span>
                        <input type="text" class="form-control" name="apellido" id="apellido" value="<%= user.getApellido() %>" placeholder="Ingrese el apellido de usuario">
                    </div>
                    <div class="input-group mb-3">
                        <span class="input-group-text"><i class="fa-solid fa-at"></i></span>
                        <input type="email" class="form-control" name="email" id="email" value="<%= user.getEmail() %>" placeholder="Ingrese el email de usuario" disabled>
                    </div>
                    <div class="input-group mb-3">
                        <span class="input-group-text"><i class="fa-solid fa-user"></i></span>
                        <input type="text" class="form-control" name="nick" id="nick" value="<%= user.getNickName() %>" placeholder="Ingrese el nickname de usuario" disabled>
                    </div>
                    <div class="input-group mb-3">
                        <span class="input-group-text"><i class="fa-solid fa-calendar-days"></i></span>
                        <input type="date" class="form-control" name="fecha" id="fecha">
                    </div>
                    <div class="input-group mb-3">
                        <span class="input-group-text"><i class="fa-solid fa-camera"></i></span>
                        <input type="file" class="form-control" name="imagen" id="imagen" value="<%= user.getImagen() %>" accept="image/*">
                    </div>
                    <div class="contenedor-imagen modificar-perfil">
                        <p>Imagen Actual</p>
                        <img src="mostrarImagen?tipo=usuarios" class="img-fluid rounded-start" alt="Imagen de perfil">
                    </div>
                </fieldset>
                
                <% if(user instanceof Profesor) {%>
                    <fieldset>
                        <legend>Datos del profesor</legend>
                        <div class="input-group mb-3">
                            <span class="input-group-text"><i class="fa-brands fa-blogger"></i></span>
                            <textarea class="form-control" name="biografia" id="biografia" placeholder="Ingrese la biografia del profesor"><%= ((Profesor) user).getBiografia() %></textarea>
                        </div>
                        <div class="input-group mb-3">
                            <span class="input-group-text"><i class="fa-solid fa-comment"></i></span>
                            <input type="text" class="form-control" name="descripcion" id="descripcion" value="<%= ((Profesor) user).getDescripcion() %>" placeholder="Ingrese la descripción">
                        </div>
                        <div class="input-group mb-3">
                            <span class="input-group-text"><i class="fa-solid fa-globe"></i></span>
                            <input type="text" class="form-control" name="web" id="web" value="<%= ((Profesor) user).getSitioWeb() %>" placeholder="Ingrese la url del profesor">
                        </div>
                    </fieldset>
                <% } %>
                <div class="contenedor_botones">
                    <button type="submit" id="btn" class="btn btn-primary">
                        Guardar cambios
                        <i class="fa-solid fa-floppy-disk"></i>
                    </button>
                </div>
            </form>
        </main>
        <%@include file="footer.jsp" %>
        <script type="text/javascript">
            document.addEventListener("DOMContentLoaded",async function(){
                const fecha = document.querySelector("#fecha");
                if(fecha !== null){
                    fecha.value = "<%= user.getFecha() %>".includes("-") ? "<%= user.getFecha() %>" : formatearFecha("<%= user.getFecha() %>");
                }
                const form = document.querySelector(".formulario");

                form.addEventListener('submit',async (e) =>{
                    e.preventDefault();
                    
                    <% if(user instanceof Profesor) {%>
                        if(!form.nombre.value || !form.apellido.value || !form.fecha.value || !form.biografia.value || !form.descripcion.value || !form.web.value){
                            mostrarMensaje("Error","Debes ingresar datos en todos los campos","error");
                            return;
                        }
                    <% } else { %>
                        if(!form.nombre.value || !form.apellido.value || !form.fecha.value){
                            mostrarMensaje("Error","Debes ingresar datos en todos los campos","error");
                            return;
                        }
                    <% } %>

                    const response = await fetch("ModificarUsuario", {
                        method: "POST",
                        body: new FormData(form), // Envía los datos del formulario
                    });
                    
                    if (response.ok) {
                        // Maneja la respuesta del servidor aquí (por ejemplo, mostrar un mensaje)
                        const mensaje = await response.text();
                        mostrarMensaje(mensaje,"Exito","success");
                        window.location.href = window.location.origin + "/entrenamosuy/consultas.jsp?tipo=usuario";
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
            
            function formatearFecha(fecha){
                        // Obtén la fecha en formato de texto
                var fechaOriginal = fecha;
                var parts = fechaOriginal.split(" "); // Dividir la cadena por espacios

                // Mapea los nombres de los meses a números
                var meses = {
                  "Jan": 0,
                  "Feb": 1,
                  "Mar": 2,
                  "Apr": 3,
                  "May": 4,
                  "Jun": 5,
                  "Jul": 6,
                  "Aug": 7,
                  "Sep": 8,
                  "Oct": 9,
                  "Nov": 10,
                  "Dec": 11
                };

                // Obtén los componentes de la fecha
                var year = parseInt(parts[5]); // El año está en la posición 5
                var month = meses[parts[1]]; // El mes está en la posición 1
                var day = parseInt(parts[2]);
                var time = parts[3];

                // Analiza la hora y la zona horaria (en este caso, se asume UYT)
                var timeParts = time.split(":");
                var hours = parseInt(timeParts[0]);
                var minutes = parseInt(timeParts[1]);
                var seconds = parseInt(timeParts[2]);

                // Crea la fecha
                var fecha = new Date(year, month, day, hours, minutes, seconds);

                if (!isNaN(fecha)) {
                  // Formatea la fecha en "yyyy-MM-dd"
                  var formattedDate = fecha.toISOString().slice(0, 10);
                  console.log(formattedDate); // Resultado: "2023-10-14"
                  return formattedDate;
                } else {
                  console.log("Fecha no válida");
                }
            }
        </script>
    <% } else { %>
        <%@include file="errorPagina.jsp" %>
    <% } %>
</body>
</html> 

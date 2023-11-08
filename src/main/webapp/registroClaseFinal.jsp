<%@page import="publicadores.Usuario" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>EntrenamosUy - Registrate a una clase</title>
</head>
<body>
    <%@include file="header.jsp" %>

    <main class="main main_registro">
        <h2 class="heading">Resumen del Registro</h2>
        
        <table class="table table-sm">
            <thead>
                <tr>
                  <th scope="col">#</th>
                  <th scope="col">Clase</th>
                  <th scope="col">Actividad</th>
                  <th scope="col">Costo</th>
                  <th scope="col">Duración</th>
                </tr>
            </thead>
            <tbody class="table-group-divider">
                <tr>
                    <th scope="row">1</th>
                    <td><p>${clase.getNombre()}</p></td>
                    <td><p>${actividad.getNombre()}</p></td>
                    <td><p>${actividad.getCosto()}</p></td>
                    <td><p>${actividad.getDuracion()}</p></td>
                </tr>
            </tbody>
        </table>
        
        <h2 class="heading">Más información</h2>
        
        <table class="table table-sm">
            <thead>
                <tr>
                  <th scope="col">#</th>
                  <th scope="col">Actividad</th>
                  <th scope="col">Hora inicio</th>
                  <th scope="col">Institución</th>
                </tr>
            </thead>
            <tbody class="table-group-divider">
                <tr>
                    <th scope="row">1</th>
                    <td><p>${actividad.getNombre()}</p></td>
                    <td><p>${clase.getHoraInicio()}</p></td>
                    <td><p>${actividad.getInst().getNombre()}</p></td>
                </tr>
            </tbody>
        </table>
        
        <div class="contenedor_botones">
            <button type="submit" id="btn" onclick="cancelar()" class="btn btn-danger">
                Cancelar
                <i class="fa-solid fa-file-circle-xmark"></i>
            </button>
            <form id="formulario">
                <input type="hidden" id="nick" value="<%= ((Usuario) session.getAttribute("usuario")).getNickname()%>"/>
                <input type="hidden" id="clase" value="${clase.getNombre().toString()}"/>
                <input type="hidden" id="actividad" value="${actividad.getNombre().toString()}"/>

                <button type="submit" id="btn" class="btn btn-primary">
                    Confirmar
                    <i class="fa-solid fa-file-circle-check"></i>
                </button>
            </form>
        </div>
    </main>
    
    <script type="text/javascript">
        function cancelar(){
            Swal.fire({
                title: "Atención",
                text: "¿Está seguro/a de cancelar esta reservación?",
                icon: "question",
                showConfirmButton: true,
                showCancelButton: true,
                confirmButtonText: "Si, cancelar",
                cancelButtonText: "No"
            }).then((response)=>{
                if(response.isConfirmed){
                    window.location.href = window.location.origin + "/entrenamosuy/registroClase.jsp";
                }
            });
        }
        
        const form = document.querySelector("#formulario");
        form.addEventListener("submit", async (e) =>{
            e.preventDefault();
            
            await registroClase();
        });
        async function registroClase(){
            
            const nick = document.querySelector("#nick").value;
            const clase = document.querySelector("#clase").value;
            const actividad = document.querySelector("#actividad").value;

            // Construye una cadena de consulta con los datos
            const formData = new URLSearchParams();
            formData.append("socio", nick);
            formData.append("clase", clase);
            formData.append("actividad", actividad);
            
            const response = await fetch("RegistroClase?opcion=registro",{
                method: 'POST',
                body: formData.toString(), // Convierte FormData a cadena de consulta
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded' // Establece el tipo de contenido
                }
            });
            if (response.ok) {
                // Maneja la respuesta del servidor aquí (por ejemplo, mostrar un mensaje)
                const mensaje = await response.text();
                Swal.fire({
                    title: "Exito",
                    text: mensaje,
                    icon: "success"
                }).then(() =>{
                    window.location.href = window.location.origin + "/entrenamosuy";
                });
            } else {
                const mensaje = await response.text();
                Swal.fire({
                    title: "Error",
                    text: mensaje,
                    icon: "error"
                }).then(() =>{
                    window.location.href = window.location.origin + "/entrenamosuy/registroClase.jsp";
                });
                throw new Error('Error en la solicitud AJAX');
            }
            
        }
    </script>
    <%@include file="footer.jsp" %>
</body>
</html>

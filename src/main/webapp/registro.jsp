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
        <form class="formulario" id="formulario">
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
    
    <script type="text/javascript">
        document.getElementById("formulario").addEventListener('submit',async function(e){
            e.preventDefault();
            
            const nick = document.querySelector("#nick").value;
            const email = document.querySelector("#email").value;
            const password = document.querySelector("#password").value;
            const passwordR = document.querySelector("#passwordR").value;
            const imagen = document.querySelector("#imagen");
            
            if(!nick || !email || !password || !passwordR){
                mostrarMensaje("Todos los campos son obligatorios","Error","error");
                return;
            }
            // Construye una cadena de consulta con los datos
            const formData = new URLSearchParams();
            formData.append("nickname", nick);
            formData.append("email", email);
            formData.append("password", password);
            formData.append("passwordR", passwordR);
            formData.append("imagen", imagen);
            try{
                // Realiza la solicitud AJAX al Servlet usando async/await
                const response = await fetch("RegistroUsuario", {
                    method: 'POST',
                    body: formData.toString(), // Convierte FormData a cadena de consulta
                    headers: {
                        'Content-Type': 'application/x-www-form-urlencoded' // Establece el tipo de contenido
                    }
                });
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
            } catch (error) {
                // Maneja cualquier error que ocurra durante la solicitud
                console.error('Error:', error);
            }
        });

        function mostrarMensaje(mensaje,titulo,tipo) {
            // Aquí puedes usar SweetAlert2 o cualquier otra biblioteca para mostrar un mensaje
            Swal.fire({ title: titulo, text: mensaje, icon: tipo });
        }
    </script>
</body>
</html>
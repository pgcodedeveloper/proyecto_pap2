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
        <form class="formulario" id="formulario">
            <div class="campo">
                <i class="fa-solid fa-envelope"></i>
                <input type="text" id="email" value="" name="email" placeholder="Tu Email">
            </div> 
            <div class="campo">
                <i class="fa-solid fa-lock"></i>
                <input type="password" id="password" value="" name="password" placeholder="Tu Password">
            </div> 
            
            <div class="contenedor_botones">
                <button type="submit" id="btn" class="btn btn-primary">
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
    
    <script type="text/javascript">
        document.getElementById("formulario").addEventListener('submit',async function(e){
            e.preventDefault();
            const cargando = false;
            const email = document.querySelector("#email").value;
            const password = document.querySelector("#password").value;
            
            if(!email || !password){
                mostrarMensaje("Todos los campos son obligatorios","Error","error");
                return;
            }
            // Construye una cadena de consulta con los datos
            const formData = new URLSearchParams();
            formData.append("tipo","login");
            formData.append("email", email);
            formData.append("password", password);
            try{
                // Realiza la solicitud AJAX al Servlet usando async/await
                ocultarBoton(true);
                const response = await fetch("Login", {
                    method: 'POST',
                    body: formData.toString(), // Convierte FormData a cadena de consulta
                    headers: {
                        'Content-Type': 'application/x-www-form-urlencoded' // Establece el tipo de contenido
                    }
                });
                if (response.ok) {
                    // Maneja la respuesta del servidor aquí (por ejemplo, mostrar un mensaje)
                    //const mensaje = await response.text();
                    //mostrarMensaje(mensaje,"Registro exitoso","success");
                    ocultarBoton(false);
                    document.getElementById("formulario").reset();
                    window.location.href = window.location.origin + "/entrenamosuy/";
                } else {
                    const mensaje = await response.text();
                    mostrarMensaje(mensaje,"Error","error");
                    ocultarBoton(false);
                    throw new Error('Error en la solicitud AJAX');
                }
            } catch (error) {
                // Maneja cualquier error que ocurra durante la solicitud
                console.error('Error:', error);
            }
        });

        function mostrarMensaje(mensaje,titulo,tipo) {
            Swal.fire({ title: titulo, text: mensaje, icon: tipo });
        }
        
        function ocultarBoton(flag){
            const btn = document.getElementById("btn");
            
            if(flag){
                btn.style.visibility = 'hidden';
            }
            else{
                btn.style.visibility = 'visible';
            }
        }
    </script>
</body>
</html>
<%@page import="logica.Usuario" %>
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
            <h2 class="heading">Registro a Clase</h2>

            <form class="formulario">
                <fieldset>
                    <legend>Institución Deportiva</legend>
                    <div class="input-group mb-3">
                        <span class="input-group-text"><i class="fa-solid fa-dumbbell"></i></span>
                        <select class="form-select" id="instituciones" aria-label="Default select example">
                            <option selected disabled>Seleccione una opción</option>
                        </select>
                    </div>
                </fieldset>

                <fieldset>
                    <legend>Actividades Deportivas</legend>
                    <div class="input-group mb-3">
                        <span class="input-group-text"><i class="fa-solid fa-dumbbell"></i></span>
                        <select class="form-select" id="actividades" name="actividad" aria-label="Default select example" disabled></select>
                    </div>
                </fieldset>
                
                <fieldset>
                    <legend>Datos de la clase</legend>
                    <div class="input-group mb-3">
                        <span class="input-group-text"><i class="fa-solid fa-graduation-cap"></i></span>
                        <input type="text" class="form-control" id="clase" placeholder="Ingrese el nombre de la clase">
                    </div>
                     <div class="input-group mb-3">
                        <span class="input-group-text"><i class="fa-solid fa-calendar-days"></i></span>
                        <input type="date" class="form-control" name="fecha" id="fecha"/>   
                    </div>
                     <div class="input-group mb-3">
                        <span class="input-group-text"><i class="fa-solid fa-clock"></i></span>
                        <input type="time" class="form-control" name="hora" id="hora"/>   
                    </div>
                     <div class="input-group mb-3">
                        <span class="input-group-text"><i class="fa-solid fa-globe"></i></span>
                        <input type="url" class="form-control" name="url" id="url" placeholder="Ingrese la url de la clase"/>   
                    </div>
                     <div class="input-group mb-3">
                        <span class="input-group-text"><i class="fa-solid fa-camera"></i></span>
                        <input type="file" class="form-control" name="imagen" id="imagen" accept="image/*"/>   
                    </div>
                </fieldset>

                <div class="contenedor_botones">
                    <button type="submit" id="btn" class="btn btn-primary">
                        Registrar Clase
                        <i class="fa-solid fa-circle-right"></i>
                    </button>
                </div>
            </form>
        </main>
    
    
    <%@include file="footer.jsp" %>
    
    <script type="text/javascript">
        document.addEventListener('DOMContentLoaded', async ()=>{
            
        });
    </script>
</body>
</html> <script type="text/javascript">
        document.addEventListener("DOMContentLoaded",async function(){
            const select = document.querySelector("#instituciones");
            const selectA = document.querySelector("#actividades");
            const selectC = document.querySelector("#clases");
            if(select !== null){
                await fetch("RegistroClase?consultar=instituciones")
                    .then(response => response.json())
                    .then(data =>{
                        console.log(data);
                        if(data === "No hay instituciones"){
                            const option = document.createElement("option");
                            option.selected = true;
                            option.disabled = true;
                            option.textContent = data;
                            select.appendChild(option);
                        }
                        else{
                            data.forEach(item => {
                                const option = document.createElement("option");
                                option.value = item; // Valor del option
                                option.textContent = item; // Texto visible en el option
                                select.appendChild(option); // Agrega la opción al select
                            });
                        }
                        
                    })
                    .catch(error =>{
                        console.error(error);
                    });
                    
                if(selectA !== null){
                    select.addEventListener("change", async function() {
                        selectA.disabled = false;
                        selectA.innerHTML = '';
                        const valor = select.value;
                        const url = 'RegistroClase?consultar=actividades&inst=' + valor;
                        await fetch(url)
                        .then(response => response.json())
                        .then(data =>{
                            if(data === "No hay actividades"){
                                const option = document.createElement("option");
                                option.selected = true;
                                option.disabled = true;
                                option.textContent = data;
                                selectA.appendChild(option);
                              }
                              else{
                                const option = document.createElement("option");
                                option.textContent = "Seleccione una opción"; // Texto visible en el option
                                option.selected = true;
                                option.disabled = true;
                                selectA.appendChild(option);
                                data.forEach(item => {
                                    const option = document.createElement("option");
                                    option.value = item; // Valor del option
                                    option.textContent = item; // Texto visible en el option
                                    selectA.appendChild(option); // Agrega la opción al select
                                });
                            }
                        })
                        .catch(error =>{
                            console.error(error);
                        });
                    });                        
                }    
            }
            const form = document.querySelector(".formulario");
            form.addEventListener("submit", async(e) => {
                e.preventDefault();
                const instituciones = document.querySelector("#instituciones").value;
                const actividades = document.querySelector("#actividades").value;
                const clase = document.querySelector("#clase").value;
                const url = document.querySelector("#url").value;
                console.log(document.querySelector("#fecha"))
                const fecha = document.querySelector("#fecha").value;

                if(!instituciones || !actividades || !clase || !url || !fecha){
                    alert("Todos los campos son obligatorios");
                    return;
                }
                // Construye una cadena de consulta con los datos
                const formData = new URLSearchParams();
                formData.append("institucion", instituciones);
                formData.append("actividad", actividades);
                formData.append("clase", clase);
                formData.append("url", url);
                formData.append("fecha", fecha);
                try{
                    // Realiza la solicitud AJAX al Servlet usando async/await
                    const response = await fetch("AltaDictadoClase", {
                        method: 'POST',
                        body: formData.toString(), // Convierte FormData a cadena de consulta
                        headers: {
                            'Content-Type': 'application/x-www-form-urlencoded' // Establece el tipo de contenido
                        }
                    });
                    if (response.ok) {
                        // Maneja la respuesta del servidor aquí (por ejemplo, mostrar un mensaje)
                        const mensaje = await response.text();
                        alert("Registro exitoso");
                        e.reset();
                    } else {
                        const mensaje = await response.text();
                        alert("error");
                        throw new Error('Error en la solicitud AJAX');
                    }
                } catch (error) {
                    // Maneja cualquier error que ocurra durante la solicitud
                    console.error('Error:', error);
                }
            })
        });  
        </script>
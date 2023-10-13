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
    
    <% if(!error){ %>
        <main class="main">
            <h2 class="heading">Elimine sus <span>Registros</span></h2>

            <form action="" class="formulario">
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


            </form>

            <div class="contenedor_registros">
                <h2 class="heading">Registros del <span>Socio</span> </h2>
                <table class="table table-hover">
                    <thead class="bg-dark">
                        <tr>
                            <th scope="col">Clase</th>
                            <th scope="col">Fecha</th>
                            <th scope="col">Socio</th>
                            <th scope="col">Acciones</th>
                        </tr>
                    </thead>
                    <tbody id="tabla_body">

                    </tbody>
                </table>
            </div>

        </main>
    <% } else {%>
        <%@include file="errorPagina.jsp" %>
    <% } %>
    
    <%@include file="footer.jsp" %>
    
    <script type="text/javascript">
        document.addEventListener('DOMContentLoaded', async ()=>{
            
            const select = document.querySelector("#instituciones");
            const selectA = document.querySelector("#actividades");
            const tbody = document.querySelector("#tabla_body");
            if(select !== null){
                await fetch("RegistroClase?consultar=instituciones")
                    .then(response => response.json())
                    .then(data =>{
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
                    
                    selectA.addEventListener("change", async function(){
                        
                        const url = "EliminarRegistro?tipo=consultar&act=" + selectA.value;
                       
                        await fetch(url)
                        .then(response => response.json())
                        .then(data =>{
                            if(tbody !== null){
                                tbody.innerHTML = "";
                                data.forEach(d => {
                                    let dato = "<tr>";
                                    dato += "<td>"+ d[0] + "</td>";
                                    dato += "<td>"+ d[1] + "</td>";
                                    dato += "<td>"+ d[2] + "</td>";
                                    const cl = d[0];
                                    dato +="<td><div class='cont_eliminar'><button class='btn_elim' id='boton_eliminar' data-clase='"+ cl +"'><i class='fa-solid fa-trash'></i></button></div></td>";
                                    tbody.innerHTML += dato;
                                });
                                const btnElim = document.querySelectorAll("#boton_eliminar");
            
                                if(btnElim !== null){
                                    btnElim.forEach(btn => {
                                        btn.addEventListener('click', async(e)=>{
                                            await swal.fire({
                                                title: "¿Esta seguro de eliminar el registro?",
                                                text: "No se podrá revertir la acción",
                                                confirmButtonText: "Si, eliminar",
                                                cancelButtonText: "No",
                                                showConfirmButton: true,
                                                showCancelButton: true,
                                                icon: "question"
                                            }).then(async (response) =>{
                                                if(response.isConfirmed){
                                                    const clase = btn.getAttribute('data-clase');
                                                    const url="EliminarRegistro";
                                                    const formData = new URLSearchParams();
                                                    formData.append("clase", clase);
                                                    try{
                                                        // Realiza la solicitud AJAX al Servlet usando async/await
                                                        const response = await fetch(url, {
                                                            method: 'POST',
                                                            body: formData.toString(), // Convierte FormData a cadena de consulta
                                                            headers: {
                                                                'Content-Type': 'application/x-www-form-urlencoded' // Establece el tipo de contenido
                                                            }
                                                        });
                                                        if (response.ok) {
                                                            // Maneja la respuesta del servidor aquí (por ejemplo, mostrar un mensaje)
                                                            const mensaje = await response.text();
                                                            swal.fire({
                                                                title: "Eliminado",
                                                                text: mensaje,
                                                                icon: "success"
                                                            }).then(() =>{
                                                                window.location.reload();
                                                            });
                                                        } else {
                                                            const mensaje = await response.text();
                                                            swal.fire({
                                                                title: "Error",
                                                                text: "Ocurrio un error al intentar eliminar el registro, intente nuevamente",
                                                                icon: "Error"
                                                            });
                                                            throw new Error('Error en la solicitud AJAX');
                                                        }
                                                    } catch (error) {
                                                        // Maneja cualquier error que ocurra durante la solicitud
                                                        console.error('Error:', error);
                                                    }
                                                }
                                            });
                                         });
                                    });

                                }
                            }
                        })
                        .catch(error =>{
                            console.error(error);
                        });
                        
                        
                    });
                }
            }
            
        });
    </script>
</body>
</html>

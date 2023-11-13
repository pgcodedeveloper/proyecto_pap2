<%@page import="publicadores.DtUsuario" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>EntrenamosUy - Registrate a una clase</title>
</head>
<body>
    <%@include file="header.jsp" %>
    
    <% if(!error) {%>
    <main class="main">
        <h2 class="heading">Registro a Clase</h2>
        
        <form action="RegistroClase?opcion=consultar" method="post" class="formulario">
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
                <legend>Clases disponibles</legend>
                <div class="input-group mb-3">
                    <span class="input-group-text"><i class="fa-solid fa-book"></i></span>
                    <select class="form-select" id="clases" name="clase" aria-label="Default select example" disabled></select>
                </div>
            </fieldset>
            
            <div class="contenedor_botones">
                <button type="submit" id="btn" class="btn btn-primary" disabled>
                    Siguiente
                    <i class="fa-solid fa-circle-right"></i>
                </button>
            </div>
        </form>
    </main>
    
    <script type="text/javascript">
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
                        selectC.innerHTML = '';
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
                    
                    if(selectC !== null){
                        selectA.addEventListener("change", async function() {
                            selectC.disabled = false;
                            selectC.innerHTML = '';
                            const valor = selectA.value;
                            const url = 'RegistroClase?consultar=clases&act=' + valor;
                            await fetch(url)
                            .then(response => response.json())
                            .then(data =>{
                                if(data === "No hay clases"){
                                    const option = document.createElement("option");
                                    option.selected = true;
                                    option.disabled = true;
                                    option.textContent = data;
                                    selectC.appendChild(option);
                                }
                                else{
                                    const option = document.createElement("option");
                                    option.textContent = "Seleccione una opción"; // Texto visible en el option
                                    option.selected = true;
                                    option.disabled = true;
                                    selectC.appendChild(option);
                                    data.forEach(item => {
                                        const option = document.createElement("option");
                                        option.value = item; // Valor del option
                                        option.textContent = item; // Texto visible en el option
                                        selectC.appendChild(option); // Agrega la opción al select
                                    });
                                }
                            })
                            .catch(error =>{
                                console.error(error);
                            });
                        });
                        
                        const btnSig = document.querySelector("#btn");
                        selectC.addEventListener("change", async function() {
                           btnSig.disabled = false;
                        });
                    }
                }
                
            }
        });    
    </script>
    
    <% } else {%>
        <%@include file="errorPagina.jsp" %>
    <% } %>
    <%@include file="footer.jsp" %>
</body>
</html>

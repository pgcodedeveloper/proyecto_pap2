<%@page import="logica.Clase" %>
<%@page import="logica.Registro" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html>
<head>
    <title>EntrenamosUy - Clase ${clase.getNombre()}</title>
</head>
<body>
    <%@include file="header.jsp" %>
    
    
    
    <% 
        Clase cl = (Clase) request.getAttribute("clase");
    
    %>
    
    <main class="main">
        <h2 class="heading">Información de la <span>Clase</span></h2>
        
        <div class="contenedor-datos">
            <div class="contenedor-info">
                <div class="contenedor-info-texto">
                    <h5 class="nombre">${clase.getNombre()}</h5>

                    <div class="contenedor-info-textoextra">
                        <div class="datos">
                            <p class="datos-p" id="fecha"></p>
                            <i class="fa-solid fa-calendar-days"></i>
                            
                        </div>
                        <div class="datos">
                            <p class="datos-p">${clase.getHoraInicio()}</p>
                            <i class="fa-solid fa-clock"></i>
                        </div>
                        <div class="datos">
                            <p class="datos-p">${clase.getUrl()}</p>
                            <i class="fa-solid fa-globe"></i>
                        </div>
                    </div>
                </div>
            </div>
                            
            <div class="contenedor-imagen">
                <img src="mostrarImagen?tipo=clases&clase=<%= cl.getNombre() %>" class="img-fluid rounded-start" alt="Imagen de Clase" />
            </div>
        </div>
        
        <h2 class="heading">Socios registrados a la <span>Clase</span></h2>      
        <div class="contenedor-registros-socio">
            <% if(cl.getRegistros() != null && !cl.getRegistros().isEmpty()){ %>
                <div class="registros">
                    <h5>Total Registros <span><%= cl.getRegistros().size() %></span></h5>
                    <ul class="list-group">
                        <% for(Registro r: cl.getRegistros()){ %>
                            <li class="list-group-item d-flex justify-content-between align-items-center">
                                <%= r.getSocioId().getNickName() %>
                            </li>
                        <% }%>
                    </ul>
                </div>
            <% } else { %>
                <div class="alert alert-info" role="alert">
                    <p class="no-registros">No hay socios registrados aún</p>
                </div>
            <% } %>
        </div>
    </main>
    
    <script type="text/javascript">
        document.addEventListener("DOMContentLoaded",async function(){
            const fecha = document.querySelector("#fecha");
            if(fecha !== null){
                fecha.textContent = "<%= cl.getFechaReg() %>".includes("-") ? "<%= cl.getFechaReg() %>" : formatearFecha("<%= cl.getFechaReg() %>");
            }
        });
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
    
    <%@include file="footer.jsp" %>
</body>
</html>


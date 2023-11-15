<%@page import="publicadores.ControladorPublishServiceLocator"%>
<%@page import="publicadores.ControladorPublish"%>
<%@page import="publicadores.ControladorPublishService"%>
<%@page import="publicadores.DtSocio"%>
<%@page import="publicadores.DtProfesor"%>
<%@page import="publicadores.Usuario" %>
<%@page import="publicadores.Socio" %>
<%@page import="publicadores.Profesor" %>
<%@page import="publicadores.Clase" %>
<%@page import="publicadores.Registro" %>
<%@page import="java.util.List"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>EntrenamosUy - Mejor Lugar para entrenar</title>
</head>
<body>
    
    <% 
        ControladorPublishService cps = new ControladorPublishServiceLocator();
        ControladorPublish port = cps.getControladorPublishPort();
        String tipoR = request.getParameter("tipo") != null ? request.getParameter("tipo") : "error";
        String tipoUsuario = ((String) session.getAttribute("tipoUser")) != null ? ((String) session.getAttribute("tipoUser")) : null;
        DtUsuario user = ((DtUsuario) session.getAttribute("usuario")) != null ? ((DtUsuario) session.getAttribute("usuario")) : null;
        String[] list = null;
        String[] listC = null;
        boolean profe = false;
        boolean socio = false;
        if (user instanceof DtSocio){
            socio = true;
            list = ((DtSocio)user).getRegistros();
        }
        else if(user instanceof DtProfesor){
            profe = true;
            listC = port.obtenerClasesProfesor(user.getEmail());
        }
    %>
    <%@include file="header.jsp" %>
    
    <% if(!error){ %>
    <main class="main">
        
        <% if(user != null && tipoR.equals("usuario") && (tipoUsuario.equals("profesor") || tipoUsuario.equals("socio"))){ %>
            <h2 class="heading">Consultar <span>Perfil</span></h2>
            
            <div class="contenedor-datos">
                <div class="contenedor-imagen">
                    <img src="mostrarImagen?tipo=usuarios" class="img-fluid rounded-start" alt="Imagen de perfil">
                </div>
                
                <div class="contenedor-info">
                    <div class="contenedor-info-texto">
                        <h5 class="nombre"> <%= user.getNombre()+ " " + user.getApellido() %> </h5>
                      
                        <div class="contenedor-info-textoextra">
                            <div class="datos">
                                <p class="datos-p"><%= user.getEmail() %></p>
                                <i class="fa-solid fa-at"></i>
                            </div>
                            <div class="datos">
                                <p class="datos-p"><%= user.getNickname()%></p>
                                <i class="fa-solid fa-user"></i>
                            </div>
                            <div class="datos">
                                <p class="datos-p" id="fechaN"></p>
                                <i class="fa-solid fa-calendar-days"></i>
                            </div>
                            <% if(profe) {%>
                                <div class="datos">
                                    <p class="datos-p"><%= ((DtProfesor)user).getBiografia() %></p>
                                    <i class="fa-brands fa-blogger"></i>
                                </div>
                                <div class="datos">
                                    <p class="datos-p"><%= ((DtProfesor)user).getDescripcion() %></p>
                                    <i class="fa-solid fa-comment"></i>
                                </div>
                                <div class="datos">
                                    <p class="datos-p"><%= ((DtProfesor)user).getSitioWeb() %></p>
                                    <i class="fa-solid fa-globe"></i>
                                </div>
                                <div class="datos">
                                    <% String nom = ((DtProfesor)user).getInst().split(",,")[0]; %>
                                    <p class="datos-p"><%= nom %></p>
                                    <i class="fa-solid fa-school"></i>
                                </div>
                            <% } %>  
                        </div>
                    </div>
                </div>
            </div>
            
            <% if(socio) {%>                
                <h2 class="heading">Registros a <span>Clases</span></h2>
                <% if(list != null){ %>
                    <section class="contenedor-registros">
                        <div class="registros">
                            <h5>Total Registros <span><%= list.length %></span></h5>
                            <ul class="list-group">
                                <% for(String r: list){ %>
                                <% String[] reg = r.split(","); %>
                                    <li class="list-group-item d-flex justify-content-between align-items-center">
                                        <%= reg[1] %>
                                        <span class="badge bg-primary rounded-pill info-extra" data-clase="<%= reg[1] %>">Info <i class="fa-solid fa-circle-info" data-clase="<%= reg[1] %>"></i></span>
                                    </li>
                                <% }%>
                            </ul>
                        </div>

                        <div class="info-registros">
                            <h5>Información de <span id="clase"></span></h5>

                            <div>
                                <p class="datos-registro">Hora de inicio: <span id="hora"></span></p> 
                                <i class="fa-solid fa-clock"></i>
                            </div>
                            <div> 
                                <p class="datos-registro">Fecha de registro: <span id="fecha"></span></p> 
                                <i class="fa-solid fa-calendar-days"></i> 
                            </div>
                            <div>
                                <p class="datos-registro">Actividad deportiva: <span id="actividad"></span></p>
                                <i class="fa-solid fa-futbol"></i>
                            </div>
                            <div class="datos-extra">
                                <div>
                                    <p class="datos-registro">Costo: <span id="costo"></span></p>
                                    <i class="fa-solid fa-sack-dollar"></i>
                                </div>
                                <div>
                                    <p class="datos-registro">Duración: <span id="duracion"></span></p>
                                    <i class="fa-solid fa-hourglass-start"></i>
                                </div>
                            </div>
                        </div>
                    </section>
                <% } else { %>
                    <div class="alert alert-info" role="alert">
                        <p class="no-registros">No tiene registros aún</p>
                    </div>
                <% } 
            } else { %>
                <h2 class="heading">Clases que <span>Dicta</span></h2>
                <% if(listC != null){ %>
                    <section class="contenedor-registros">
                        <div class="registros">
                            <h5>Total Clases <span><%= listC.length %></span></h5>
                            <ul class="list-group">
                                <% for(String c: listC){ %>
                                    <% String[] clas = c.split(","); %>
                                    <li class="list-group-item d-flex justify-content-between align-items-center">
                                        <%= clas[0] %>
                                        <span class="badge bg-primary rounded-pill info-extra" data-clase="<%= clas[0] %>">Info <i class="fa-solid fa-circle-info" data-clase="<%= clas[0] %>"></i></span>
                                    </li>
                                <% }%>
                            </ul>
                        </div>

                        <div class="info-registros">
                            <h5>Información de <span id="clase"></span></h5>

                            <div>
                                <p class="datos-registro">Hora de inicio: <span id="hora"></span></p> 
                                <i class="fa-solid fa-clock"></i>
                            </div>
                            <div> 
                                <p class="datos-registro">Fecha de registro: <span id="fecha"></span></p> 
                                <i class="fa-solid fa-calendar-days"></i> 
                            </div>
                            <div>
                                <p class="datos-registro">Actividad deportiva: <span id="actividad"></span></p>
                                <i class="fa-solid fa-futbol"></i>
                            </div>
                            <div class="datos-extra">
                                <div>
                                    <p class="datos-registro">Costo: <span id="costo"></span></p>
                                    <i class="fa-solid fa-sack-dollar"></i>
                                </div>
                                <div>
                                    <p class="datos-registro">Duración: <span id="duracion"></span></p>
                                    <i class="fa-solid fa-hourglass-start"></i>
                                </div>
                            </div>
                        </div>
                    </section>
                <% } else { %>
                    <div class="alert alert-info" role="alert">
                        <p class="no-registros">No tiene registros aún</p>
                    </div>
                <% } 
            } %>
   
        <% } else if(user != null && tipoR.equals("act") && (tipoUsuario.equals("profesor") || tipoUsuario.equals("socio"))){ %>
            <h2 class="heading">Consultar <span>Actividades</span></h2>
            <%@include file="consultarActividades.jsp" %>
            
        <% } else if( user != null && tipoR.equals("clases") && tipoUsuario.equals("profesor")){ %>
            <h2 class="heading">Consultar <span>Clases</span></h2>
            <%@ include  file="consultarClases.jsp" %>
        <% } else { %>
            <%@include file="errorPagina.jsp" %>
        <% } %>
        
    </main>
    
    <%@include file="footer.jsp" %>
    
    <script type="text/javascript">
        document.addEventListener('DOMContentLoaded', async ()=>{
            const fecha = document.querySelector("#fechaN");
            if(fecha !== null){
                fecha.textContent = "<%= user.getFechaNac().getTime().toString()%>".includes("-") ? "<%= user.getFechaNac().getTime().toString()%>" : formatearFechaA("<%= user.getFechaNac().getTime().toString()%>");
            }
            const btnInfo = document.querySelectorAll(".info-extra");
            const btnInfoIcono = document.querySelectorAll(".info-extra i");
            if(btnInfo !== null && btnInfoIcono !== null){
                btnInfo.forEach(btn => {
                   const clase = btn.getAttribute('data-clase');
                   
                   btn.addEventListener('click', async (e) =>{
                       const c = document.querySelector("#clase");
                       if(c.textContent !== e.target.getAttribute('data-clase')){
                           c.textContent = e.target.getAttribute('data-clase');
                           await obtenerDatos(e.target.getAttribute('data-clase'));
                       }
                   });
                });
                
                btnInfoIcono.forEach(btn => {
                   const clase = btn.getAttribute('data-clase');
                   
                   btn.addEventListener('click', async (e) =>{
                       const c = document.querySelector("#clase");
                       if(c.textContent !== e.target.getAttribute('data-clase')){
                           c.textContent = e.target.getAttribute('data-clase');
                           await obtenerDatos(e.target.getAttribute('data-clase'));
                       }
                   });
                });
            }
        });
        
        async function obtenerDatos(clase){
            const url = "Consultas?consultar=registros&clase=" + clase;
             await fetch(url)
                    .then(response => response.json())
                        .then(data =>{
                            const acti = document.querySelector("#actividad");
                            const costo = document.querySelector("#costo");
                            const duracion = document.querySelector("#duracion");
                            const horaInicio = document.querySelector("#hora");
                            const fecha = document.querySelector("#fecha");
                            let d = data[0];
                            let indiceInicio = d.indexOf("[");
                            let indiceFin = d.lastIndexOf("]");
                            let resultado = (d.substring(indiceInicio + 1, indiceFin)).split(",");
                            acti.textContent = resultado[0];
                            costo.textContent = resultado[1];
                            duracion.textContent = resultado[2];
                            
                            <%if (list != null) {
                                for(String r: list){ %>
                                    <% String[] reg = r.split(","); %>        
                                    if ("<%=reg[1]%>"===clase){
                                        horaInicio.textContent = "<%=reg[3]%>";
                                        fecha.textContent = "<%=reg[2]%>".includes("-") ? formatearFecha(Date.parse("<%=reg[2]%>")) : formatearFechaA("<%=reg[2]%>");
                                    }
                                <% }
                            } else if(listC !=null) {%>
                                <% for(String c: listC) { %>
                                    <% String[] clas = c.split(","); %>
                                    if ("<%=clas[0]%>"===clase){
                                        horaInicio.textContent = "<%=clas[2]%>";
                                        fecha.textContent = "<%=clas[1]%>".includes("-") ? formatearFecha(Date.parse("<%=clas[1]%>")) : formatearFechaA("<%=clas[1]%>");
                                    }
                                 
                                <% }
                            } %>
                        })
                        .catch(error =>{
                            console.error(error);
                    });
        }
        function formatearFecha(fecha){
                    // Obtén la fecha en formato de texto
            var fechaTexto = fecha;

            // Crea un objeto de fecha a partir del texto
            var fechaObjeto = new Date(fechaTexto);

            // Obtiene los componentes de la fecha
            var dia = fechaObjeto.getDate();
            var mes = fechaObjeto.getMonth() + 1; // Los meses comienzan desde 0, por lo que sumamos 1
            var anio = fechaObjeto.getFullYear();

            // Formatea el mes, día, hora, minutos y segundos para que tengan dos dígitos
            mes = (mes < 10) ? '0' + mes : mes;
            dia = (dia < 10) ? '0' + dia : dia;

            // Construye la cadena de texto formateada
            var fechaFormateada = dia + '/' + mes + '/' + anio;
            return fechaFormateada;
        }
        
        function formatearFechaA(fecha){
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
    <% } else {%>
    <%@include file="errorPagina.jsp" %>
    <% } %>
</body>
</html>

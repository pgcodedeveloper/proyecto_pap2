<%@page import="publicadores.ControladorPublish"%>
<%@page import="publicadores.ControladorPublishServiceLocator"%>
<%@page import="publicadores.ControladorPublishService"%>
<%@page import="logica.Usuario" %>
<%@page import="logica.Clase" %>
<%@page import="logica.ActividadDeportiva" %>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="interfaces.Fabrica"%>
<%@page import="interfaces.IControlador"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
    <title>EntrenamosUy - Mejor Lugar para entrenar</title>
</head>
<body>
    <%@include file="header.jsp" %>
    
    <% if(!error) { %>
        <main class="main main-rankings">
            <%
            ControladorPublishService cps = new ControladorPublishServiceLocator();
            ControladorPublish port = cps.getControladorPublishPort();
            
            String[] rankClases = port.rankingClases();
            String[] rankAct = port.rankingActividades();
            %>
            <h2 class="heading"> Ranking de <span>Clases</span></h2>
            <section class="seccion-ranking">
                <table class="table table-striped" style="margin-bottom: 40px;">
                    <thead>
                      <tr>

                        <th scope="col">#</th>
                        <th scope="col">Nombre</th>
                        <th scope="col">Fecha de Clase</th>
                        <th scope="col"style="text-align: left;">URL</th>
                        <th scope="col">Socios Inscriptos</th>
                      </tr>
                    </thead>
                    <tbody>
                        <% 
                            int i = 1;
                            for (String c:rankClases){
                        %>
                        <tr>
                            <th scope="row"><%=i%></th>
                            <td><%= c.split(",,")[0] %></td>
                            <td id="fecha"> <%= c.split(",,")[1] %></td>
                            <td><%= c.split(",,")[2] %></td>
                            <td><%= c.split(",,")[3] %></td>
                        </tr>
                        <% 
                            i++;
                            }
                        %>
                    </tbody>
                </table>
            </section>
            <h2 class="heading"> Ranking de <span>Actividades</span></h2>
            <section class="seccion-ranking">
                <table class="table table-striped">
                    <thead>
                      <tr>
                        <th scope="col">#</th>
                        <th scope="col">Nombre</th>
                        <th scope="col">Costo</th>
                        <th scope="col"style="text-align: left;">Descripcion</th>
                        <th scope="col">Clases Asociadas</th>
                      </tr>
                    </thead>
                    <tbody>
                        <% 
                            i = 1;
                            for (String a:rankAct){
                                
                        %>
                        <tr>
                            <th scope="row"><%=i%></th>
                            <td><%= a.split(",,")[0] %></td>
                            <td><%= a.split(",,")[1] %></td>
                            <td><%= a.split(",,")[2] %></td>
                            <td><%= a.split(",,")[3] %></td>
                        </tr>
                        <% 
                            i++;
                            }
                        %>
                    </tbody>
                </table>
            </section>
        </main>
        <%@include file="footer.jsp" %>
        <script type="text/javascript">
            document.addEventListener('DOMContentLoaded', async ()=>{
                
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
    <% } else { %>
        <%@include file="errorPagina.jsp" %>
    <% } %>
    
    
</body>
</html>

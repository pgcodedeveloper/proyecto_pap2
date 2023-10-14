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
            Fabrica fb = Fabrica.getInstancia();
            IControlador icon = fb.getIControlador();
        
            ArrayList <Object[]> listaClases = icon.rankingClases();
            ArrayList <Object[]> listaAct = icon.rankingActividades();
  
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
                            for (Object[] cs:listaClases){
                                Clase c= (Clase)cs[0];
                        %>
                        <tr>
                            <th scope="row"><%=i%></th>
                            <td><%=c.getNombre()%></td>
                            <td><%=c.getFecha()%></td>
                            <td><%=c.getUrl()%></td>
                            <td><%=cs[1]%></td>
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
                            for (Object[] lista:listaAct){
                                ActividadDeportiva a= (ActividadDeportiva)lista[0];
                        %>
                        <tr>
                            <th scope="row"><%=i%></th>
                            <td><%=a.getNombre()%></td>
                            <td><%=a.getCosto()%></td>
                            <td><%=a.getDescripcion()%></td>
                            <td><%=lista[1]%></td>
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
    <% } else { %>
        <%@include file="errorPagina.jsp" %>
    <% } %>
</body>
</html>

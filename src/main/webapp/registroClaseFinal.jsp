<%@page import="logica.Usuario" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>EntrenamosUy - Registrate a una clase</title>
</head>
<body>
    <%@include file="header.jsp" %>
    
    
    <main class="main">
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
                  <th scope="col">Url</th>
                </tr>
            </thead>
            <tbody class="table-group-divider">
                <tr>
                    <th scope="row">1</th>
                    <td><p>${actividad.getNombre()}</p></td>
                    <td><p>${clase.getHoraInicio()}</p></td>
                    <td><p>${actividad.getInst().getNombre()}</p></td>
                    <td><p>${clase.getUrl()}</p></td>
                </tr>
            </tbody>
        </table>
                
        <div class="contenedor_botones">
            <button type="submit" id="btn" class="btn btn-danger">
                Cancelar
                <i class="fa-solid fa-file-circle-xmark"></i>
            </button>
            <button type="submit" id="btn" class="btn btn-primary">
                Confirmar
                <i class="fa-solid fa-file-circle-check"></i>
            </button>
        </div>
    </main>
    
    <%@include file="footer.jsp" %>
</body>
</html>

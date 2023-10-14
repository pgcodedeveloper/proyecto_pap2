<%-- 
    Document   : header
    Created on : 8 sept 2023, 15:25:53
    Author     : PC
--%>
<!DOCTYPE html>
<%@page import="logica.Usuario" %>
<%@ page language="java"%>

<%  
    boolean error = false;
    boolean tipoUS = false;
    boolean tipoUP = false;
    String contextPath = request.getContextPath(); // Obtiene el contexto de la aplicación
    String requestURI = request.getRequestURI();   // Obtiene la URI de la solicitud
    String currentPage = requestURI.substring(contextPath.length());
    if(((Usuario) session.getAttribute("usuario")) != null){
        Usuario u = ((Usuario) session.getAttribute("usuario"));
        String tipo = ((String) session.getAttribute("tipoUser"));

        if(u != null && tipo != null){
            //out.println(u);
            //out.println(tipo);
            if(tipo.equals("profesor")){
                tipoUP = true;
            }
            else if(tipo.equals("socio")){
                tipoUS = true;
            }
        }
        else{
            tipoUS = false;
            tipoUP = false;
        }
    }
    else{
        error = true;
    }
%>

<html>
    <head>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta charset="UTF-8">
        <link rel="stylesheet" href="css/styles.css"/>
        <link rel="icon" href="img/dumbbell-solid.ico" type="image/x-icon">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous"/>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css" integrity="sha512-z3gLpd7yknf1YoNbCzqRKc4qyor8gaKU1qmn+CShxbuBusANI9QpRohGBreCFkKxLhei6S9CQXFEbbKuqLg0DA==" crossorigin="anonymous" referrerpolicy="no-referrer" />
    </head>
    <body>
        <% if(!error || (currentPage.equals("/login.jsp") || currentPage.equals("/registro.jsp"))){ %>
            <% if(currentPage.equals("/login.jsp") || currentPage.equals("/registro.jsp")){ %>
                    <div class="contenedor_login">
                        <div class="contenedor_login_icono">
                            <i class="fa-solid fa-dumbbell"></i>
                        </div>
                        <div class="contenedor_login_texto">
                            <h1>Entrenamos<span>Uy</span></h1>
                            <p>El mejor lugar para entrenar</p>
                        </div>
                    </div>     
            <%  }
                else{ %>
                    <header class="header">
                        <nav class="navbar navbar-expand-lg navbar-light bg-light">
                            <div class="container-fluid">
                                <a class="navbar-brand heading-logo" href="/entrenamosuy/">Entrenamos<span>Uy</span></a>
                                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
                                    <span class="navbar-toggler-icon"></span>
                                </button>
                                <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
                                    <div class="navbar-nav ms-auto">
                                        <% if(tipoUS){ %>
                                            <a class="nav-link" aria-current="page" href="registroClase.jsp"><strong>Registro a Clase</strong></a>
                                            <a class="nav-link" aria-current="page" href="eliminarRegistro.jsp"><strong>Eliminar Registro</strong></a>
                                            <a class="nav-link" aria-current="page" href="modificarPerfil.jsp"><strong>Modificar Perfil</strong></a>
                                            <div class="btn-group btn-consultas">
                                                <button class="btn btn-sm dropdown-toggle nav-link" type="button" data-bs-toggle="dropdown" aria-expanded="false">
                                                    <strong>Consultar</strong>
                                                </button>
                                                <ul class="dropdown-menu">
                                                    <li><a class="dropdown-item" href="consultas.jsp?tipo=usuario">Perfil</a></li>
                                                    <li><hr class="dropdown-divider"></li>
                                                    <li><a class="dropdown-item" href="consultas.jsp?tipo=act">Actividades Deportivas</a></li>
                                                </ul>
                                            </div>
                                            <button class="nav-link btn" onclick="cerrarSesion()"><strong>Cerrar Sesión</strong></button>
                                        <% } else if(tipoUP){ %>
                                            <a class="nav-link" href="altaDictadoClase.jsp"><strong>Registro Dictado</strong></a>
                                            <a class="nav-link" href="rankings.jsp"><strong>Ranking Dictados y Actividades</strong></a>
                                            <a class="nav-link" aria-current="page" href="modificarPerfil.jsp"><strong>Modificar Perfil</strong></a>
                                            <div class="btn-group btn-consultas">
                                                <button class="btn btn-sm dropdown-toggle nav-link" type="button" data-bs-toggle="dropdown" aria-expanded="false">
                                                    <strong>Consultar</strong>
                                                </button>
                                                <ul class="dropdown-menu">
                                                    <li><a class="dropdown-item" href="consultas.jsp?tipo=usuario">Perfil</a></li>
                                                    <li><hr class="dropdown-divider"></li>
                                                    <li><a class="dropdown-item" href="consultas.jsp?tipo=act">Actividades Deportivas</a></li>
                                                    <li><hr class="dropdown-divider"></li>
                                                    <li><a class="dropdown-item" href="consultas.jsp?tipo=clases">Clases</a></li>
                                                </ul>
                                            </div>
                                            <button class="nav-link btn" onclick="cerrarSesion()"><strong>Cerrar Sesión</strong></button>
                                        <% } else { %>
                                            <a class="nav-link" href="login.jsp"><strong>Login</strong></a>
                                        <% } %>
                                    </div>
                                </div>
                            </div>
                        </nav>
                    </header>


            <%  }%>


            <script type="text/javascript">
                async function cerrarSesion(){
                    Swal.fire({
                        title: "Atención !",
                        text: "¿Desea cerrar la sesión?",
                        showConfirmButton: true,
                        confirmButtonText: 'Si, cerrar',
                        showCancelButton: true,
                        cancelButtonText: "No",
                        icon: "question"
                    }).then(async (response) => {
                        if(response.isConfirmed){
                            const formData = new URLSearchParams();
                            formData.append("tipo","cerrar");

                            await fetch("Login", {
                                method: 'POST',
                                body: formData.toString(), // Convierte FormData a cadena de consulta
                                headers: {
                                    'Content-Type': 'application/x-www-form-urlencoded' // Establece el tipo de contenido
                                }
                            });
                            window.location.href = window.location.origin + "/entrenamosuy/login.jsp";
                        }
                    });
                }
            </script>
        <% } %>
    </body>
</html>

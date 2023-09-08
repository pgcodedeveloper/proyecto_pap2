<%-- 
    Document   : header
    Created on : 8 sept 2023, 15:25:53
    Author     : PC
--%>
<!DOCTYPE html>
<%@ page language="java"%>

<html>
    <head>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta charset="UTF-8">
        <link rel="stylesheet" href="css/styles.css"/>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous"/>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css" integrity="sha512-z3gLpd7yknf1YoNbCzqRKc4qyor8gaKU1qmn+CShxbuBusANI9QpRohGBreCFkKxLhei6S9CQXFEbbKuqLg0DA==" crossorigin="anonymous" referrerpolicy="no-referrer" />
    </head>
    <body>
        <%
            String contextPath = request.getContextPath(); // Obtiene el contexto de la aplicación
            String requestURI = request.getRequestURI();   // Obtiene la URI de la solicitud
            String currentPage = requestURI.substring(contextPath.length());
            
            if(currentPage.equals("/login.jsp")){ %>
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
                    <nav class="navbar navbar-expand-lg bg-body-tertiary">
                        <div class="container-fluid">
                            <a class="navbar-brand" href="#">Navbar</a>
                            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
                                <span class="navbar-toggler-icon"></span>
                            </button>
                            <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
                                <div class="navbar-nav">
                                    <a class="nav-link active" aria-current="page" href="#">Home</a>
                                    <a class="nav-link" href="#">Features</a>
                                    <a class="nav-link" href="#">Pricing</a>
                                    <a class="nav-link disabled" aria-disabled="true">Disabled</a>
                                </div>
                            </div>
                        </div>
                    </nav>
                </header>
        <%  }%>
        
    </body>
</html>

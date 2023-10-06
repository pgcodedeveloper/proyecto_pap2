<div class="contenedor-alerta">
    <h2 class="titulo-alerta">Ups! <span><i class="fa-solid fa-ban"></i> </span></h2>
    <p class="texto-alerta">No tienes permisos o la página es incorrecta</p>
    
    <div class="contenedor_acciones">
        <% if(error){ %>
            <a href="/entrenamosuy/login.jsp">Regresar al <span>Inicio Sesión</span></a>
        <% } else{ %>
            <a href="/entrenamosuy/">Regresar al <span>Inicio</span></a>
        <% } %>
    </div>
</div>


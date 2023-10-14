
<section class="contenedor">
    <form class="formulario">
        <fieldset>
            <legend>Instituci�n Deportiva</legend>
            <div class="input-group mb-3">
                <span class="input-group-text"><i class="fa-solid fa-dumbbell"></i></span>
                <select class="form-select" id="instituciones" aria-label="Default select example">
                    <option selected disabled>Seleccione una opci�n</option>
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
        
        <div class="contenedor_botones">
            <button type="submit" id="btn" class="btn btn-primary" disabled>
                Consultar Datos
                <i class="fa-solid fa-magnifying-glass"></i>
            </button>
        </div>
    </form>
    
    <h2 class="heading">Datos de la <span>Actividad</span></h2>
    <div class="contenedor-actividades">    
        <div class="actividades">
            <h5>Actividad: <span></span></h5>
            
            <div>
                <p class="datos-registro">Descripci�n <span id="descripcion"></span></p> 
                <i class="fa-solid fa-comment-dots"></i>
            </div>
            <div> 
                <p class="datos-registro">Fecha de registro: <span id="fechaR"></span></p> 
                <i class="fa-solid fa-calendar-days"></i> 
            </div>
            <div>
                <p class="datos-registro">Instituci�n <span id="institucion"></span></p>
                <i class="fa-solid fa-graduation-cap"></i>
            </div>
            <div class="datos-extra">
                <div>
                    <p class="datos-registro">Costo: <span id="costoA"></span></p>
                    <i class="fa-solid fa-sack-dollar"></i>
                </div>
                <div>
                    <p class="datos-registro">Duraci�n: <span id="duracionA"></span></p>
                    <i class="fa-solid fa-hourglass-start"></i>
                </div>
            </div>
            <div class="datos-imagen">
                <div class="contenedor-imagen">
                    
                </div>
                <i class="fa-solid fa-images"></i>
            </div>
        </div>

        <div class="info-actividades">
            <h5>Clases de la Actividad</h5>
            <ul class="list-group" id="lista-clases">
                
            </ul>
        </div>
    </div>
</section>

<script type="text/javascript">
    document.addEventListener('DOMContentLoaded', async ()=>{
        const select = document.querySelector("#instituciones");
        const selectA = document.querySelector("#actividades");
        const btnBuscar = document.querySelector("#btn");
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
                            select.appendChild(option); // Agrega la opci�n al select
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
                            option.textContent = "Seleccione una opci�n"; // Texto visible en el option
                            option.selected = true;
                            option.disabled = true;
                            selectA.appendChild(option);
                            data.forEach(item => {
                                const option = document.createElement("option");
                                option.value = item; // Valor del option
                                option.textContent = item; // Texto visible en el option
                                selectA.appendChild(option); // Agrega la opci�n al select
                            });
                        }
                    })
                    .catch(error =>{
                        console.error(error);
                    });
                });
                
                selectA.addEventListener("change", () =>{
                   btnBuscar.disabled = false;
                });
            }    
        }
        
        
        const form = document.querySelector(".formulario");
        form.addEventListener("submit", async (e) =>{
           e.preventDefault();
           const actividad = document.querySelector('#actividades').value;
           if(actividad !== "No hay actividades" || actividad !== null){
                const url = 'Consultas?consultar=actividades&act=' + actividad;
                await fetch(url)
                .then(response => response.json())
                .then(data =>{
                    console.log(data);
                    const desc = document.querySelector("#descripcion");
                    const fechaR = document.querySelector("#fechaR");
                    const inst = document.querySelector("#institucion");
                    const costoA = document.querySelector("#costoA");
                    const duracionA = document.querySelector("#duracionA");
                    
                    desc.textContent = data[0][0];
                    fechaR.textContent = data[0][1];
                    inst.textContent = data[0][2];
                    costoA.textContent = data[0][3];
                    duracionA.textContent = data[0][4];
                    
                    const lista = document.querySelector("#lista-clases");
                    lista.innerHTML = "";
                    data[1].forEach(d =>{
                        let dato = "<li class='list-group-item d-flex justify-content-between align-items-center'>";
                        dato += d;
                        dato += "<form class='form' action=\"VerClase?clase=" + d + "\" method='POST'><button type='submit' class='badge bg-primary rounded-pill btn-info'>Info <i class='fa-solid fa-circle-info' ></i> </button> </form>";
                        dato += "</li>";
                        lista.innerHTML += dato;
                    });
                    const contImg = document.querySelector(".contenedor-imagen");
                    contImg.innerHTML = "";
                    let img = "<img src='mostrarImagen?tipo=actividad&act=" + actividad + "'";
                    img += " class='img-fluid rounded-start' alt='Imagen de Actividad' />";
                    console.log(img);
                    contImg.innerHTML += img;
                    
                    
                })
                .catch(error =>{
                    console.error(error);
                });
           }
        });
        
            
    });
    
    async function verDatosClase(clase){
        const url = "VerClase?clase=" + clase;
        try{
            // Realiza la solicitud AJAX al Servlet usando async/await
            const response = await fetch(url, {
                method: 'POST'
            });
            window.location.href = window.location.origin + "/entrenamosuy/verClase.jsp";
        } catch (error) {
            // Maneja cualquier error que ocurra durante la solicitud
            console.error('Error:', error);
        }
    }
</script>
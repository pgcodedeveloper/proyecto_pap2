
<section class="contenedor">
    <form class="formulario">
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
        
        <div class="contenedor_botones">
            <button type="submit" id="btn" class="btn btn-primary" disabled>
                Consultar Datos
                <i class="fa-solid fa-magnifying-glass"></i>
            </button>
        </div>
    </form>
    
    <h2 class="heading">Clases de la <span>Actividad</span></h2>
    <div class="contenedor-clase">    
        <div class="info-actividades ">
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
                    
                    const lista = document.querySelector("#lista-clases");
                    lista.innerHTML = "";
                    data[1].forEach(d =>{
                        let dato = "<li class='list-group-item d-flex justify-content-between align-items-center'>";
                        dato += d;
                        dato += "<form class='form' action=\"VerClase?clase=" + d + "\" method='POST'><button type='submit' class='badge bg-primary rounded-pill btn-info'>Info <i class='fa-solid fa-circle-info' ></i> </button> </form>";
                        dato += "</li>";
                        lista.innerHTML += dato;
                    });
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
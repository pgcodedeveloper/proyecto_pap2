/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */
(function(){
    function enviarSubmit(){
        const form = document.querySelector(".formulario");

        form.addEventListener("submit",function(e){
            e.preventDefault();

            const url = "RegistroUsuario";
            const nick = document.querySelector("#nick").value;
            const email = document.querySelector("#email").value;
            const password = document.querySelector("#password").value;
            const passwordR = document.querySelector("#passwordR").value;
            const imagen = document.querySelector("#imagen");
            var datos = {
                nick: nick,
                email: email,
                password: password,
                passwordR: passwordR,
                imagen: imagen
            };
            const opciones = {
                method: 'POST', // Método HTTP
                body: JSON.stringify(datos), // Convierte los datos a JSON
                headers: {
                    'Content-Type': 'application/json'
                }  
            }
            fetch(url, opciones)
                .then(function(response) {
                    if (response.ok) {
                        return response.text(); // Parsea la respuesta como texto
                    } else {
                        throw new Error('Error en la solicitud AJAX');
                    }
                })
                .then(function(data) {
                    // Manipula la respuesta del servidor (en 'data')
                    mostrarMensaje(data);
                })
                .catch(function(error) {
                    // Maneja cualquier error que ocurra durante la solicitud
                    console.error('Error:', error);
                });
        });
    
    }
        
    function mostrarMensaje(mensaje) {
        // Aquí puedes usar SweetAlert2 o cualquier otra biblioteca para mostrar un mensaje
        Swal.fire({ title: 'Mensaje', text: mensaje, icon: 'info' });
    }
});


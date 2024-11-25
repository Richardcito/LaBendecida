// Navbar
const navSlide = () => {
    const burger = document.querySelector('.burger');
    const nav = document.querySelector('.nav-links');
    const navLinks = document.querySelectorAll('.nav-links li');
  
    burger.addEventListener('click', () => {
        // Toggle Nav
        nav.classList.toggle('nav-active');
  
        // Animate Links
        navLinks.forEach((link, index) => {
            if (link.style.animation) {
                link.style.animation = '';
            } else {
                link.style.animation = `navLinkFade 0.5s ease forwards ${index / 7 + 0.3}s`;
            }
        });
  
        // Burger Animation
        burger.classList.toggle('toggle');
    });
}
  
navSlide();

document.addEventListener('DOMContentLoaded', function() {
    const especialidadSelect = document.getElementById('especialidad');
    const medicoSelect = document.getElementById('medico');
    const fechaInput = document.getElementById('fecha');
    const horaSelect = document.getElementById('hora');
    const formularioReserva = document.getElementById('formulario-reserva');
    const mensajeDiv = document.getElementById('mensaje');

    // Cargar especialidades
    fetch('php/obtener_especialidades.php')
        .then(response => response.json())
        .then(especialidades => {
            especialidades.forEach(especialidad => {
                const option = document.createElement('option');
                option.value = especialidad.id;
                option.textContent = especialidad.nombre;
                especialidadSelect.appendChild(option);
            });
        });

    // Evento al cambiar la especialidad
    especialidadSelect.addEventListener('change', function() {
        medicoSelect.disabled = false;
        medicoSelect.innerHTML = '<option value="">Seleccione un médico</option>';
        fechaInput.disabled = true;
        horaSelect.disabled = true;

        if (this.value) {
            fetch(`php/obtener_medicos.php?especialidad_id=${this.value}`)
                .then(response => response.json())
                .then(medicos => {
                    medicos.forEach(medico => {
                        const option = document.createElement('option');
                        option.value = medico.id;
                        option.textContent = medico.nombre_completo;
                        medicoSelect.appendChild(option);
                    });
                });
        }
    });

    // Evento al cambiar el médico
    medicoSelect.addEventListener('change', function() {
        fechaInput.disabled = !this.value;
        horaSelect.disabled = true;
        
        if (this.value) {
            // Establecer la fecha mínima como hoy
            const today = new Date().toISOString().split('T')[0];
            fechaInput.min = today;
            
            // Establecer la fecha máxima como 30 días a partir de hoy
            const maxDate = new Date();
            maxDate.setDate(maxDate.getDate() + 30);
            fechaInput.max = maxDate.toISOString().split('T')[0];
        }
    });

    // Evento al cambiar la fecha
    fechaInput.addEventListener('change', function() {
        horaSelect.disabled = false;
        horaSelect.innerHTML = '<option value="">Seleccione una hora</option>';

        if (this.value && medicoSelect.value) {
            fetch(`php/obtener_horarios.php?medico_id=${medicoSelect.value}&fecha=${this.value}`)
                .then(response => response.json())
                .then(horarios => {
                    if (horarios.length === 0) {
                        const option = document.createElement('option');
                        option.textContent = 'No hay horarios disponibles';
                        horaSelect.appendChild(option);
                        horaSelect.disabled = true;
                    } else {
                        horarios.forEach(hora => {
                            const option = document.createElement('option');
                            option.value = hora;
                            option.textContent = hora;
                            horaSelect.appendChild(option);
                        });
                    }
                })
                .catch(error => {
                    console.error('Error al obtener horarios:', error);
                    horaSelect.innerHTML = '<option value="">Error al cargar horarios</option>';
                });
        }
    });

    // Evento al enviar el formulario
    formularioReserva.addEventListener('submit', function(e) {
        e.preventDefault();

        const formData = new FormData();
        formData.append('medico_id', medicoSelect.value);
        formData.append('fecha', fechaInput.value);
        formData.append('hora', horaSelect.value);
        formData.append('motivo', document.getElementById('motivo').value);

        fetch('php/reservar_cita.php', {
            method: 'POST',
            body: formData
        })
        .then(response => response.json())
        .then(data => {
            if (data.success) {
                mensajeDiv.textContent = data.message;
                formularioReserva.reset();
            } else {
                mensajeDiv.textContent = 'Error: ' + data.message;
            }
        });
    });
});

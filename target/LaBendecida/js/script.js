document.getElementById('formulario-reserva').addEventListener('submit', function(event) {
    event.preventDefault();

    const paciente_id = document.getElementById('paciente_id').value;
    const doctor_id = document.getElementById('doctor_id').value;
    const nombre = document.getElementById('nombre').value;
    const email = document.getElementById('email').value;
    const fecha = document.getElementById('fecha').value;
    const hora = document.getElementById('hora').value;
    const motivo = document.getElementById('motivo').value;

    const datos = new FormData();
    datos.append('paciente_id', paciente_id);
    datos.append('doctor_id', doctor_id);
    datos.append('nombre', nombre);
    datos.append('email', email);
    datos.append('fecha', fecha);
    datos.append('hora', hora);
    datos.append('motivo', motivo);

    fetch('php/reservar_cita.php', {
        method: 'POST',
        body: datos
    })
    .then(response => response.text())
    .then(data => {
        document.getElementById('mensaje').innerText = data;
    })
    .catch(error => {
        document.getElementById('mensaje').innerText = 'Error al enviar el formulario.';
    });
});
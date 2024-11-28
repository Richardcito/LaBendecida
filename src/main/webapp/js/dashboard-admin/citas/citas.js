// Funciones para el modal
function mostrarModalCita() {
    document.getElementById('modalCita').style.display = 'block';
    limpiarFormulario();
}

function cerrarModal() {
    document.getElementById('modalCita').style.display = 'none';
}

function limpiarFormulario() {
    document.getElementById('citaForm').reset();
}

// Funciones para CRUD
function editarCita(id) {
    mostrarModalCita();
    // Aquí irá la lógica para cargar los datos de la cita
}

function eliminarCita(id) {
    if (confirm('¿Está seguro de que desea eliminar esta cita?')) {
        // Aquí irá la lógica para eliminar la cita
    }
}

// Event Listeners
document.getElementById('citaForm').addEventListener('submit', function(e) {
    e.preventDefault();
    // Aquí irá la lógica para guardar la cita
});

// Cerrar modal al hacer clic fuera de él
window.onclick = function(event) {
    if (event.target == document.getElementById('modalCita')) {
        cerrarModal();
    }
}

// Cargar datos iniciales
document.addEventListener('DOMContentLoaded', function() {
    // Aquí irá la lógica para cargar médicos y pacientes en los selects
}); 
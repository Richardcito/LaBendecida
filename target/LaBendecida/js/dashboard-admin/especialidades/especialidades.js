// Funciones para el modal
function mostrarModalEspecialidad() {
    document.getElementById('modalEspecialidad').style.display = 'block';
    limpiarFormulario();
}

function cerrarModal() {
    document.getElementById('modalEspecialidad').style.display = 'none';
}

function limpiarFormulario() {
    document.getElementById('especialidadForm').reset();
}

// Funciones para CRUD
function editarEspecialidad(id) {
    // Implementar lógica para editar
    mostrarModalEspecialidad();
    // Cargar datos de la especialidad
}

function eliminarEspecialidad(id) {
    if (confirm('¿Está seguro de que desea eliminar esta especialidad?')) {
        // Implementar lógica para eliminar
    }
}

// Event Listeners
document.getElementById('especialidadForm').addEventListener('submit', function(e) {
    e.preventDefault();
    // Implementar lógica para guardar
});

// Cerrar modal al hacer clic fuera de él
window.onclick = function(event) {
    if (event.target == document.getElementById('modalEspecialidad')) {
        cerrarModal();
    }
} 
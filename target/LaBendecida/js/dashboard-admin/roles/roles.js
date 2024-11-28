// Variables para el modal
const modal = document.getElementById('modalRol');
const form = document.getElementById('formRol');
const btnNuevo = document.getElementById('btnNuevoRol');
const span = document.getElementsByClassName('close')[0];

// Funciones para el modal
function mostrarModalNuevoRol() {
    document.getElementById('modalTitle').textContent = 'Nuevo Rol';
    form.reset();
    document.getElementById('rolId').value = '';
    modal.style.display = 'block';
}

function editarRol(id, nombre, descripcion) {
    document.getElementById('modalTitle').textContent = 'Editar Rol';
    document.getElementById('rolId').value = id;
    document.getElementById('nombre').value = nombre;
    document.getElementById('descripcion').value = descripcion;
    modal.style.display = 'block';
}

function eliminarRol(id) {
    if (confirm('¿Está seguro de que desea eliminar este rol?')) {
        const form = document.createElement('form');
        form.method = 'POST';
        form.action = 'roles?action=delete&id=' + id;
        document.body.appendChild(form);
        form.submit();
    }
}

function cerrarModal() {
    modal.style.display = 'none';
}

// Event Listeners
btnNuevo.onclick = mostrarModalNuevoRol;
span.onclick = cerrarModal;

window.onclick = function(event) {
    if (event.target == modal) {
        cerrarModal();
    }
}

// Manejar el envío del formulario
form.onsubmit = function(e) {
    e.preventDefault();
    const id = document.getElementById('rolId').value;
    const action = id ? 'update' : 'create';
    
    form.action = 'roles?action=' + action;
    form.submit();
} 
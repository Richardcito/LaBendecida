document.addEventListener('DOMContentLoaded', function() {
    // Inicializar eventos
    document.getElementById('btnNuevoUsuario').addEventListener('click', () => mostrarModal());
    document.getElementById('filtroRol').addEventListener('change', aplicarFiltros);
    document.getElementById('filtroEstado').addEventListener('change', aplicarFiltros);

    // Cargar datos iniciales
    cargarUsuarios();
});

function cargarUsuarios() {
    fetch('/LaBendecida/api/usuarios')
        .then(response => response.json())
        .then(usuarios => {
            const tbody = document.querySelector('#tablaUsuarios tbody');
            tbody.innerHTML = '';
            
            usuarios.forEach(usuario => {
                const tr = document.createElement('tr');
                tr.innerHTML = `
                    <td>${usuario.id}</td>
                    <td>${usuario.nombre || ''}</td>
                    <td>${usuario.email}</td>
                    <td>${usuario.rol || 'No asignado'}</td>
                    <td>${formatearFecha(usuario.fechaRegistro)}</td>
                    <td><span class="status-badge ${usuario.estado ? 'active' : 'inactive'}">
                        ${usuario.estado ? 'Activo' : 'Inactivo'}</span></td>
                    <td class="actions">
                        <button class="btn-action edit" onclick="editarUsuario(${usuario.id})">
                            <i class="fas fa-edit"></i>
                        </button>
                        <button class="btn-action delete" onclick="eliminarUsuario(${usuario.id})">
                            <i class="fas fa-trash"></i>
                        </button>
                    </td>
                `;
                tbody.appendChild(tr);
            });
        })
        .catch(error => console.error('Error:', error));
}

function mostrarModal(usuarioId = null) {
    const modal = document.createElement('div');
    modal.className = 'modal';
    modal.innerHTML = `
        <div class="modal-content">
            <h2>${usuarioId ? 'Editar Usuario' : 'Nuevo Usuario'}</h2>
            <form id="formUsuario">
                <input type="hidden" name="id" value="${usuarioId || ''}">
                <div class="form-group">
                    <label for="nombre">Nombre:</label>
                    <input type="text" name="nombre" id="nombre" required>
                </div>
                <div class="form-group">
                    <label for="apellido">Apellido:</label>
                    <input type="text" name="apellido" id="apellido" required>
                </div>
                <div class="form-group">
                    <label for="email">Email:</label>
                    <input type="email" name="email" id="email" required>
                </div>
                ${!usuarioId ? `
                <div class="form-group">
                    <label for="password">Contraseña:</label>
                    <input type="password" name="password" id="password" required>
                </div>
                ` : ''}
                <div class="form-group">
                    <label for="rol">Rol:</label>
                    <select name="rol" id="rol" required>
                        <option value="1">Paciente</option>
                        <option value="2">Médico</option>
                        <option value="3">Administrador</option>
                    </select>
                </div>
                <div class="form-actions">
                    <button type="button" onclick="guardarUsuario(this.form)" class="btn-primary">Guardar</button>
                    <button type="button" onclick="cerrarModal()" class="btn-secondary">Cancelar</button>
                </div>
            </form>
        </div>
    `;
    document.body.appendChild(modal);

    if (usuarioId) {
        cargarDatosUsuario(usuarioId);
    }
}

function aplicarFiltros() {
    const rolSeleccionado = document.getElementById('filtroRol').value;
    const estadoSeleccionado = document.getElementById('filtroEstado').value;
    
    const filas = document.querySelectorAll('#tablaUsuarios tbody tr');
    filas.forEach(fila => {
        const rol = fila.children[3].textContent.trim();
        const estado = fila.children[5].textContent.trim();
        
        const cumpleRol = !rolSeleccionado || rol === rolSeleccionado;
        const cumpleEstado = !estadoSeleccionado || 
            (estadoSeleccionado === '1' && estado === 'Activo') ||
            (estadoSeleccionado === '0' && estado === 'Inactivo');
        
        fila.style.display = cumpleRol && cumpleEstado ? '' : 'none';
    });
}

function formatearFecha(fecha) {
    if (!fecha) return '';
    const f = new Date(fecha);
    return f.toLocaleString('es-ES');
}

// Funciones para el CRUD
function cargarDatosUsuario(id) {
    fetch(`/LaBendecida/api/usuarios/${id}`)
        .then(response => response.json())
        .then(usuario => {
            document.getElementById('nombre').value = usuario.nombre || '';
            document.getElementById('apellido').value = usuario.apellido || '';
            document.getElementById('email').value = usuario.email || '';
            document.getElementById('rol').value = usuario.rol || '1';
        })
        .catch(error => console.error('Error:', error));
}

function guardarUsuario(form) {
    const id = form.querySelector('[name="id"]').value;
    const url = id ? `/LaBendecida/api/usuarios/${id}` : '/LaBendecida/api/usuarios';
    const method = id ? 'PUT' : 'POST';

    // Crear objeto con los datos del formulario
    const data = {
        nombre: form.querySelector('[name="nombre"]').value,
        apellido: form.querySelector('[name="apellido"]').value,
        email: form.querySelector('[name="email"]').value,
        rol: form.querySelector('[name="rol"]').value
    };

    // Si es nuevo usuario, agregar contraseña
    if (!id) {
        data.password = form.querySelector('[name="password"]').value;
    }

    fetch(url, {
        method: method,
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded'
        },
        body: new URLSearchParams(data).toString()
    })
    .then(response => {
        if (response.ok) {
            cerrarModal();
            location.reload();
        } else {
            return response.text().then(text => {
                throw new Error(text || 'Error al guardar usuario');
            });
        }
    })
    .catch(error => {
        console.error('Error:', error);
        alert(error.message);
    });
}

function eliminarUsuario(id) {
    if (confirm('¿Está seguro de eliminar este usuario?')) {
        fetch(`/LaBendecida/api/usuarios/${id}`, {
            method: 'DELETE'
        })
        .then(response => {
            if (response.ok) {
                location.reload();
            } else {
                throw new Error('Error al eliminar usuario');
            }
        })
        .catch(error => {
            console.error('Error:', error);
            alert(error.message);
        });
    }
}

function cerrarModal() {
    const modal = document.querySelector('.modal');
    if (modal) {
        modal.remove();
    }
}

function editarUsuario(id) {
    mostrarModal(id);
} 
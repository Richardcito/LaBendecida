document.addEventListener('DOMContentLoaded', function() {
    // Inicializar eventos
    document.getElementById('btnNuevoMedico').addEventListener('click', () => mostrarModal());
    document.getElementById('filtroEspecialidad').addEventListener('change', aplicarFiltros);
    document.getElementById('filtroEstado').addEventListener('change', aplicarFiltros);
});

function mostrarModal(medicoId = null) {
    const modal = document.createElement('div');
    modal.className = 'modal';
    modal.innerHTML = `
        <div class="modal-content">
            <h2>${medicoId ? 'Editar Médico' : 'Nuevo Médico'}</h2>
            <form id="formMedico">
                <input type="hidden" name="id" value="${medicoId || ''}">
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
                ${!medicoId ? `
                <div class="form-group">
                    <label for="password">Contraseña:</label>
                    <input type="password" name="password" id="password" required>
                </div>
                ` : ''}
                <div class="form-group">
                    <label for="especialidad">Especialidad:</label>
                    <select name="especialidad" id="especialidad" required>
                        <option value="">Seleccione una especialidad</option>
                        <!-- Las opciones se cargarán dinámicamente -->
                    </select>
                </div>
                <div class="form-actions">
                    <button type="button" onclick="guardarMedico(this.form)" class="btn-primary">Guardar</button>
                    <button type="button" onclick="cerrarModal()" class="btn-secondary">Cancelar</button>
                </div>
            </form>
        </div>
    `;
    document.body.appendChild(modal);

    // Cargar especialidades
    cargarEspecialidades();

    if (medicoId) {
        cargarDatosMedico(medicoId);
    }
}

function cargarEspecialidades() {
    fetch('/LaBendecida/api/especialidades')
        .then(response => response.json())
        .then(especialidades => {
            const select = document.getElementById('especialidad');
            especialidades.forEach(especialidad => {
                const option = document.createElement('option');
                option.value = especialidad.id;
                option.textContent = especialidad.nombre;
                select.appendChild(option);
            });
        })
        .catch(error => console.error('Error:', error));
}

function guardarMedico(form) {
    const id = form.querySelector('[name="id"]').value;
    const url = id ? `/LaBendecida/api/medicos/${id}` : '/LaBendecida/api/medicos';
    const method = id ? 'PUT' : 'POST';

    // Crear objeto con los datos del formulario
    const data = {
        nombre: form.querySelector('[name="nombre"]').value,
        apellido: form.querySelector('[name="apellido"]').value,
        email: form.querySelector('[name="email"]').value,
        especialidad: form.querySelector('[name="especialidad"]').value
    };

    // Si es nuevo médico, agregar contraseña
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
                throw new Error(text || 'Error al guardar médico');
            });
        }
    })
    .catch(error => {
        console.error('Error:', error);
        alert(error.message);
    });
}

function cargarDatosMedico(id) {
    fetch(`/LaBendecida/api/medicos/${id}`)
        .then(response => response.json())
        .then(medico => {
            document.getElementById('nombre').value = medico.nombre || '';
            document.getElementById('apellido').value = medico.apellido || '';
            document.getElementById('email').value = medico.email || '';
            document.getElementById('especialidad').value = medico.especialidad_id || '';
        })
        .catch(error => console.error('Error:', error));
}

function eliminarMedico(id) {
    if (confirm('¿Está seguro de eliminar este médico?')) {
        fetch(`/LaBendecida/api/medicos/${id}`, {
            method: 'DELETE'
        })
        .then(response => {
            if (response.ok) {
                location.reload();
            } else {
                throw new Error('Error al eliminar médico');
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

function aplicarFiltros() {
    const especialidadSeleccionada = document.getElementById('filtroEspecialidad').value;
    const estadoSeleccionado = document.getElementById('filtroEstado').value;
    
    const filas = document.querySelectorAll('#tablaMedicos tbody tr');
    filas.forEach(fila => {
        const especialidad = fila.children[3].textContent.trim();
        const estado = fila.children[5].textContent.trim();
        
        const cumpleEspecialidad = !especialidadSeleccionada || 
            especialidad === document.querySelector(`#filtroEspecialidad option[value="${especialidadSeleccionada}"]`).textContent;
        const cumpleEstado = !estadoSeleccionado || 
            (estadoSeleccionado === '1' && estado === 'Activo') ||
            (estadoSeleccionado === '0' && estado === 'Inactivo');
        
        fila.style.display = cumpleEspecialidad && cumpleEstado ? '' : 'none';
    });
} 
document.addEventListener('DOMContentLoaded', function() {
    const filterForm = document.querySelector('.filter-container');
    const appointmentsTable = document.querySelector('.appointments-table tbody');

    // Función para cargar citas
    function loadAppointments(filters = {}) {
        // Aquí iría la llamada al backend para obtener las citas
        fetch('/api/citas?' + new URLSearchParams(filters))
            .then(response => response.json())
            .then(data => {
                // Actualizar la tabla con los datos
                updateAppointmentsTable(data);
            })
            .catch(error => console.error('Error:', error));
    }

    // Función para actualizar la tabla de citas
    function updateAppointmentsTable(appointments) {
        appointmentsTable.innerHTML = appointments.map(appointment => `
            <tr>
                <td>${appointment.fecha}</td>
                <td>${appointment.hora}</td>
                <td>${appointment.paciente}</td>
                <td>${appointment.motivo}</td>
                <td><span class="status ${appointment.estado.toLowerCase()}">${appointment.estado}</span></td>
                <td>
                    <button class="btn-action" onclick="viewAppointment(${appointment.id})">
                        <i class="fas fa-eye"></i>
                    </button>
                    <button class="btn-action" onclick="editAppointment(${appointment.id})">
                        <i class="fas fa-edit"></i>
                    </button>
                </td>
            </tr>
        `).join('');
    }

    // Event listeners para filtros
    filterForm.addEventListener('submit', function(e) {
        e.preventDefault();
        const filters = {
            status: document.getElementById('filterStatus').value,
            date: document.getElementById('filterDate').value
        };
        loadAppointments(filters);
    });

    // Cargar citas iniciales
    loadAppointments();
}); 
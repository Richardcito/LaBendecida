document.addEventListener('DOMContentLoaded', function() {
    // Actualizar fecha actual
    const fechaActual = document.getElementById('fecha-actual');
    if (fechaActual) {
        fechaActual.textContent = new Date().toLocaleDateString('es-ES', {
            weekday: 'long',
            year: 'numeric',
            month: 'long',
            day: 'numeric'
        });
    }

    // Manejar navegación del sidebar
    const sidebarLinks = document.querySelectorAll('.sidebar-nav a');
    sidebarLinks.forEach(link => {
        link.addEventListener('click', function(e) {
            sidebarLinks.forEach(l => l.parentElement.classList.remove('active'));
            this.parentElement.classList.add('active');
        });
    });
}); 
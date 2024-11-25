document.addEventListener('DOMContentLoaded', function() {
    const scheduleGrid = document.querySelector('.schedule-grid');
    const scheduleForm = document.getElementById('scheduleForm');
    let currentWeek = new Date();

    // Función para actualizar el rango de la semana
    function updateWeekRange() {
        const weekStart = getWeekStart(currentWeek);
        const weekEnd = new Date(weekStart);
        weekEnd.setDate(weekEnd.getDate() + 6);
        
        document.getElementById('week-range').textContent = `
            ${weekStart.toLocaleDateString()} - ${weekEnd.toLocaleDateString()}
        `;
    }

    // Función para obtener el inicio de la semana
    function getWeekStart(date) {
        const start = new Date(date);
        start.setDate(start.getDate() - start.getDay() + 1);
        return start;
    }

    // Función para renderizar la grilla de horarios
    function renderScheduleGrid() {
        const weekStart = getWeekStart(currentWeek);
        scheduleGrid.innerHTML = '';

        for (let i = 0; i < 7; i++) {
            const currentDate = new Date(weekStart);
            currentDate.setDate(currentDate.getDate() + i);
            
            const dayElement = document.createElement('div');
            dayElement.className = 'schedule-day';
            if (currentDate.toDateString() === new Date().toDateString()) {
                dayElement.classList.add('today');
            }
            
            dayElement.innerHTML = `
                <h3>${currentDate.toLocaleDateString('es-ES', { weekday: 'long' })}</h3>
                <p>${currentDate.toLocaleDateString()}</p>
                <div class="schedule-slots"></div>
            `;
            
            scheduleGrid.appendChild(dayElement);
        }

        // Cargar horarios existentes
        loadSchedule();
    }

    // Event listeners para navegación de semanas
    document.querySelector('.btn-prev-week').addEventListener('click', () => {
        currentWeek.setDate(currentWeek.getDate() - 7);
        updateWeekRange();
        renderScheduleGrid();
    });

    document.querySelector('.btn-next-week').addEventListener('click', () => {
        currentWeek.setDate(currentWeek.getDate() + 7);
        updateWeekRange();
        renderScheduleGrid();
    });

    // Event listener para el formulario de horarios
    scheduleForm.addEventListener('submit', function(e) {
        e.preventDefault();
        // Aquí iría la lógica para guardar el horario
    });

    // Inicialización
    updateWeekRange();
    renderScheduleGrid();
}); 
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Gestión de Horarios - Dashboard Médico</title>
    <!-- CSS Principal del dashboard -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/dashboard-medico/main.css">
    <!-- CSS específico de horarios -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/dashboard-medico/horarios.css">
    <link rel="icon" href="${pageContext.request.contextPath}/images/logo la bendecida.ico" type="image/x-icon">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
</head>
<body>
    <div class="dashboard-container">
        <!-- Sidebar -->
        <aside class="sidebar">
            <div class="logo">
                <img src="${pageContext.request.contextPath}/images/logo la bendecida.png" alt="Logo">
            </div>
            <nav class="sidebar-nav">
                <ul>
                    <li>
                        <a href="${pageContext.request.contextPath}/dashboard-medico">
                            <i class="fas fa-home"></i> Panel Principal
                        </a>
                    </li>
                    <li>
                        <a href="${pageContext.request.contextPath}/dashboard-medico/citas">
                            <i class="fas fa-calendar-alt"></i> Citas
                        </a>
                    </li>
                    <li class="active">
                        <a href="${pageContext.request.contextPath}/dashboard-medico/horarios">
                            <i class="fas fa-clock"></i> Horarios
                        </a>
                    </li>
                    <li>
                        <a href="${pageContext.request.contextPath}/dashboard-medico/perfil">
                            <i class="fas fa-user-circle"></i> Mi Perfil
                        </a>
                    </li>
                    <li>
                        <a href="${pageContext.request.contextPath}/logout">
                            <i class="fas fa-sign-out-alt"></i> Cerrar Sesión
                        </a>
                    </li>
                </ul>
            </nav>
        </aside>

        <main class="main-content">
            <header class="dashboard-header">
                <h1>Gestión de Horarios</h1>
                <div class="fecha-actual">
                    <i class="far fa-calendar"></i>
                    <span id="fecha-actual"></span>
                </div>
            </header>

            <!-- Vista de Horario Semanal -->
            <section class="weekly-schedule">
                <div class="schedule-header">
                    <button class="btn-nav"><i class="fas fa-chevron-left"></i></button>
                    <h2>Semana del <span id="week-range"></span></h2>
                    <button class="btn-nav"><i class="fas fa-chevron-right"></i></button>
                </div>
                
                <div class="schedule-grid">
                    <div class="time-column">
                        <div class="time-slot">8:00 AM</div>
                        <div class="time-slot">9:00 AM</div>
                        <div class="time-slot">10:00 AM</div>
                        <div class="time-slot">11:00 AM</div>
                        <div class="time-slot">12:00 PM</div>
                        <div class="time-slot">1:00 PM</div>
                        <div class="time-slot">2:00 PM</div>
                        <div class="time-slot">3:00 PM</div>
                        <div class="time-slot">4:00 PM</div>
                        <div class="time-slot">5:00 PM</div>
                    </div>
                    
                    <div class="day-column">
                        <div class="day-header">Lunes</div>
                        <div class="appointments-container">
                            <!-- Las citas se agregarán dinámicamente -->
                        </div>
                    </div>
                    
                    <div class="day-column">
                        <div class="day-header">Martes</div>
                        <div class="appointments-container"></div>
                    </div>
                    
                    <div class="day-column">
                        <div class="day-header">Miércoles</div>
                        <div class="appointments-container"></div>
                    </div>
                    
                    <div class="day-column">
                        <div class="day-header">Jueves</div>
                        <div class="appointments-container"></div>
                    </div>
                    
                    <div class="day-column">
                        <div class="day-header">Viernes</div>
                        <div class="appointments-container"></div>
                    </div>
                    
                    <div class="day-column">
                        <div class="day-header">Sábado</div>
                        <div class="appointments-container"></div>
                    </div>
                    
                    <div class="day-column">
                        <div class="day-header">Domingo</div>
                        <div class="appointments-container"></div>
                    </div>
                </div>
            </section>

            <!-- Configuración de Horarios -->
            <section class="schedule-config">
                <div class="config-header">
                    <h3><i class="fas fa-cog"></i> Configuración de Horarios</h3>
                    <button class="btn-primary"><i class="fas fa-plus"></i> Nuevo Horario</button>
                </div>
                
                <div class="config-grid">
                    <div class="config-card">
                        <div class="day-label">Lunes a Viernes</div>
                        <div class="time-range">8:00 AM - 5:00 PM</div>
                        <div class="break-time">Almuerzo: 1:00 PM - 2:00 PM</div>
                        <div class="card-actions">
                            <button class="btn-edit"><i class="fas fa-edit"></i></button>
                            <button class="btn-delete"><i class="fas fa-trash"></i></button>
                        </div>
                    </div>
                    
                    <div class="config-card">
                        <div class="day-label">Sábado</div>
                        <div class="time-range">8:00 AM - 1:00 PM</div>
                        <div class="card-actions">
                            <button class="btn-edit"><i class="fas fa-edit"></i></button>
                            <button class="btn-delete"><i class="fas fa-trash"></i></button>
                        </div>
                    </div>
                </div>
            </section>
        </main>
    </div>

    <script>
        // Actualizar fecha actual
        document.getElementById('fecha-actual').textContent = new Date().toLocaleDateString('es-ES', {
            weekday: 'long',
            year: 'numeric',
            month: 'long',
            day: 'numeric'
        });

        // Actualizar rango de semana
        const today = new Date();
        const firstDay = new Date(today.setDate(today.getDate() - today.getDay() + 1));
        const lastDay = new Date(today.setDate(today.getDate() - today.getDay() + 7));
        
        document.getElementById('week-range').textContent = `${firstDay.getDate()} - ${lastDay.getDate()} de ${lastDay.toLocaleString('es-ES', { month: 'long' })}`;
    </script>
</body>
</html> 
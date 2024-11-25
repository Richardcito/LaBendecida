<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Dashboard Médico - Clínica La Bendecida</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/dashboard-medico.css">
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
                    <li class="active">
                        <a href="${pageContext.request.contextPath}/dashboard-medico"><i class="fas fa-home"></i> Panel Principal</a>
                    </li>
                    <li>
                        <a href="${pageContext.request.contextPath}/dashboard-medico/citas"><i class="fas fa-calendar-alt"></i> Citas</a>
                    </li>
                    <li>
                        <a href="${pageContext.request.contextPath}/dashboard-medico/horarios"><i class="fas fa-clock"></i> Horarios</a>
                    </li>
                    <li>
                        <a href="${pageContext.request.contextPath}/dashboard-medico/perfil"><i class="fas fa-user-circle"></i> Mi Perfil</a>
                    </li>
                    <li>
                        <a href="${pageContext.request.contextPath}/logout"><i class="fas fa-sign-out-alt"></i> Cerrar Sesión</a>
                    </li>
                </ul>
            </nav>
        </aside>

        <!-- Main Content -->
        <main class="main-content">
            <header class="dashboard-header">
                <h1>Bienvenido, Dr. ${sessionScope.nombre}</h1>
                <div class="fecha-actual">
                    <i class="far fa-calendar"></i>
                    <span id="fecha-actual"></span>
                </div>
            </header>

            <!-- Resumen de Estadísticas -->
            <section class="stats-container">
                <div class="stat-card">
                    <i class="fas fa-calendar-check"></i>
                    <div class="stat-info">
                        <h3>Citas Hoy</h3>
                        <p>8</p>
                    </div>
                </div>
                <div class="stat-card">
                    <i class="fas fa-user-clock"></i>
                    <div class="stat-info">
                        <h3>Pendientes</h3>
                        <p>5</p>
                    </div>
                </div>
                <div class="stat-card">
                    <i class="fas fa-check-circle"></i>
                    <div class="stat-info">
                        <h3>Completadas</h3>
                        <p>3</p>
                    </div>
                </div>
                <div class="stat-card">
                    <i class="fas fa-user-md"></i>
                    <div class="stat-info">
                        <h3>Total Pacientes</h3>
                        <p>150</p>
                    </div>
                </div>
            </section>

            <!-- Próximas Citas -->
            <section class="appointments-section">
                <h2>Próximas Citas</h2>
                <div class="table-responsive">
                    <table class="appointments-table">
                        <thead>
                            <tr>
                                <th>Hora</th>
                                <th>Paciente</th>
                                <th>Motivo</th>
                                <th>Estado</th>
                                <th>Acciones</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td>09:00 AM</td>
                                <td>Juan Pérez</td>
                                <td>Consulta General</td>
                                <td><span class="status pending">Pendiente</span></td>
                                <td>
                                    <button class="btn-action"><i class="fas fa-eye"></i></button>
                                    <button class="btn-action"><i class="fas fa-edit"></i></button>
                                </td>
                            </tr>
                            <!-- Más filas de citas... -->
                        </tbody>
                    </table>
                </div>
            </section>

            <!-- Horario del Día -->
            <section class="schedule-section">
                <h2>Mi Horario de Hoy</h2>
                <div class="schedule-timeline">
                    <!-- Aquí iría una representación visual del horario -->
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

        // Marcar el ítem activo en el menú
        document.addEventListener('DOMContentLoaded', function() {
            const currentPath = window.location.pathname;
            const menuItems = document.querySelectorAll('.sidebar-nav li');
            
            menuItems.forEach(item => {
                item.classList.remove('active');
                const link = item.querySelector('a');
                if (link.getAttribute('href') === currentPath) {
                    item.classList.add('active');
                }
            });

            // Si estamos en la página principal del dashboard
            if (currentPath.endsWith('/dashboard-medico')) {
                menuItems[0].classList.add('active');
            }
        });
    </script>
</body>
</html> 
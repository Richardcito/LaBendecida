<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Dashboard Administrador - Clínica La Bendecida</title>
    
    <!-- CSS -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/dashboard-admin/dashboard-admin.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
    
    <!-- Favicon -->
    <link rel="icon" href="${pageContext.request.contextPath}/images/logo la bendecida.ico" type="image/x-icon">
</head>
<body>
    <div class="dashboard-container">
        <!-- Sidebar -->
        <aside class="sidebar">
            <div class="logo">
                <img src="${pageContext.request.contextPath}/images/logo la bendecida.png" alt="Logo Clínica">
                <h2>La Bendecida</h2>
            </div>
            
            <nav class="sidebar-nav">
                <ul>
                    <li class="active">
                        <a href="${pageContext.request.contextPath}/dashboard-admin">
                            <i class="fas fa-home"></i>
                            <span>Panel Principal</span>
                        </a>
                    </li>
                    <li>
                        <a href="${pageContext.request.contextPath}/dashboard-admin/usuarios">
                            <i class="fas fa-users"></i>
                            <span>Usuarios</span>
                        </a>
                    </li>
                    <li>
                        <a href="${pageContext.request.contextPath}/dashboard-admin/medicos">
                            <i class="fas fa-user-md"></i>
                            <span>Médicos</span>
                        </a>
                    </li>
                    <li>
                        <a href="${pageContext.request.contextPath}/dashboard-admin/especialidades">
                            <i class="fas fa-stethoscope"></i>
                            <span>Especialidades</span>
                        </a>
                    </li>
                    <li>
                        <a href="${pageContext.request.contextPath}/dashboard-admin/citas">
                            <i class="fas fa-calendar-check"></i>
                            <span>Citas</span>
                        </a>
                    </li>
                    <li>
                        <a href="${pageContext.request.contextPath}/dashboard-admin/roles">
                            <i class="fas fa-user-shield"></i>
                            <span>Roles</span>
                        </a>
                    </li>
                </ul>
            </nav>
        </aside>

        <!-- Contenido Principal -->
        <main class="main-content">
            <!-- Header -->
            <header class="dashboard-header">
                <div class="header-left">
                    <h1>Panel de Administración</h1>
                    <p id="fecha-actual"></p>
                </div>
                <div class="header-right">
                    <div class="admin-profile">
                        <span>Administrador</span>
                        <img src="${pageContext.request.contextPath}/images/admin-avatar.png" alt="Admin Avatar">
                    </div>
                </div>
            </header>

            <!-- Contenido del Dashboard -->
            <section class="dashboard-content">
                <!-- Tarjetas de Estadísticas -->
                <div class="stats-container">
                    <div class="stat-card">
                        <i class="fas fa-users"></i>
                        <div class="stat-info">
                            <h3>Total Usuarios</h3>
                            <p>${totalUsuarios != null ? totalUsuarios : '0'}</p>
                        </div>
                    </div>
                    <div class="stat-card">
                        <i class="fas fa-user-md"></i>
                        <div class="stat-info">
                            <h3>Total Médicos</h3>
                            <p>${totalMedicos != null ? totalMedicos : '0'}</p>
                        </div>
                    </div>
                    <div class="stat-card">
                        <i class="fas fa-calendar-check"></i>
                        <div class="stat-info">
                            <h3>Citas Hoy</h3>
                            <p>${citasHoy != null ? citasHoy : '0'}</p>
                        </div>
                    </div>
                    <div class="stat-card">
                        <i class="fas fa-stethoscope"></i>
                        <div class="stat-info">
                            <h3>Especialidades</h3>
                            <p>${totalEspecialidades != null ? totalEspecialidades : '0'}</p>
                        </div>
                    </div>
                </div>

                <!-- Sección de Actividad Reciente -->
                <div class="recent-activity">
                    <h2>Actividad Reciente</h2>
                    <div class="activity-container">
                        <!-- Usuarios Recientes -->
                        <div class="recent-users">
                            <h3>Usuarios Recientes</h3>
                            <div class="table-responsive">
                                <table>
                                    <thead>
                                        <tr>
                                            <th>Nombre</th>
                                            <th>Email</th>
                                            <th>Fecha Registro</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach items="${usuariosRecientes}" var="usuario">
                                            <tr>
                                                <td>${usuario.nombre} ${usuario.apellido}</td>
                                                <td>${usuario.email}</td>
                                                <td>
                                                    <c:if test="${usuario.fechaRegistro != null}">
                                                        ${usuario.fechaRegistro}
                                                    </c:if>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                        </div>

                        <!-- Citas Recientes -->
                        <div class="recent-appointments">
                            <h3>Citas Recientes</h3>
                            <div class="table-responsive">
                                <table>
                                    <thead>
                                        <tr>
                                            <th>Paciente</th>
                                            <th>Médico</th>
                                            <th>Fecha</th>
                                            <th>Estado</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach items="${citasRecientes}" var="cita">
                                            <tr>
                                                <td>${cita.nombrePaciente}</td>
                                                <td>${cita.nombreMedico}</td>
                                                <td>${cita.fecha} ${cita.hora}</td>
                                                <td>
                                                    <span class="status-badge ${cita.estado.toLowerCase()}">
                                                        ${cita.estado}
                                                    </span>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </section>
        </main>
    </div>

    <!-- Scripts -->
    <script>
        // Actualizar fecha actual
        document.getElementById('fecha-actual').textContent = new Date().toLocaleDateString('es-ES', {
            weekday: 'long',
            year: 'numeric',
            month: 'long',
            day: 'numeric'
        });
    </script>
</body>
</html> 
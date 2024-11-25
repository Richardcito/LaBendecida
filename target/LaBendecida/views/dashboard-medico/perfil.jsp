<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Mi Perfil - Dashboard Médico</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/dashboard-medico/main.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/dashboard-medico/perfil.css">
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
                        <a href="${pageContext.request.contextPath}/dashboard-medico"><i class="fas fa-home"></i> Panel Principal</a>
                    </li>
                    <li>
                        <a href="${pageContext.request.contextPath}/dashboard-medico/citas"><i class="fas fa-calendar-alt"></i> Citas</a>
                    </li>
                    <li>
                        <a href="${pageContext.request.contextPath}/dashboard-medico/horarios"><i class="fas fa-clock"></i> Horarios</a>
                    </li>
                    <li class="active">
                        <a href="${pageContext.request.contextPath}/dashboard-medico/perfil"><i class="fas fa-user-circle"></i> Mi Perfil</a>
                    </li>
                    <li>
                        <a href="${pageContext.request.contextPath}/logout"><i class="fas fa-sign-out-alt"></i> Cerrar Sesión</a>
                    </li>
                </ul>
            </nav>
        </aside>

        <main class="main-content">
            <header class="dashboard-header">
                <h1>Mi Perfil</h1>
            </header>

            <div class="profile-container">
                <!-- Información Principal -->
                <section class="profile-main">
                    <div class="profile-header">
                        <div class="profile-image">
                            <img src="${pageContext.request.contextPath}/images/default-profile.png" alt="Foto de perfil">
                            <button class="btn-change-photo"><i class="fas fa-camera"></i></button>
                        </div>
                        <div class="profile-info">
                            <h2>Dr. ${sessionScope.nombre}</h2>
                            <p class="specialty"><i class="fas fa-stethoscope"></i> ${sessionScope.especialidad}</p>
                            <p class="license"><i class="fas fa-id-card"></i> CMP: ${medico.numeroColegiado}</p>
                        </div>
                    </div>
                </section>

                <!-- Estadísticas Rápidas -->
                <section class="profile-stats">
                    <div class="stat-item">
                        <i class="fas fa-calendar-check"></i>
                        <div class="stat-details">
                            <span class="stat-value">150</span>
                            <span class="stat-label">Citas Totales</span>
                        </div>
                    </div>
                    <div class="stat-item">
                        <i class="fas fa-star"></i>
                        <div class="stat-details">
                            <span class="stat-value">4.8</span>
                            <span class="stat-label">Calificación</span>
                        </div>
                    </div>
                    <div class="stat-item">
                        <i class="fas fa-user-friends"></i>
                        <div class="stat-details">
                            <span class="stat-value">120</span>
                            <span class="stat-label">Pacientes</span>
                        </div>
                    </div>
                </section>

                <!-- Formulario de Edición -->
                <section class="profile-edit">
                    <h3><i class="fas fa-user-edit"></i> Información Personal</h3>
                    <form id="profileForm" class="profile-form">
                        <div class="form-grid">
                            <div class="form-group">
                                <label>Número de Colegiado:</label>
                                <input type="text" name="numeroColegiado" value="${medico.numeroColegiado}" readonly>
                            </div>
                            <div class="form-group">
                                <label>Email:</label>
                                <input type="email" name="email" value="${medico.email}" required>
                            </div>
                            <div class="form-group">
                                <label>Teléfono:</label>
                                <input type="tel" name="telefono" value="${medico.telefono}" required>
                            </div>
                        </div>

                        <h3><i class="fas fa-lock"></i> Cambiar Contraseña</h3>
                        <div class="form-grid">
                            <div class="form-group">
                                <label>Contraseña Actual:</label>
                                <input type="password" name="currentPassword">
                            </div>
                            <div class="form-group">
                                <label>Nueva Contraseña:</label>
                                <input type="password" name="newPassword">
                            </div>
                            <div class="form-group">
                                <label>Confirmar Contraseña:</label>
                                <input type="password" name="confirmPassword">
                            </div>
                        </div>

                        <div class="form-actions">
                            <button type="submit" class="btn-save"><i class="fas fa-save"></i> Guardar Cambios</button>
                            <button type="reset" class="btn-cancel"><i class="fas fa-times"></i> Cancelar</button>
                        </div>
                    </form>
                </section>
            </div>
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
    </script>
</body>
</html> 
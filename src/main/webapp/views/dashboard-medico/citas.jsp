<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Gestión de Citas - Dashboard Médico</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/dashboard-medico/main.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/dashboard-medico/citas.css">
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
                            <i class="fas fa-home"></i>
                            <span>Panel Principal</span>
                        </a>
                    </li>
                    <li class="active">
                        <a href="<%= request.getContextPath() %>/dashboard-medico/citas">
                            <i class="fas fa-calendar-alt"></i>
                            <span>Citas</span>
                        </a>
                    </li>
                    <li>
                        <a href="${pageContext.request.contextPath}/dashboard-medico/horarios">
                            <i class="fas fa-clock"></i>
                            <span>Horarios</span>
                        </a>
                    </li>
                    <li>
                        <a href="${pageContext.request.contextPath}/dashboard-medico/perfil">
                            <i class="fas fa-user-circle"></i>
                            <span>Mi Perfil</span>
                        </a>
                    </li>
                    <li>
                        <a href="${pageContext.request.contextPath}/logout">
                            <i class="fas fa-sign-out-alt"></i>
                            <span>Cerrar Sesión</span>
                        </a>
                    </li>
                </ul>
            </nav>
        </aside>

        <main class="main-content">
            <header class="dashboard-header">
                <h1>Gestión de Citas</h1>
                <div class="fecha-actual">
                    <i class="far fa-calendar"></i>
                    <span>${fecha}</span>
                </div>
            </header>

            <section class="filters-section">
                <div class="filters">
                    <select id="estadoFilter">
                        <option>Todos los estados</option>
                        <option>Pendiente</option>
                        <option>Confirmada</option>
                        <option>Cancelada</option>
                        <option>Completada</option>
                    </select>
                    <input type="date" id="fechaFilter">
                    <button onclick="aplicarFiltros()" class="btn-filter">
                        <i class="fas fa-filter"></i> Filtrar
                    </button>
                </div>
            </section>

            <section class="citas-table">
                <!-- Para debugging -->
                <c:if test="${empty citas}">
                    <p>No hay citas disponibles</p>
                </c:if>
                <c:if test="${not empty citas}">
                    <p>Número de citas encontradas: ${citas.size()}</p>
                </c:if>
                
                <table>
                    <thead>
                        <tr>
                            <th>Fecha</th>
                            <th>Hora</th>
                            <th>Paciente</th>
                            <th>Motivo</th>
                            <th>Estado</th>
                            <th>Acciones</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="cita" items="${citas}">
                            <tr>
                                <td>${cita.fecha}</td>
                                <td>${cita.hora} - ${cita.horaFin}</td>
                                <td>${cita.nombrePaciente} ${cita.apellidoPaciente}</td>
                                <td>${cita.motivo}</td>
                                <td>
                                    <span class="status ${cita.estado.toLowerCase()}">${cita.estado}</span>
                                </td>
                                <td>
                                    <div class="actions">
                                        <button onclick="actualizarEstado('${cita.id}', 'Confirmada')" 
                                                class="btn-action" title="Confirmar">
                                            <i class="fas fa-check"></i>
                                        </button>
                                        <button onclick="actualizarEstado('${cita.id}', 'Cancelada')" 
                                                class="btn-action" title="Cancelar">
                                            <i class="fas fa-times"></i>
                                        </button>
                                        <button onclick="actualizarEstado('${cita.id}', 'Completada')" 
                                                class="btn-action" title="Completar">
                                            <i class="fas fa-check-double"></i>
                                        </button>
                                    </div>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </section>
        </main>
    </div>

    <script>
    function aplicarFiltros() {
        const estado = document.getElementById('estadoFilter').value;
        const fecha = document.getElementById('fechaFilter').value;
        
        window.location.href = `${pageContext.request.contextPath}/dashboard-medico/citas?estado=${estado}&fecha=${fecha}`;
    }

    function actualizarEstado(citaId, nuevoEstado) {
        fetch('${pageContext.request.contextPath}/dashboard-medico/citas', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded',
            },
            body: `action=actualizarEstado&citaId=${citaId}&estado=${nuevoEstado}`
        })
        .then(response => response.json())
        .then(data => {
            if (data.success) {
                location.reload();
            } else {
                alert('Error al actualizar el estado de la cita');
            }
        })
        .catch(error => {
            console.error('Error:', error);
            alert('Error al actualizar el estado de la cita');
        });
    }
    </script>
</body>
</html> 
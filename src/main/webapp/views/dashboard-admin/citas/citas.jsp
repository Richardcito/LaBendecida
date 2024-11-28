<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Gestión de Citas - La Bendecida</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/dashboard-admin/citas/citas.css">
</head>
<body>
    <!-- Barra lateral -->
    <nav>
        <div class="logo-container">
            <img src="${pageContext.request.contextPath}/images/logo la bendecida.png" alt="Logo" class="logo">
            <h1>La Bendecida</h1>
        </div>
        
        <a href="${pageContext.request.contextPath}/dashboard-admin">
            <i class="fas fa-home"></i>
            <span>Panel Principal</span>
        </a>
        <a href="${pageContext.request.contextPath}/dashboard-admin/usuarios">
            <i class="fas fa-users"></i>
            <span>Usuarios</span>
        </a>
        <a href="${pageContext.request.contextPath}/dashboard-admin/medicos">
            <i class="fas fa-user-md"></i>
            <span>Médicos</span>
        </a>
        <a href="${pageContext.request.contextPath}/dashboard-admin/especialidades">
            <i class="fas fa-stethoscope"></i>
            <span>Especialidades</span>
        </a>
        <a href="${pageContext.request.contextPath}/dashboard-admin/citas" class="active">
            <i class="fas fa-calendar-check"></i>
            <span>Citas</span>
        </a>
        <a href="${pageContext.request.contextPath}/dashboard-admin/roles">
            <i class="fas fa-user-tag"></i>
            <span>Roles</span>
        </a>
    </nav>

    <!-- Contenido principal -->
    <main>
        <header>
            <h2>Panel de Administración</h2>
            <div class="admin-info">
                <span>Administrador</span>
                <img src="${pageContext.request.contextPath}/img/admin-avatar.png" alt="Admin Avatar">
            </div>
        </header>

        <section class="content">
            <div class="content-header">
                <h2>Gestión de Citas</h2>
                <button class="btn-add" onclick="mostrarModalCita()">
                    <i class="fas fa-plus"></i>
                    Nueva Cita
                </button>
            </div>

            <div class="table-container">
                <table>
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Paciente</th>
                            <th>Médico</th>
                            <th>Especialidad</th>
                            <th>Fecha</th>
                            <th>Hora</th>
                            <th>Estado</th>
                            <th>Acciones</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:if test="${empty citas}">
                            <tr>
                                <td colspan="8" style="text-align: center;">No hay citas registradas</td>
                            </tr>
                        </c:if>
                        <c:forEach var="cita" items="${citas}">
                            <tr>
                                <td>${cita.id}</td>
                                <td>${cita.nombrePaciente}</td>
                                <td>${cita.nombreMedico}</td>
                                <td>${cita.nombreEspecialidad}</td>
                                <td>${cita.fecha}</td>
                                <td>${cita.hora}</td>
                                <td>${cita.estado}</td>
                                <td class="actions">
                                    <button class="btn-action edit" onclick="editarCita('${cita.id}')" title="Editar">
                                        <i class="fas fa-edit"></i>
                                    </button>
                                    <button class="btn-action delete" onclick="eliminarCita('${cita.id}')" title="Eliminar">
                                        <i class="fas fa-trash"></i>
                                    </button>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </section>
    </main>

    <!-- Modal para agregar/editar cita -->
    <div id="modalCita" class="modal">
        <div class="modal-content">
            <h3>Nueva Cita</h3>
            <form id="citaForm">
                <div class="form-group">
                    <label for="paciente">Paciente:</label>
                    <select id="paciente" name="paciente" required>
                        <option value="">Seleccione un paciente</option>
                    </select>
                </div>
                <div class="form-group">
                    <label for="medico">Médico:</label>
                    <select id="medico" name="medico" required>
                        <option value="">Seleccione un médico</option>
                    </select>
                </div>
                <div class="form-group">
                    <label for="fecha">Fecha:</label>
                    <input type="date" id="fecha" name="fecha" required>
                </div>
                <div class="form-group">
                    <label for="hora">Hora:</label>
                    <input type="time" id="hora" name="hora" required>
                </div>
                <div class="form-group">
                    <label for="estado">Estado:</label>
                    <select id="estado" name="estado" required>
                        <option value="Pendiente">Pendiente</option>
                        <option value="Confirmada">Confirmada</option>
                        <option value="Cancelada">Cancelada</option>
                        <option value="Completada">Completada</option>
                    </select>
                </div>
                <div class="form-actions">
                    <button type="submit" class="btn-primary">
                        <i class="fas fa-save"></i>
                        Guardar
                    </button>
                    <button type="button" class="btn-secondary" onclick="cerrarModal()">
                        <i class="fas fa-times"></i>
                        Cancelar
                    </button>
                </div>
            </form>
        </div>
    </div>

    <script src="${pageContext.request.contextPath}/js/dashboard-admin/citas/citas.js"></script>
</body>
</html> 
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Gestión de Especialidades - La Bendecida</title>
    <!-- Estilos -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/dashboard-admin/especialidades/especialidades.css">
    <link rel="icon" href="${pageContext.request.contextPath}/images/logo la bendecida.ico" type="image/x-icon">
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
        <a href="${pageContext.request.contextPath}/dashboard-admin/especialidades" class="active">
            <i class="fas fa-stethoscope"></i>
            <span>Especialidades</span>
        </a>
        <a href="${pageContext.request.contextPath}/dashboard-admin/citas">
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
                <h2>Gestión de Especialidades</h2>
                <button class="btn-add" onclick="mostrarModalEspecialidad()">
                    <i class="fas fa-plus"></i>
                    Nueva Especialidad
                </button>
            </div>

            <div class="table-container">
                <table>
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Nombre</th>
                            <th>Descripción</th>
                            <th>Acciones</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:if test="${empty especialidades}">
                            <tr>
                                <td colspan="4" style="text-align: center;">No hay especialidades registradas</td>
                            </tr>
                        </c:if>
                        <c:forEach var="especialidad" items="${especialidades}">
                            <tr>
                                <td>${especialidad.id}</td>
                                <td>${especialidad.nombre}</td>
                                <td>${especialidad.descripcion}</td>
                                <td class="actions">
                                    <button class="btn-action edit" onclick="editarEspecialidad('${especialidad.id}')" title="Editar">
                                        <i class="fas fa-edit"></i>
                                    </button>
                                    <button class="btn-action delete" onclick="eliminarEspecialidad('${especialidad.id}')" title="Eliminar">
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

    <!-- Modal para agregar/editar especialidad -->
    <div id="modalEspecialidad" class="modal">
        <div class="modal-content">
            <h3>Nueva Especialidad</h3>
            <form id="especialidadForm">
                <div class="form-group">
                    <label for="nombre">Nombre:</label>
                    <input type="text" id="nombre" name="nombre" required>
                </div>
                <div class="form-group">
                    <label for="descripcion">Descripción:</label>
                    <textarea id="descripcion" name="descripcion" required></textarea>
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

    <script src="${pageContext.request.contextPath}/js/dashboard-admin/especialidades/especialidades.js"></script>
</body>
</html> 
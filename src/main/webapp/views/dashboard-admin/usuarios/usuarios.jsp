<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Gestión de Usuarios - La Bendecida</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/dashboard-admin/usuarios/usuarios.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
    <link rel="icon" href="${pageContext.request.contextPath}/images/logo la bendecida.ico" type="image/x-icon">
</head>
<body>
    <div class="dashboard-container">
        <!-- Sidebar -->
        <aside class="sidebar">
            <div class="logo">
                <img src="${pageContext.request.contextPath}/images/logo la bendecida.png" alt="Logo">
                <h2>La Bendecida</h2>
            </div>
            <nav class="sidebar-nav">
                <ul>
                    <li><a href="${pageContext.request.contextPath}/dashboard-admin"><i class="fas fa-home"></i>Panel Principal</a></li>
                    <li class="active"><a href="${pageContext.request.contextPath}/dashboard-admin/usuarios"><i class="fas fa-users"></i>Usuarios</a></li>
                    <li><a href="${pageContext.request.contextPath}/dashboard-admin/medicos"><i class="fas fa-user-md"></i>Médicos</a></li>
                    <li><a href="${pageContext.request.contextPath}/dashboard-admin/especialidades"><i class="fas fa-stethoscope"></i>Especialidades</a></li>
                    <li><a href="${pageContext.request.contextPath}/dashboard-admin/citas"><i class="fas fa-calendar-check"></i>Citas</a></li>
                    <li><a href="${pageContext.request.contextPath}/dashboard-admin/roles"><i class="fas fa-user-shield"></i>Roles</a></li>
                </ul>
            </nav>
        </aside>

        <!-- Contenido Principal -->
        <main class="main-content">
            <header class="dashboard-header">
                <h1>Panel de Administración</h1>
                <div class="user-info">
                    <span>Administrador</span>
                    <img src="${pageContext.request.contextPath}/images/Admin Avatar.png" alt="Admin Avatar">
                </div>
            </header>

            <section class="content">
                <div class="content-header">
                    <h2>Gestión de Usuarios</h2>
                    <button class="btn-primary" id="btnNuevoUsuario">
                        <i class="fas fa-plus"></i> Nuevo Usuario
                    </button>
                </div>

                <div class="filters">
                    <div class="filter-group">
                        <label for="filtroRol">Rol:</label>
                        <select id="filtroRol">
                            <option value="">Todos</option>
                            <option value="PACIENTE">Paciente</option>
                            <option value="MEDICO">Médico</option>
                            <option value="ADMIN">Administrador</option>
                        </select>
                    </div>
                    <div class="filter-group">
                        <label for="filtroEstado">Estado:</label>
                        <select id="filtroEstado">
                            <option value="">Todos</option>
                            <option value="1">Activo</option>
                            <option value="0">Inactivo</option>
                        </select>
                    </div>
                </div>

                <div class="table-container">
                    <table id="tablaUsuarios">
                        <thead>
                            <tr>
                                <th>ID</th>
                                <th>Nombre</th>
                                <th>Email</th>
                                <th>Rol</th>
                                <th>Fecha Registro</th>
                                <th>Estado</th>
                                <th>Acciones</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${usuarios}" var="usuario">
                                <tr>
                                    <td>${usuario.id}</td>
                                    <td>${usuario.nombre} ${usuario.apellido}</td>
                                    <td>${usuario.email}</td>
                                    <td>${usuario.rol}</td>
                                    <td>${usuario.fechaRegistro}</td>
                                    <td>
                                        <span class="status-badge ${usuario.estado ? 'active' : 'inactive'}">
                                            ${usuario.estado ? 'Activo' : 'Inactivo'}
                                        </span>
                                    </td>
                                    <td class="actions">
                                        <button class="btn-action edit" onclick="editarUsuario('${usuario.id}')">
                                            <i class="fas fa-edit"></i>
                                        </button>
                                        <button class="btn-action delete" onclick="eliminarUsuario('${usuario.id}')">
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
    </div>

    <script src="${pageContext.request.contextPath}/js/dashboard-admin/usuarios/usuarios.js"></script>
</body>
</html> 
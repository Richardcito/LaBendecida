<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Gestión de Roles - La Bendecida</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/dashboard-admin/roles/roles.css">
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
                    <li><a href="${pageContext.request.contextPath}/dashboard-admin/usuarios"><i class="fas fa-users"></i>Usuarios</a></li>
                    <li><a href="${pageContext.request.contextPath}/dashboard-admin/medicos"><i class="fas fa-user-md"></i>Médicos</a></li>
                    <li><a href="${pageContext.request.contextPath}/dashboard-admin/especialidades"><i class="fas fa-stethoscope"></i>Especialidades</a></li>
                    <li><a href="${pageContext.request.contextPath}/dashboard-admin/citas"><i class="fas fa-calendar-check"></i>Citas</a></li>
                    <li class="active"><a href="${pageContext.request.contextPath}/dashboard-admin/roles"><i class="fas fa-user-shield"></i>Roles</a></li>
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
                    <h2>Gestión de Roles</h2>
                    <button class="btn-primary" id="btnNuevoRol">
                        <i class="fas fa-plus"></i> Nuevo Rol
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
                            <c:forEach var="rol" items="${roles}">
                                <tr>
                                    <td>${rol.id}</td>
                                    <td>${rol.nombre}</td>
                                    <td>${rol.descripcion}</td>
                                    <td class="actions">
                                        <button class="btn-action edit" onclick="editarRol('${rol.id}', '${rol.nombre}', '${rol.descripcion}')" title="Editar">
                                            <i class="fas fa-edit"></i>
                                        </button>
                                        <button class="btn-action delete" onclick="eliminarRol('${rol.id}')" title="Eliminar">
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

    <!-- Modal para Nuevo/Editar Rol -->
    <div id="modalRol" class="modal">
        <div class="modal-content">
            <span class="close">&times;</span>
            <h2 id="modalTitle">Nuevo Rol</h2>
            <form id="formRol" method="POST">
                <input type="hidden" id="rolId" name="rolId">
                <div class="form-group">
                    <label for="nombre">Nombre:</label>
                    <input type="text" id="nombre" name="nombre" required>
                </div>
                <div class="form-group">
                    <label for="descripcion">Descripción:</label>
                    <textarea id="descripcion" name="descripcion" required></textarea>
                </div>
                <div class="form-actions">
                    <button type="submit" class="btn-guardar">Guardar</button>
                    <button type="button" class="btn-cancelar" onclick="cerrarModal()">Cancelar</button>
                </div>
            </form>
        </div>
    </div>

    <script src="${pageContext.request.contextPath}/js/dashboard-admin/roles/roles.js"></script>
</body>
</html> 
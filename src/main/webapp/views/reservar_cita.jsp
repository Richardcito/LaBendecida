<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="es">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Reservar Cita - Clínica La Bendecida</title>
    <link rel="icon" href="${pageContext.request.contextPath}/images/logo la bendecida.ico" type="image/x-icon" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/reservarcita.css" />
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css" />
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;600;700&display=swap" rel="stylesheet" />
  </head>
  <body>
    <header>
      <nav class="navbar">
        <div class="logo">
          <a href="${pageContext.request.contextPath}/inicio">
            <img src="${pageContext.request.contextPath}/images/logo la bendecida.png" alt="Clínica La Bendecida Logo"/>
          </a>
        </div>
        <ul class="nav-links">
          <li><a href="${pageContext.request.contextPath}/inicio">Inicio</a></li>
          <li><a href="${pageContext.request.contextPath}/servicios">Servicios</a></li>
          <li><a href="${pageContext.request.contextPath}/medicos">Médicos</a></li>
          <li><a href="${pageContext.request.contextPath}/sobre_nosotros">Sobre Nosotros</a></li>
          <li>
            <a href="${pageContext.request.contextPath}/reservar_cita" class="btn-reservar">Reservar Cita</a>
          </li>
        </ul>
        <div class="burger">
          <div class="line1"></div>
          <div class="line2"></div>
          <div class="line3"></div>
        </div>
      </nav>
    </header>

    <main>
      <h1>Reserva de Citas - Clínica La Bendecida</h1>
      <form id="formulario-reserva" class="formulario-reserva" action="${pageContext.request.contextPath}/reservar_cita" method="POST">
        <div class="form-group">
          <label for="especialidad">Especialidad</label>
          <select id="especialidad" name="especialidad" required>
            <option value="">Seleccione una especialidad</option>
            <c:forEach items="${especialidades}" var="especialidad">
              <option value="${especialidad.id}">${especialidad.nombre}</option>
            </c:forEach>
          </select>
        </div>
        <div class="form-group">
          <label for="medico">Médico</label>
          <select id="medico" name="medico" required disabled>
            <option value="">Seleccione un médico</option>
          </select>
        </div>
        <div class="form-group">
          <label for="fecha">Fecha</label>
          <input type="date" id="fecha" name="fecha" required disabled />
        </div>
        <div class="form-group">
          <label for="hora">Hora</label>
          <select id="hora" name="hora" required disabled>
            <option value="">Seleccione una hora</option>
          </select>
        </div>
        <div class="form-group">
          <label for="motivo">Motivo de la cita</label>
          <textarea id="motivo" name="motivo" placeholder="Describa brevemente el motivo de su cita"></textarea>
        </div>
        <button type="submit" class="btn-enviar">Reservar Cita</button>
      </form>
      <c:if test="${not empty mensaje}">
        <div id="mensaje" class="${mensaje.tipo}">${mensaje.texto}</div>
      </c:if>
    </main>

    <footer>
      <div class="footer-content">
        <div class="footer-section">
          <h3>Contacto</h3>
          <p><i class="fas fa-map-marker-alt"></i> Av. Angelica Gamarra</p>
          <p><i class="fas fa-phone"></i> (01) 543-3453</p>
          <p><i class="fas fa-envelope"></i> info@clinicalabendicion.com</p>
        </div>
        <div class="footer-section">
          <h3>Síguenos</h3>
          <div class="social-icons">
            <a href="#"><i class="fab fa-facebook"></i></a>
            <a href="#"><i class="fab fa-twitter"></i></a>
            <a href="#"><i class="fab fa-instagram"></i></a>
          </div>
        </div>
        <div class="footer-section">
          <h3>Horario</h3>
          <p>Lunes a Viernes: 8:00 AM - 8:00 PM</p>
          <p>Sábados: 9:00 AM - 2:00 PM</p>
          <p>Domingos: Cerrado</p>
        </div>
      </div>
      <div class="footer-bottom">
        <p>&copy; 2024 Clínica La Bendecida. Todos los derechos reservados.</p>
      </div>
    </footer>

    <script src="${pageContext.request.contextPath}/js/reservarcita.js"></script>
  </body>
</html>
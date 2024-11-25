<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="es">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Clínica La Bendecida/Médicos</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/medicos.css" />
    <link rel="icon" href="${pageContext.request.contextPath}/images/logo la bendecida.ico" type="image/x-icon" />
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
          <li><a href="${pageContext.request.contextPath}/sobre-nosotros">Sobre nosotros</a></li>
          <li>
            <a href="${pageContext.request.contextPath}/login-registro" id="reservarCitaLink" class="btn-reservar">
              Reservar Cita
            </a>
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
      <section id="medicos" class="medicos">
        <h2>Nuestros Médicos</h2>
        <div class="medicos-container">
          <div class="medico-card">
            <img src="${pageContext.request.contextPath}/images/medico1.jpg" alt="Dr. Juan Pérez" />
            <h3>Dr. Juan Pérez</h3>
            <p>Cardiólogo</p>
          </div>
          <div class="medico-card">
            <img src="${pageContext.request.contextPath}/images/medico2.jpg" alt="Dra. Ana Martínez" />
            <h3>Dra. Ana Martínez</h3>
            <p>Neuróloga</p>
          </div>
          <div class="medico-card">
            <img src="${pageContext.request.contextPath}/images/medico3.jpg" alt="Dr. Carlos Rodríguez" />
            <h3>Dr. Carlos Rodríguez</h3>
            <p>Pediatra</p>
          </div>
          <div class="medico-card">
            <img src="${pageContext.request.contextPath}/images/medico4.jpg" alt="Dra. María López" />
            <h3>Dra. María López</h3>
            <p>Dermatóloga</p>
          </div>
          <div class="medico-card">
            <img src="${pageContext.request.contextPath}/images/medico5.jpg" alt="Dr. Roberto Sánchez" />
            <h3>Dr. Roberto Sánchez</h3>
            <p>Traumatólogo</p>
          </div>
          <div class="medico-card">
            <img src="${pageContext.request.contextPath}/images/medico6.jpg" alt="Dra. Laura García" />
            <h3>Dra. Laura García</h3>
            <p>Ginecóloga</p>
          </div>
        </div>
      </section>
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

    <script src="${pageContext.request.contextPath}/js/medicos.js"></script>
    <script>
      document.addEventListener("DOMContentLoaded", function () {
        const reservarCitaLink = document.getElementById("reservarCitaLink");

        reservarCitaLink.addEventListener("click", function (e) {
          e.preventDefault();
          fetch("${pageContext.request.contextPath}/verificar-sesion")
            .then((response) => response.json())
            .then((data) => {
              if (data.loggedin) {
                window.location.href = "${pageContext.request.contextPath}/reservar-cita";
              } else {
                window.location.href = "${pageContext.request.contextPath}/login-registro";
              }
            });
        });
      });
    </script>
  </body>
</html>

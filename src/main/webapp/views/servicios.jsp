<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="es">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Servicios - Clínica La Bendecida</title>
    <link rel="icon" href="${pageContext.request.contextPath}/images/logo la bendecida.ico" type="image/x-icon" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/servicios.css" />
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
          <li><a href="${pageContext.request.contextPath}/sobre-nosotros">Sobre Nosotros</a></li>
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
      <section id="especialidades" class="especialidades">
        <h2>Nuestras Especialidades</h2>
        <div class="especialidades-container">
          <div class="especialidad-card">
            <img src="${pageContext.request.contextPath}/images/cardiologia.jpg" alt="Cardiología" />
            <div class="especialidad-info">
              <h3>Cardiología</h3>
              <p>Cuidado experto para tu corazón. Nuestros cardiólogos están altamente capacitados para tratar enfermedades del corazón y del sistema circulatorio.</p>
            </div>
          </div>
          <div class="especialidad-card reverse">
            <img src="${pageContext.request.contextPath}/images/neurologia.jpg" alt="Neurología" />
            <div class="especialidad-info">
              <h3>Neurología</h3>
              <p>Especialistas en salud cerebral. Ofrecemos diagnóstico y tratamiento para una amplia gama de trastornos neurológicos.</p>
            </div>
          </div>
          <div class="especialidad-card">
            <img src="${pageContext.request.contextPath}/images/pediatria.jpg" alt="Pediatría" />
            <div class="especialidad-info">
              <h3>Pediatría</h3>
              <p>Atención integral para los más pequeños. Nuestros pediatras se dedican a la salud y el bienestar de los niños desde el nacimiento hasta la adolescencia.</p>
            </div>
          </div>
          <div class="especialidad-card reverse">
            <img src="${pageContext.request.contextPath}/images/ginecologia.jpg" alt="Ginecología" />
            <div class="especialidad-info">
              <h3>Ginecología</h3>
              <p>Salud y bienestar femenino. Ofrecemos servicios de salud reproductiva y ginecológica para mujeres de todas las edades.</p>
            </div>
          </div>
          <div class="especialidad-card">
            <img src="${pageContext.request.contextPath}/images/radiologia.jpg" alt="Radiología" />
            <div class="especialidad-info">
              <h3>Radiología</h3>
              <p>Diagnóstico por imágenes de alta precisión. Utilizamos tecnología avanzada para obtener imágenes detalladas del interior del cuerpo.</p>
            </div>
          </div>
          <div class="especialidad-card reverse">
            <img src="${pageContext.request.contextPath}/images/odontologia.jpg" alt="Odontología" />
            <div class="especialidad-info">
              <h3>Odontología</h3>
              <p>Cuidado dental completo para toda la familia. Nuestros dentistas ofrecen una amplia gama de servicios para mantener tu sonrisa saludable.</p>
            </div>
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

    <script src="${pageContext.request.contextPath}/js/servicios.js"></script>
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

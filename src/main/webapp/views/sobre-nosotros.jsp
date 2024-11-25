<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="es">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Sobre Nosotros - Clínica La Bendecida</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/sobre-nosotros.css" />
    <link rel="icon" href="${pageContext.request.contextPath}/images/logo la bendecida.ico" type="image/x-icon" />
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css" />
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;600;700&display=swap" rel="stylesheet" />
    <script src="https://unpkg.com/scrollreveal"></script>
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
            <a href="#" id="reservarCitaLink" class="btn-reservar">Reservar Cita</a>
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
      <section class="hero" style="background-image: url('${pageContext.request.contextPath}/images/slider-2.jpg');">
        <div class="hero-content">
          <h1>Sobre Nosotros</h1>
          <p>Cuidando de tu salud con excelencia y compasión</p>
        </div>
      </section>

      <section class="about-us">
        <div class="about-content">
          <h2>Nuestra Misión</h2>
          <p>
            En Clínica La Bendecida, nos dedicamos a proporcionar atención
            médica de la más alta calidad, centrada en el paciente y basada en
            la evidencia. Nuestro compromiso es mejorar la salud y el bienestar
            de nuestra comunidad a través de un servicio excepcional, tecnología
            avanzada y un equipo médico altamente calificado.
          </p>
        </div>
        <div class="about-image">
          <img src="${pageContext.request.contextPath}/images/portada.png" alt="Exterior de la Clínica La Bendecida" />
        </div>
      </section>

      <section class="our-team">
        <h2>Nuestro Equipo Médico</h2>
        <div class="team-grid">
          <div class="team-member">
            <img
              src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRhOxjmia4cLCDvwVTcVOsbvNM3OR5PgTkKSA&s"
              alt="Dr. Juan Pérez"
            />
            <h3>Dr. Juan Pérez</h3>
            <p>Cardiología</p>
          </div>
          <div class="team-member">
            <img
              src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRhOxjmia4cLCDvwVTcVOsbvNM3OR5PgTkKSA&s"
              alt="Dra. Ana Rodríguez"
            />
            <h3>Dra. Ana Rodríguez</h3>
            <p>Pediatría</p>
          </div>
          <div class="team-member">
            <img
              src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRhOxjmia4cLCDvwVTcVOsbvNM3OR5PgTkKSA&s"
              alt="Dr. Carlos López"
            />
            <h3>Dr. Carlos López</h3>
            <p>Oftalmología</p>
          </div>
          <div class="team-member">
            <img
              src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRhOxjmia4cLCDvwVTcVOsbvNM3OR5PgTkKSA&s"
              alt="Dra. María Martínez"
            />
            <h3>Dra. María Martínez</h3>
            <p>Ginecología</p>
          </div>
        </div>
      </section>

      <section class="testimonials">
        <h2>Lo que dicen nuestros pacientes</h2>
        <div class="testimonial-carousel">
          <div class="testimonial">
            <p>
              "El equipo médico de La Bendecida es excepcional. Siempre me
              siento bien atendido y en buenas manos."
            </p>
            <h4>- Pedro Gómez</h4>
          </div>
          <div class="testimonial">
            <p>
              "Gracias a la atención recibida en La Bendecida, mi calidad de
              vida ha mejorado significativamente."
            </p>
            <h4>- Laura Sánchez</h4>
          </div>
          <div class="testimonial">
            <p>
              "Los médicos son muy profesionales y el personal es amable.
              Recomiendo esta clínica a todos."
            </p>
            <h4>- Miguel Torres</h4>
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

    <script src="${pageContext.request.contextPath}/js/sobre-nosotros.js"></script>
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

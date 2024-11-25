<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="es">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Clínica La Bendecida/Inicio</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/inicio.css" />
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
          <li><a href="${pageContext.request.contextPath}/sobre-nosotros">Sobre Nosotros</a></li>
          <li>
            <a href="${pageContext.request.contextPath}/login-registro" id="reservarCitaLink" class="btn-reservar">
              Reservar Cita
            </a>
          </li>
          <div class="user-icons">
            <a href="${pageContext.request.contextPath}/login-registro" class="user-icon" title="Acceso Usuario">
              <i class="fas fa-user"></i>
            </a>
            <a href="${pageContext.request.contextPath}/login-medico" class="user-icon" title="Acceso Médicos">
              <i class="fas fa-user-md"></i>
            </a>
          </div>
        </ul>
      </nav>
    </header>

    <main>
      <section class="hero-slider">
        <div class="slider-container">
          <div class="slide active">
            <img src="${pageContext.request.contextPath}/images/slider-4.png" alt="Clínica MediCare" />
            <div class="slide-content">
              <h1>Bienvenido a la Clínica La Bendecida</h1>
              <p>Cuidando de tu salud con excelencia y compromiso</p>
            </div>
          </div>
          <div class="slide">
            <img src="${pageContext.request.contextPath}/images/slider-2.jpg" alt="Tecnología Avanzada" />
            <div class="slide-content">
              <h1>Tecnología de Vanguardia</h1>
              <p>Equipos modernos para diagnósticos precisos</p>
            </div>
          </div>
          <div class="slide">
            <img src="${pageContext.request.contextPath}/images/slider-4-1.jpg" alt="Equipo Médico" />
            <div class="slide-content">
              <h1>Equipo Médico Especializado</h1>
              <p>Profesionales altamente calificados a tu servicio</p>
            </div>
          </div>
        </div>
        <div class="slider-nav">
          <button class="prev-slide">
            <i class="fas fa-chevron-left"></i>
          </button>
          <button class="next-slide">
            <i class="fas fa-chevron-right"></i>
          </button>
        </div>
        <div class="slider-dots"></div>
      </section>

      <section id="especialidades" class="especialidades">
        <h2>Nuestras Especialidades</h2>
        <div class="especialidades-container">
          <div class="especialidad-card">
            <i class="fas fa-heartbeat"></i>
            <h3>Cardiología</h3>
            <p>Cuidado experto para tu corazón</p>
          </div>
          <div class="especialidad-card">
            <i class="fas fa-brain"></i>
            <h3>Neurología</h3>
            <p>Especialistas en salud cerebral</p>
          </div>
          <div class="especialidad-card">
            <i class="fas fa-baby"></i>
            <h3>Pediatría</h3>
            <p>Atención integral para los más pequeños</p>
          </div>
          <div class="especialidad-card">
            <i class="fas fa-venus"></i>
            <h3>Ginecología</h3>
            <p>Salud y bienestar femenino</p>
          </div>
        </div>
        <div class="ver-mas-container">
          <a href="${pageContext.request.contextPath}/servicios" class="btn-ver-mas"
            >Ver más especialidades</a
          >
        </div>
      </section>

      <section id="por-que-elegirnos" class="por-que-elegirnos">
        <h2>¿Por qué elegirnos?</h2>
        <div class="razones-grid">
          <div class="razon">
            <i class="fas fa-user-md"></i>
            <h3>Profesionales Expertos</h3>
            <p>
              Nuestro equipo médico está formado por especialistas altamente
              calificados.
            </p>
          </div>
          <div class="razon">
            <i class="fas fa-clock"></i>
            <h3>Atención 24/7</h3>
            <p>
              Estamos disponibles las 24 horas del día, los 7 días de la semana.
            </p>
          </div>
          <div class="razon">
            <i class="fas fa-hospital"></i>
            <h3>Instalaciones Modernas</h3>
            <p>
              Contamos con equipos de última generación para un diagnóstico
              preciso.
            </p>
          </div>
          <div class="razon">
            <i class="fas fa-heart"></i>
            <h3>Atención Personalizada</h3>
            <p>
              Nos enfocamos en las necesidades individuales de cada paciente.
            </p>
          </div>
        </div>
      </section>

      <section id="testimonios" class="testimonios">
        <h2>Lo que dicen nuestros pacientes</h2>
        <div class="testimonios-container">
          <div class="testimonios-slider">
            <div class="testimonio">
              <p>
                "Excelente atención y profesionalismo. Me sentí en buenas manos
                desde el primer momento."
              </p>
              <div class="autor">- María García</div>
            </div>
            <div class="testimonio">
              <p>
                "Los médicos son muy atentos y explican todo con claridad.
                Recomiendo totalmente esta clínica."
              </p>
              <div class="autor">- Juan Pérez</div>
            </div>
            <div class="testimonio">
              <p>
                "Instalaciones modernas y personal amable. Una experiencia muy
                positiva en general."
              </p>
              <div class="autor">- Ana Martínez</div>
            </div>
          </div>
        </div>
      </section>

      <section id="contacto" class="contacto">
        <h2>Contáctanos</h2>
        <div class="contacto-container">
          <div class="mapa">
            <iframe
              src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3976.9728391215!2d-74.07662868523807!3d4.598916043799!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x8e3f99a7eccfe58f%3A0x99cb72b35351cc4a!2sHospital%20Universitario%20San%20Ignacio!5e0!3m2!1ses!2sco!4v1653664034619!5m2!1ses!2sco"
              width="100%"
              height="450"
              style="border: 0"
              allowfullscreen=""
              loading="lazy"
              referrerpolicy="no-referrer-when-downgrade"
            ></iframe>
          </div>
          <div class="formulario-contacto">
            <form id="contactForm">
              <div class="form-group">
                <label for="nombre">Nombre</label>
                <input type="text" id="nombre" name="nombre" required />
              </div>
              <div class="form-group">
                <label for="email">Correo electrónico</label>
                <input type="email" id="email" name="email" required />
              </div>
              <div class="form-group">
                <label for="mensaje">Mensaje</label>
                <textarea id="mensaje" name="mensaje" required></textarea>
              </div>
              <button type="submit" class="btn-enviar">Enviar Mensaje</button>
            </form>
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

    <script src="${pageContext.request.contextPath}/js/inicio.js"></script>
    <script src="${pageContext.request.contextPath}/js/vapi.js"></script>
    <script>
        // Inicialización del slider y efectos de navegación
        document.addEventListener('DOMContentLoaded', function() {
            // Iniciar el slider
            initSlider();
            
            // Iniciar efectos de navegación
            initNavEffects();
            
            // Verificar sesión para el botón de reserva
            initReservationButton();
        });
        
        function initReservationButton() {
            const reservarCitaLink = document.getElementById("reservarCitaLink");
            
            reservarCitaLink.addEventListener("click", function(e) {
                e.preventDefault();
                fetch("${pageContext.request.contextPath}/verificar-sesion")
                    .then(response => response.json())
                    .then(data => {
                        if (data.loggedin) {
                            window.location.href = "${pageContext.request.contextPath}/reservar-cita";
                        } else {
                            window.location.href = "${pageContext.request.contextPath}/login-registro";
                        }
                    });
            });
        }
    </script>
  </body>
</html>

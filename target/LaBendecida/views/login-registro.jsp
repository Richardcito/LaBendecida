<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="es">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <meta
      http-equiv="Cache-Control"
      content="no-cache, no-store, must-revalidate"
    />
    <meta http-equiv="Pragma" content="no-cache" />
    <meta http-equiv="Expires" content="0" />
    <title>Clínica La Bendecida/Login/Registro</title>
    <link rel="icon" href="${pageContext.request.contextPath}/images/logo la bendecida.ico" type="image/x-icon" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/login-registro.css" />
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

    <div class="container">
      <div class="form-container sign-in">
        <form id="login-form" action="${pageContext.request.contextPath}/login" method="POST">
          <h2>Iniciar Sesión</h2>
          <div class="message" style="display: none;"></div>
          
          <div class="input-field">
            <input type="email" name="email" required />
            <label>Correo electrónico</label>
          </div>
          <div class="input-field">
            <input type="password" name="password" required />
            <label>Contraseña</label>
          </div>
          <div class="forget">
            <label for="remember">
              <input type="checkbox" id="remember" name="remember" />
              <p>Recuérdame</p>
            </label>
            <a href="${pageContext.request.contextPath}/recuperar-password">¿Olvidaste tu contraseña?</a>
          </div>
          <button type="submit">Iniciar Sesión</button>
          <div class="register">
            <p>¿No tienes una cuenta? <a href="#" class="register-link">Regístrate</a></p>
          </div>
        </form>
      </div>

      <div class="form-container sign-up">
        <form id="signup-form" action="${pageContext.request.contextPath}/registro" method="post">
          <h2>Registrarse</h2>
          <div class="message" style="display: none;"></div>
          
          <div class="input-field">
            <input type="text" name="nombre" required />
            <label>Nombre</label>
          </div>
          <div class="input-field">
            <input type="text" name="apellido" required />
            <label>Apellido</label>
          </div>
          <div class="input-field">
            <input type="email" name="email" required />
            <label>Correo electrónico</label>
          </div>
          <div class="input-field">
            <input type="password" name="password" id="password" required />
            <label>Contraseña</label>
          </div>
          <div class="input-field">
            <input type="password" id="confirm-password" required />
            <label>Confirmar contraseña</label>
          </div>
          <button type="submit">Registrarse</button>
          <div class="login">
            <p>¿Ya tienes una cuenta? <a href="#" class="login-link">Inicia sesión</a></p>
          </div>
        </form>
      </div>
    </div>

    <script src="${pageContext.request.contextPath}/js/login-registro.js"></script>
  </body>
</html>

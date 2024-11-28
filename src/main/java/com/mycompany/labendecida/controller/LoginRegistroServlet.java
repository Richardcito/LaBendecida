package com.mycompany.labendecida.controller;

import com.mycompany.labendecida.dao.UsuarioDAO;
import com.mycompany.labendecida.dao.UsuarioRolDAO;
import com.mycompany.labendecida.model.Usuario;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import org.json.JSONObject;

@WebServlet(urlPatterns = {"/login-registro", "/login", "/registro"})
public class LoginRegistroServlet extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/views/login-registro.jsp").forward(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String servletPath = request.getServletPath();
        JSONObject jsonResponse = new JSONObject();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        
        try {
            if ("/login".equals(servletPath)) {
                handleLogin(request, response, jsonResponse);
            } else if ("/registro".equals(servletPath)) {
                handleRegistro(request, response, jsonResponse);
            }
        } catch (Exception e) {
            e.printStackTrace();
            jsonResponse.put("success", false);
            jsonResponse.put("message", "Error en el servidor: " + e.getMessage());
        }
        
        response.getWriter().write(jsonResponse.toString());
        response.getWriter().flush();
    }
    
    private void handleLogin(HttpServletRequest request, HttpServletResponse response, JSONObject jsonResponse) 
            throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        
        System.out.println("Intento de login - Email: " + email); // Debug
        
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        Usuario usuario = usuarioDAO.validarUsuario(email, password);
        
        if (usuario != null) {
            HttpSession session = request.getSession();
            session.setAttribute("usuario", usuario);
            
            // Obtener el rol usando el nuevo UsuarioRolDAO
            UsuarioRolDAO usuarioRolDAO = new UsuarioRolDAO();
            String rol = usuarioRolDAO.obtenerRolPorUsuarioId(usuario.getId());
            String redirectUrl;
            
            // Determinar la redirección según el rol
            if (rol != null) {
                switch(rol.toLowerCase()) {
                    case "admin":
                    case "administrador":
                        redirectUrl = "/dashboard-admin";
                        break;
                    case "medico":
                        redirectUrl = "/dashboard-medico";
                        break;
                    case "paciente":
                        redirectUrl = "/inicio";
                        break;
                    default:
                        redirectUrl = "/inicio";
                        break;
                }
            } else {
                redirectUrl = "/inicio"; // Si no se encuentra rol
            }
            
            // Guardar el rol en la sesión para uso futuro
            session.setAttribute("rolUsuario", rol);
            
            jsonResponse.put("success", true);
            jsonResponse.put("message", "Inicio de sesión exitoso");
            jsonResponse.put("redirect", request.getContextPath() + redirectUrl);
            System.out.println("Login exitoso - Redirigiendo a: " + request.getContextPath() + redirectUrl); // Debug
        } else {
            jsonResponse.put("success", false);
            jsonResponse.put("message", "Credenciales inválidas");
            System.out.println("Login fallido - Credenciales inválidas"); // Debug
        }
    }
    
    private void handleRegistro(HttpServletRequest request, HttpServletResponse response, JSONObject jsonResponse) 
            throws ServletException, IOException {
        try {
            String nombre = request.getParameter("nombre");
            String apellido = request.getParameter("apellido");
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            
            System.out.println("Intento de registro - Nombre: " + nombre + ", Email: " + email); // Debug
            
            if (nombre == null || apellido == null || email == null || password == null || 
                nombre.trim().isEmpty() || apellido.trim().isEmpty() || email.trim().isEmpty() || password.trim().isEmpty()) {
                jsonResponse.put("success", false);
                jsonResponse.put("message", "Todos los campos son requeridos");
                return;
            }
            
            UsuarioDAO usuarioDAO = new UsuarioDAO();
            
            if (usuarioDAO.existeEmail(email)) {
                jsonResponse.put("success", false);
                jsonResponse.put("message", "El email ya está registrado");
                return;
            }
            
            Usuario nuevoUsuario = new Usuario();
            nuevoUsuario.setEmail(email);
            nuevoUsuario.setPassword(password);
            
            // Registrar usuario y obtener el ID generado
            int userId = usuarioDAO.registrarUsuario(nuevoUsuario, nombre, apellido);
            
            if (userId > 0) {
                // Asignar rol de paciente por defecto
                UsuarioRolDAO usuarioRolDAO = new UsuarioRolDAO();
                usuarioRolDAO.asignarRol(userId, 3); // Asumiendo que 3 es el ID del rol "paciente"
                
                jsonResponse.put("success", true);
                jsonResponse.put("message", "Registro exitoso. Por favor, inicia sesión.");
                System.out.println("Registro exitoso - UserID: " + userId); // Debug
            } else {
                jsonResponse.put("success", false);
                jsonResponse.put("message", "Error al registrar el usuario");
                System.out.println("Registro fallido"); // Debug
            }
        } catch (Exception e) {
            e.printStackTrace();
            jsonResponse.put("success", false);
            jsonResponse.put("message", "Error del servidor: " + e.getMessage());
        }
    }
} 
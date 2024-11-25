package com.mycompany.labendecida.controller;

import com.mycompany.labendecida.dao.UsuarioDAO;
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
            
            jsonResponse.put("success", true);
            jsonResponse.put("message", "Inicio de sesión exitoso");
            jsonResponse.put("redirect", request.getContextPath() + "/inicio");
            System.out.println("Login exitoso - Redirigiendo a: " + request.getContextPath() + "/inicio"); // Debug
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
            
            if (nombre == null || apellido == null || email == null || password == null) {
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
            
            int userId = usuarioDAO.registrarUsuario(nuevoUsuario, nombre, apellido);
            
            if (userId > 0) {
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
package com.mycompany.labendecida.controller;

import com.google.gson.Gson;
import com.mycompany.labendecida.dao.UsuarioDAO;
import com.mycompany.labendecida.model.Usuario;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/api/usuarios/*")
public class UsuarioServlet extends HttpServlet {
    private UsuarioDAO usuarioDAO;
    
    @Override
    public void init() throws ServletException {
        usuarioDAO = new UsuarioDAO();
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String pathInfo = request.getPathInfo();
        
        if (pathInfo == null || pathInfo.equals("/")) {
            try {
                List<Usuario> usuarios = usuarioDAO.getAllUsuarios();
                request.setAttribute("usuarios", usuarios);
                request.getRequestDispatcher("/views/dashboard-admin/usuarios/usuarios.jsp")
                      .forward(request, response);
            } catch (SQLException e) {
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
            }
        } else {
            try {
                int id = Integer.parseInt(pathInfo.substring(1));
                Usuario usuario = usuarioDAO.getUsuarioById(id);
                if (usuario != null) {
                    response.setContentType("application/json");
                    response.getWriter().write(new Gson().toJson(usuario));
                } else {
                    response.sendError(HttpServletResponse.SC_NOT_FOUND);
                }
            } catch (SQLException e) {
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
            }
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        try {
            Usuario usuario = new Usuario();
            usuario.setNombre(request.getParameter("nombre"));
            usuario.setApellido(request.getParameter("apellido"));
            usuario.setEmail(request.getParameter("email"));
            usuario.setPassword(request.getParameter("password"));
            
            // Convertir el rol de String a int
            String rolStr = request.getParameter("rol");
            if (rolStr != null && !rolStr.isEmpty()) {
                usuario.setRol(Integer.parseInt(rolStr));
            }
            
            usuarioDAO.createUsuario(usuario);
            response.setStatus(HttpServletResponse.SC_CREATED);
            
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error al crear usuario: " + e.getMessage());
        }
    }
    
    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        try {
            int id = Integer.parseInt(request.getPathInfo().substring(1));
            
            Usuario usuario = new Usuario();
            usuario.setId(id);
            usuario.setNombre(request.getParameter("nombre"));
            usuario.setApellido(request.getParameter("apellido"));
            usuario.setEmail(request.getParameter("email"));
            
            // Convertir el rol de String a int
            String rolStr = request.getParameter("rol");
            if (rolStr != null && !rolStr.isEmpty()) {
                usuario.setRol(Integer.parseInt(rolStr));
            }
            
            usuarioDAO.updateUsuario(usuario);
            response.setStatus(HttpServletResponse.SC_OK);
            
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error al actualizar usuario: " + e.getMessage());
        }
    }
    
    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        try {
            int id = Integer.parseInt(request.getPathInfo().substring(1));
            if (usuarioDAO.deleteUsuario(id)) {
                response.setStatus(HttpServletResponse.SC_OK);
            } else {
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
            }
        } catch (SQLException e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }
} 
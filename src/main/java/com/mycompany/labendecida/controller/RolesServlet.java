package com.mycompany.labendecida.controller;

import com.mycompany.labendecida.dao.RolDAO;
import com.mycompany.labendecida.model.Rol;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/dashboard-admin/roles")
public class RolesServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private RolDAO rolDAO;

    @Override
    public void init() throws ServletException {
        rolDAO = new RolDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Rol> roles = rolDAO.getAllRoles();
        request.setAttribute("roles", roles);
        request.getRequestDispatcher("/views/dashboard-admin/roles/roles.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        
        switch (action) {
            case "create":
                createRol(request, response);
                break;
            case "update":
                updateRol(request, response);
                break;
            case "delete":
                deleteRol(request, response);
                break;
        }
        
        response.sendRedirect(request.getContextPath() + "/dashboard-admin/roles");
    }

    private void createRol(HttpServletRequest request, HttpServletResponse response) {
        String nombre = request.getParameter("nombre");
        String descripcion = request.getParameter("descripcion");
        
        Rol rol = new Rol();
        rol.setNombre(nombre);
        rol.setDescripcion(descripcion);
        
        rolDAO.createRol(rol);
    }

    private void updateRol(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("rolId"));
        String nombre = request.getParameter("nombre");
        String descripcion = request.getParameter("descripcion");
        
        Rol rol = new Rol();
        rol.setId(id);
        rol.setNombre(nombre);
        rol.setDescripcion(descripcion);
        
        rolDAO.updateRol(rol);
    }

    private void deleteRol(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));
        rolDAO.deleteRol(id);
    }
} 
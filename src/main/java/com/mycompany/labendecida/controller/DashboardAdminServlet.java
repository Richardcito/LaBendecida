package com.mycompany.labendecida.controller;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/dashboard-admin/*")
public class DashboardAdminServlet extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String action = request.getPathInfo();
        if (action == null) {
            action = "/";
        }

        switch (action) {
            case "/":
                showDashboard(request, response);
                break;
            case "/usuarios":
                showUsuarios(request, response);
                break;
            case "/medicos":
                showMedicos(request, response);
                break;
            case "/especialidades":
                showEspecialidades(request, response);
                break;
            case "/citas":
                showCitas(request, response);
                break;
            case "/roles":
                showRoles(request, response);
                break;
            default:
                response.sendRedirect(request.getContextPath() + "/dashboard-admin");
                break;
        }
    }

    private void showDashboard(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // Simplemente redirigir al JSP por ahora
            request.getRequestDispatcher("/views/dashboard-admin/dashboard-admin.jsp")
                  .forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/error");
        }
    }

    private void showUsuarios(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/views/dashboard-admin/usuarios.jsp")
              .forward(request, response);
    }

    private void showMedicos(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/views/dashboard-admin/medicos.jsp")
              .forward(request, response);
    }

    private void showEspecialidades(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/views/dashboard-admin/especialidades.jsp")
              .forward(request, response);
    }

    private void showCitas(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/views/dashboard-admin/citas.jsp")
              .forward(request, response);
    }

    private void showRoles(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/views/dashboard-admin/roles.jsp")
              .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Por ahora solo redirigimos al dashboard
        response.sendRedirect(request.getContextPath() + "/dashboard-admin");
    }
} 
package com.mycompany.labendecida.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.mycompany.labendecida.dao.UsuarioDAO;
import com.mycompany.labendecida.dao.CitaDAO;
import com.mycompany.labendecida.model.Usuario;
import com.mycompany.labendecida.model.Cita;

@WebServlet("/dashboard-admin/*")
public class DashboardAdminServlet extends HttpServlet {
    private UsuarioDAO usuarioDAO;
    private CitaDAO citaDAO;

    @Override
    public void init() throws ServletException {
        usuarioDAO = new UsuarioDAO();
        citaDAO = new CitaDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            
        String action = request.getPathInfo();
        if (action == null) {
            action = "/";
        }

        try {
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
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/error");
        }
    }

    private void showUsuarios(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            List<Usuario> usuarios = usuarioDAO.getAllUsuarios();
            request.setAttribute("usuarios", usuarios);
            request.getRequestDispatcher("/views/dashboard-admin/usuarios/usuarios.jsp")
                  .forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/error");
        }
    }

    private void showDashboard(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // Obtener estadísticas reales de la base de datos
            int totalUsuarios = usuarioDAO.getTotalUsuarios();
            int totalMedicos = usuarioDAO.getTotalMedicos();
            int citasHoy = citaDAO.getCitasHoy();
            int totalEspecialidades = usuarioDAO.getTotalEspecialidades();

            // Obtener listas para actividad reciente
            List<Usuario> usuariosRecientes = usuarioDAO.getUsuariosRecientes(5);
            List<Cita> citasRecientes = citaDAO.getCitasRecientes(5);

            // Establecer atributos para la vista
            request.setAttribute("totalUsuarios", totalUsuarios);
            request.setAttribute("totalMedicos", totalMedicos);
            request.setAttribute("citasHoy", citasHoy);
            request.setAttribute("totalEspecialidades", totalEspecialidades);
            request.setAttribute("usuariosRecientes", usuariosRecientes);
            request.setAttribute("citasRecientes", citasRecientes);

            // Mostrar la vista
            request.getRequestDispatcher("/views/dashboard-admin/dashboard-admin.jsp")
                  .forward(request, response);

        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/error");
        }
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
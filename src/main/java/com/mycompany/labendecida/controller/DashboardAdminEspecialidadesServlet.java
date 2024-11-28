package com.mycompany.labendecida.controller;

import com.mycompany.labendecida.dao.EspecialidadDAO;
import com.mycompany.labendecida.model.Especialidad;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/dashboard-admin/especialidades")
public class DashboardAdminEspecialidadesServlet extends HttpServlet {
    private final EspecialidadDAO especialidadDAO;

    public DashboardAdminEspecialidadesServlet() {
        this.especialidadDAO = new EspecialidadDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            List<Especialidad> especialidades = especialidadDAO.getAllEspecialidades();
            request.setAttribute("especialidades", especialidades);
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("error", "Error al cargar las especialidades: " + e.getMessage());
        }
        
        request.getRequestDispatcher("/views/dashboard-admin/especialidades/especialidades.jsp")
              .forward(request, response);
    }
} 
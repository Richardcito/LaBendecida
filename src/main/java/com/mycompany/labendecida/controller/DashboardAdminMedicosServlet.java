package com.mycompany.labendecida.controller;

import com.mycompany.labendecida.dao.MedicoDAO;
import com.mycompany.labendecida.dao.EspecialidadDAO;
import com.mycompany.labendecida.model.Medico;
import com.mycompany.labendecida.model.Especialidad;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Usuario
 */
@WebServlet("/dashboard-admin/medicos")
public class DashboardAdminMedicosServlet extends HttpServlet {
    private MedicoDAO medicoDAO;
    private EspecialidadDAO especialidadDAO;

    @Override
    public void init() throws ServletException {
        medicoDAO = new MedicoDAO();
        especialidadDAO = new EspecialidadDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            List<Medico> medicos = medicoDAO.getAllMedicos();
            List<Especialidad> especialidades = especialidadDAO.getAllEspecialidades();
            
            request.setAttribute("medicos", medicos);
            request.setAttribute("especialidades", especialidades);
            request.getRequestDispatcher("/views/dashboard-admin/medicos/medicos.jsp")
                  .forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("error", "Error al cargar los médicos: " + e.getMessage());
            request.getRequestDispatcher("/views/dashboard-admin/medicos/medicos.jsp")
                  .forward(request, response);
        }
    }
} 
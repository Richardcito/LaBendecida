package com.mycompany.labendecida.controller;

import com.mycompany.labendecida.dao.CitaDAO;
import com.mycompany.labendecida.model.Cita;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/dashboard-admin/citas")
public class DashboardAdminCitasServlet extends HttpServlet {
    private final CitaDAO citaDAO;

    public DashboardAdminCitasServlet() {
        this.citaDAO = new CitaDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            List<Cita> citas = citaDAO.getAllCitas();
            System.out.println("Servlet: Citas recuperadas = " + citas.size()); // Debug
            
            request.setAttribute("citas", citas);
            
            // Verifica si las citas están vacías
            if (citas.isEmpty()) {
                System.out.println("No se encontraron citas en la base de datos");
            } else {
                System.out.println("Se encontraron " + citas.size() + " citas");
            }
            
            request.getRequestDispatcher("/views/dashboard-admin/citas/citas.jsp")
                  .forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("error", "Error al cargar las citas: " + e.getMessage());
            request.getRequestDispatcher("/views/dashboard-admin/citas/citas.jsp")
                  .forward(request, response);
        }
    }
} 
/*package com.mycompany.labendecida.controller;

import com.mycompany.labendecida.dao.CitaDAO;
import com.mycompany.labendecida.model.Cita;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.util.List;

@WebServlet("/dashboard-medico/citas")
public class CitasServlet extends HttpServlet {
    private CitaDAO citaDAO = new CitaDAO();
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        try {
            // Usar un ID fijo para pruebas
            int medicoId = 1; // ID fijo para pruebas
            
            // Obtener parámetros de filtro
            String estado = request.getParameter("estado");
            String fechaStr = request.getParameter("fecha");
            Date fecha = null;
            
            if (fechaStr != null && !fechaStr.isEmpty()) {
                fecha = Date.valueOf(fechaStr);
            }
            
            // Obtener las citas
            List<Cita> citas = citaDAO.listarCitasPorMedico(medicoId, estado, fecha);
            
            // Establecer los datos en el request
            request.setAttribute("citas", citas);
            request.setAttribute("fecha", new java.util.Date());
            
            // Forward a la página JSP
            request.getRequestDispatcher("/views/dashboard-medico/citas.jsp")
                  .forward(request, response);
            
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error en CitasServlet: " + e.getMessage());
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error al cargar las citas");
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String citaIdStr = request.getParameter("citaId");
        
        // Validar que citaId no sea null o vacío
        if (citaIdStr != null && !citaIdStr.trim().isEmpty()) {
            try {
                int citaId = Integer.parseInt(citaIdStr);
                String nuevoEstado = request.getParameter("estado");
                
                boolean success = citaDAO.actualizarEstadoCita(citaId, nuevoEstado);
                
                response.setContentType("application/json");
                response.getWriter().write("{\"success\":" + success + "}");
            } catch (NumberFormatException e) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.getWriter().write("ID de cita inválido");
            }
        } else {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("ID de cita no proporcionado");
        }
    }
} 
    */
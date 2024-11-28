package com.mycompany.labendecida.controller;

import com.google.gson.Gson;
import com.mycompany.labendecida.dao.MedicoDAO;
import com.mycompany.labendecida.model.Medico;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

/**
 *
 * @author Usuario
 */
@WebServlet("/api/medicos/*")
public class MedicoServlet extends HttpServlet {
    private MedicoDAO medicoDAO;
    
    @Override
    public void init() throws ServletException {
        medicoDAO = new MedicoDAO();
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String pathInfo = request.getPathInfo();
        
        if (pathInfo == null || pathInfo.equals("/")) {
            try {
                List<Medico> medicos = medicoDAO.getAllMedicos();
                request.setAttribute("medicos", medicos);
                request.getRequestDispatcher("/views/dashboard-admin/medicos/medicos.jsp")
                      .forward(request, response);
            } catch (SQLException e) {
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
            }
        } else {
            try {
                int id = Integer.parseInt(pathInfo.substring(1));
                Medico medico = medicoDAO.getMedicoById(id);
                if (medico != null) {
                    response.setContentType("application/json");
                    response.getWriter().write(new Gson().toJson(medico));
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
            Medico medico = new Medico();
            medico.setNombre(request.getParameter("nombre"));
            medico.setApellido(request.getParameter("apellido"));
            medico.setEmail(request.getParameter("email"));
            medico.setPassword(request.getParameter("password"));
            medico.setFechaRegistro(new Timestamp(System.currentTimeMillis()));
            
            String especialidadStr = request.getParameter("especialidad");
            if (especialidadStr != null && !especialidadStr.isEmpty()) {
                medico.setEspecialidad_id(Integer.parseInt(especialidadStr));
            }
            
            medicoDAO.createMedico(medico);
            response.setStatus(HttpServletResponse.SC_CREATED);
            
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error al crear médico: " + e.getMessage());
        }
    }
    
    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        try {
            int id = Integer.parseInt(request.getPathInfo().substring(1));
            
            Medico medico = new Medico();
            medico.setId(id);
            medico.setNombre(request.getParameter("nombre"));
            medico.setApellido(request.getParameter("apellido"));
            medico.setEmail(request.getParameter("email"));
            
            String especialidadStr = request.getParameter("especialidad");
            if (especialidadStr != null && !especialidadStr.isEmpty()) {
                medico.setEspecialidad_id(Integer.parseInt(especialidadStr));
            }
            
            medicoDAO.updateMedico(medico);
            response.setStatus(HttpServletResponse.SC_OK);
            
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error al actualizar médico: " + e.getMessage());
        }
    }
    
    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        try {
            int id = Integer.parseInt(request.getPathInfo().substring(1));
            if (medicoDAO.deleteMedico(id)) {
                response.setStatus(HttpServletResponse.SC_OK);
            } else {
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
            }
        } catch (SQLException e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }
} 
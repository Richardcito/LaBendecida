package com.mycompany.labendecida.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/dashboard-medico/*")
public class DashboardMedicoServlet extends HttpServlet {
    
    private static final Map<String, String> PAGES = new HashMap<>();
    
    static {
        // Mapeo de rutas a páginas JSP
        PAGES.put("/", "/views/dashboard-medico/dashboard-medico.jsp");
        PAGES.put("/citas", "/views/dashboard-medico/citas.jsp");
        PAGES.put("/horarios", "/views/dashboard-medico/horarios.jsp");
        PAGES.put("/perfil", "/views/dashboard-medico/perfil.jsp");
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            
        // Obtener la ruta después de /dashboard-medico
        String pathInfo = request.getPathInfo();
        pathInfo = (pathInfo == null) ? "/" : pathInfo;
        
        // Obtener la página correspondiente o la página por defecto
        String page = PAGES.getOrDefault(pathInfo, PAGES.get("/"));
        
        // Forward a la página JSP correspondiente
        request.getRequestDispatcher(page).forward(request, response);
    }
} 
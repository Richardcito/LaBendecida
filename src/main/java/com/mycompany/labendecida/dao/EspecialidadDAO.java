package com.mycompany.labendecida.dao;

import com.mycompany.labendecida.config.Conexion;
import com.mycompany.labendecida.model.Especialidad;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EspecialidadDAO {
    
    public List<Especialidad> getAllEspecialidades() throws SQLException {
        List<Especialidad> especialidades = new ArrayList<>();
        String sql = "SELECT id, nombre, descripcion FROM especialidades ORDER BY id";
        
        try (Connection conn = Conexion.getConnection()) {
            System.out.println("Conexión establecida");
            
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                try (ResultSet rs = stmt.executeQuery()) {
                    System.out.println("Ejecutando query: " + sql);
                    
                    while (rs.next()) {
                        Especialidad especialidad = new Especialidad();
                        especialidad.setId(rs.getInt("id"));
                        especialidad.setNombre(rs.getString("nombre"));
                        especialidad.setDescripcion(rs.getString("descripcion"));
                        especialidades.add(especialidad);
                        
                        System.out.println("Especialidad encontrada: " + especialidad.getNombre());
                    }
                }
            }
        } catch (SQLException e) {
            System.err.println("Error en getAllEspecialidades: " + e.getMessage());
            e.printStackTrace();
            throw e;
        }
        
        System.out.println("Total especialidades encontradas: " + especialidades.size());
        return especialidades;
    }
    
    public Especialidad getEspecialidadById(int id) throws SQLException {
        String sql = "SELECT id, nombre, descripcion, estado FROM especialidades WHERE id = ?";
        
        try (Connection conn = Conexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                Especialidad especialidad = new Especialidad();
                especialidad.setId(rs.getInt("id"));
                especialidad.setNombre(rs.getString("nombre"));
                especialidad.setDescripcion(rs.getString("descripcion"));
                especialidad.setEstado(rs.getBoolean("estado"));
                return especialidad;
            }
        }
        return null;
    }
    
    public void createEspecialidad(Especialidad especialidad) throws SQLException {
        String sql = "INSERT INTO especialidades (nombre, descripcion, estado) VALUES (?, ?, ?)";
        
        try (Connection conn = Conexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, especialidad.getNombre());
            stmt.setString(2, especialidad.getDescripcion());
            stmt.setBoolean(3, especialidad.isEstado());
            stmt.executeUpdate();
        }
    }
    
    public void updateEspecialidad(Especialidad especialidad) throws SQLException {
        String sql = "UPDATE especialidades SET nombre = ?, descripcion = ?, estado = ? WHERE id = ?";
        
        try (Connection conn = Conexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, especialidad.getNombre());
            stmt.setString(2, especialidad.getDescripcion());
            stmt.setBoolean(3, especialidad.isEstado());
            stmt.setInt(4, especialidad.getId());
            stmt.executeUpdate();
        }
    }
    
    public boolean deleteEspecialidad(int id) throws SQLException {
        String sql = "UPDATE especialidades SET estado = false WHERE id = ?";
        
        try (Connection conn = Conexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        }
    }
} 
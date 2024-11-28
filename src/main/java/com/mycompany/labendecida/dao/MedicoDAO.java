package com.mycompany.labendecida.dao;

import com.mycompany.labendecida.config.Conexion;
import com.mycompany.labendecida.model.Medico;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Usuario
 */
public class MedicoDAO {
    
    public List<Medico> getAllMedicos() throws SQLException {
        List<Medico> medicos = new ArrayList<>();
        String sql = "SELECT DISTINCT u.id, u.email, u.fecha_registro, " +
                    "ip.nombre, ip.apellido, " +
                    "e.nombre as especialidad_nombre, " +
                    "m.numero_colegiado " +
                    "FROM usuarios u " +
                    "INNER JOIN usuario_roles ur ON u.id = ur.usuario_id " +
                    "LEFT JOIN informacion_personal ip ON u.id = ip.usuario_id " +
                    "LEFT JOIN medicos m ON u.id = m.usuario_id " +
                    "LEFT JOIN especialidades e ON m.especialidad_id = e.id " +
                    "WHERE ur.rol_id = 2";
        
        try (Connection conn = Conexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            
            while (rs.next()) {
                try {
                    Medico medico = new Medico();
                    medico.setId(rs.getInt("id"));
                    medico.setEmail(rs.getString("email"));
                    medico.setNombre(rs.getString("nombre"));
                    medico.setApellido(rs.getString("apellido"));
                    medico.setFechaRegistro(rs.getTimestamp("fecha_registro"));
                    medico.setNumeroColegiado(rs.getString("numero_colegiado"));
                    
                    // Manejar especialidad
                    String especialidad = rs.getString("especialidad_nombre");
                    medico.setEspecialidad(especialidad != null ? especialidad : "Sin especialidad");
                    
                    medicos.add(medico);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return medicos;
    }
    
    public Medico getMedicoById(int id) throws SQLException {
        String sql = "SELECT u.*, ip.nombre, ip.apellido " +
                    "FROM usuarios u " +
                    "INNER JOIN usuario_roles ur ON u.id = ur.usuario_id " +
                    "LEFT JOIN informacion_personal ip ON u.id = ip.usuario_id " +
                    "WHERE u.id = ? AND ur.rol_id = 2";
        
        try (Connection conn = Conexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                Medico medico = new Medico();
                medico.setId(rs.getInt("id"));
                medico.setEmail(rs.getString("email"));
                medico.setNombre(rs.getString("nombre"));
                medico.setApellido(rs.getString("apellido"));
                medico.setFechaRegistro(rs.getTimestamp("fecha_registro"));
                medico.setNumeroColegiado(rs.getString("numero_colegiado"));
                return medico;
            }
        }
        return null;
    }
    
    public void createMedico(Medico medico) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        
        try {
            conn = Conexion.getConnection();
            conn.setAutoCommit(false);
            
            // Insertar en la tabla usuarios
            String sql = "INSERT INTO usuarios (email, password, fecha_registro) VALUES (?, ?, ?)";
            pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, medico.getEmail());
            pstmt.setString(2, medico.getPassword());
            pstmt.setTimestamp(3, medico.getFechaRegistro());
            pstmt.executeUpdate();
            
            // Obtener el ID generado
            ResultSet rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                int medicoId = rs.getInt(1);
                
                // Insertar en la tabla informacion_personal
                sql = "INSERT INTO informacion_personal (usuario_id, nombre, apellido) VALUES (?, ?, ?)";
                pstmt = conn.prepareStatement(sql);
                pstmt.setInt(1, medicoId);
                pstmt.setString(2, medico.getNombre());
                pstmt.setString(3, medico.getApellido());
                pstmt.executeUpdate();
                
                // Insertar en usuario_roles
                sql = "INSERT INTO usuario_roles (usuario_id, rol_id) VALUES (?, 2)";
                pstmt = conn.prepareStatement(sql);
                pstmt.setInt(1, medicoId);
                pstmt.executeUpdate();
            }
            
            conn.commit();
        } catch (SQLException e) {
            if (conn != null) conn.rollback();
            throw e;
        } finally {
            if (pstmt != null) pstmt.close();
            if (conn != null) {
                conn.setAutoCommit(true);
                conn.close();
            }
        }
    }
    
    public void updateMedico(Medico medico) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        
        try {
            conn = Conexion.getConnection();
            conn.setAutoCommit(false);
            
            // Actualizar información personal
            String sql = "UPDATE informacion_personal SET nombre = ?, apellido = ? WHERE usuario_id = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, medico.getNombre());
            pstmt.setString(2, medico.getApellido());
            pstmt.setInt(3, medico.getId());
            pstmt.executeUpdate();
            
            // Actualizar email en usuarios
            sql = "UPDATE usuarios SET email = ? WHERE id = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, medico.getEmail());
            pstmt.setInt(2, medico.getId());
            pstmt.executeUpdate();
            
            conn.commit();
        } catch (SQLException e) {
            if (conn != null) conn.rollback();
            throw e;
        } finally {
            if (pstmt != null) pstmt.close();
            if (conn != null) {
                conn.setAutoCommit(true);
                conn.close();
            }
        }
    }
    
    public boolean deleteMedico(int id) throws SQLException {
        String sql = "UPDATE usuarios SET estado = false WHERE id = ? AND rol_id = 2";
        
        try (Connection conn = Conexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        }
    }
} 
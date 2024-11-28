package com.mycompany.labendecida.dao;

import com.mycompany.labendecida.config.Conexion;
import com.mycompany.labendecida.model.Cita;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CitaDAO {
    
    public List<Cita> listarCitasPorMedico(int medicoId, String estado, Date fecha) {
        List<Cita> citas = new ArrayList<>();
        StringBuilder sql = new StringBuilder(
            "SELECT c.*, " +
            "p_info.nombre as paciente_nombre, " +
            "p_info.apellido as paciente_apellido, " +
            "m_info.nombre as medico_nombre, " +
            "m_info.apellido as medico_apellido " +
            "FROM citas c " +
            "INNER JOIN informacion_personal p_info ON c.paciente_id = p_info.usuario_id " +
            "INNER JOIN informacion_personal m_info ON c.medico_id = m_info.usuario_id " +
            "WHERE c.medico_id = ?");
        
        if (estado != null && !estado.isEmpty() && !estado.equals("Todos los estados")) {
            sql.append(" AND c.estado = ?");
        }
        if (fecha != null) {
            sql.append(" AND c.fecha = ?");
        }
        sql.append(" ORDER BY c.fecha, c.hora");
        
        try (Connection conn = Conexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql.toString())) {
            
            int paramIndex = 1;
            stmt.setInt(paramIndex++, medicoId);
            
            if (estado != null && !estado.isEmpty() && !estado.equals("Todos los estados")) {
                stmt.setString(paramIndex++, estado);
            }
            if (fecha != null) {
                stmt.setDate(paramIndex, fecha);
            }
            
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Cita cita = new Cita();
                cita.setId(rs.getInt("id"));
                cita.setPacienteId(rs.getInt("paciente_id"));
                cita.setMedicoId(rs.getInt("medico_id"));
                cita.setFecha(rs.getDate("fecha"));
                cita.setHora(rs.getTime("hora"));
                cita.setHoraFin(rs.getTime("hora_fin"));
                cita.setMotivo(rs.getString("motivo"));
                cita.setEstado(rs.getString("estado"));
                
                // Información del paciente
                cita.setNombrePaciente(rs.getString("paciente_nombre"));
                cita.setApellidoPaciente(rs.getString("paciente_apellido"));
                
                // Información del médico
                cita.setNombreMedico(rs.getString("medico_nombre"));
                cita.setApellidoMedico(rs.getString("medico_apellido"));
                
                citas.add(cita);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return citas;
    }
    
    public boolean actualizarEstadoCita(int citaId, String nuevoEstado) {
        String sql = "UPDATE citas SET estado = ? WHERE id = ?";
        try (Connection conn = Conexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, nuevoEstado);
            stmt.setInt(2, citaId);
            
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public int getCitasHoy() throws SQLException {
        String sql = "SELECT COUNT(*) FROM citas WHERE DATE(fecha) = CURRENT_DATE";
        try (PreparedStatement stmt = Conexion.getConnection().prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        }
        return 0;
    }
    
    public List<Cita> getCitasRecientes(int limit) throws SQLException {
        List<Cita> citas = new ArrayList<>();
        String sql = "SELECT c.*, " +
                    "CONCAT(ip_pac.nombre, ' ', ip_pac.apellido) as nombre_paciente, " +
                    "CONCAT(ip_med.nombre, ' ', ip_med.apellido) as nombre_medico " +
                    "FROM citas c " +
                    "JOIN informacion_personal ip_pac ON c.paciente_id = ip_pac.usuario_id " +
                    "JOIN medicos m ON c.medico_id = m.id " +
                    "JOIN informacion_personal ip_med ON m.usuario_id = ip_med.usuario_id " +
                    "ORDER BY c.fecha DESC, c.hora DESC LIMIT ?";

        try (Connection conn = Conexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, limit);
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                Cita cita = new Cita();
                cita.setId(rs.getInt("id"));
                cita.setPacienteId(rs.getInt("paciente_id"));
                cita.setMedicoId(rs.getInt("medico_id"));
                cita.setFecha(rs.getDate("fecha"));
                cita.setHora(rs.getTime("hora"));
                cita.setHoraFin(rs.getTime("hora_fin"));
                cita.setMotivo(rs.getString("motivo"));
                cita.setEstado(rs.getString("estado"));
                cita.setNombrePaciente(rs.getString("nombre_paciente"));
                cita.setNombreMedico(rs.getString("nombre_medico"));
                citas.add(cita);
            }
        }
        return citas;
    }
    
    public List<Cita> getAllCitas() throws SQLException {
        List<Cita> citas = new ArrayList<>();
        String sql = "SELECT c.*, " +
                    "CONCAT(p.nombre, ' ', p.apellido) as nombre_paciente, " +
                    "CONCAT(m.nombre, ' ', m.apellido) as nombre_medico, " +
                    "e.nombre as nombre_especialidad " +
                    "FROM citas c " +
                    "LEFT JOIN informacion_personal p ON c.paciente_id = p.usuario_id " +
                    "LEFT JOIN medicos med ON c.medico_id = med.id " +
                    "LEFT JOIN informacion_personal m ON med.usuario_id = m.usuario_id " +
                    "LEFT JOIN especialidades e ON med.especialidad_id = e.id " +
                    "ORDER BY c.id ASC";
        
        try (Connection conn = Conexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            System.out.println("Ejecutando consulta SQL: " + sql); // Debug
            
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Cita cita = new Cita();
                    cita.setId(rs.getInt("id"));
                    cita.setPacienteId(rs.getInt("paciente_id"));
                    cita.setMedicoId(rs.getInt("medico_id"));
                    cita.setFecha(rs.getDate("fecha"));
                    cita.setHora(rs.getTime("hora"));
                    cita.setEstado(rs.getString("estado"));
                    
                    cita.setNombrePaciente(rs.getString("nombre_paciente"));
                    cita.setNombreMedico(rs.getString("nombre_medico"));
                    cita.setNombreEspecialidad(rs.getString("nombre_especialidad"));
                    
                    citas.add(cita);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error SQL: " + e.getMessage());
            e.printStackTrace();
            throw e;
        }
        
        System.out.println("Total citas encontradas: " + citas.size()); // Debug
        return citas;
    }
} 
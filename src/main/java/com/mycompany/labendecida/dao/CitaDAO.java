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
} 
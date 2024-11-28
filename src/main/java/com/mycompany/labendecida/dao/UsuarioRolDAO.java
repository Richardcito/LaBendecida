package com.mycompany.labendecida.dao;

import com.mycompany.labendecida.config.Conexion;
import java.sql.*;

public class UsuarioRolDAO {
    private Connection conexion;

    public UsuarioRolDAO() {
        try {
            this.conexion = Conexion.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String obtenerRolPorUsuarioId(int usuarioId) {
        String sql = "SELECT r.nombre FROM roles r " +
                    "INNER JOIN usuario_roles ur ON r.id = ur.rol_id " +
                    "WHERE ur.usuario_id = ?";
        
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, usuarioId);
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                return rs.getString("nombre");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void asignarRol(int usuarioId, int rolId) {
        String sql = "INSERT INTO usuario_roles (usuario_id, rol_id) VALUES (?, ?)";
        
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, usuarioId);
            ps.setInt(2, rolId);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
} 
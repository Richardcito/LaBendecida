package com.mycompany.labendecida.dao;

import com.mycompany.labendecida.config.Conexion;
import com.mycompany.labendecida.model.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {
    private Connection conexion;

    public UsuarioDAO() {
        try {
            this.conexion = Conexion.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Usuario validarMedico(String email, String password) {
        Usuario medico = null;
        String sql = "SELECT u.*, m.id as medico_id, m.especialidad_id, m.numero_colegiado " +
                    "FROM usuarios u " +
                    "INNER JOIN medicos m ON u.id = m.usuario_id " +
                    "INNER JOIN usuario_roles ur ON u.id = ur.usuario_id " +
                    "INNER JOIN roles r ON ur.rol_id = r.id " +
                    "WHERE u.email = ? AND u.password = PASSWORD(?) " +
                    "AND r.nombre = 'MEDICO'";
        
        try {
            PreparedStatement ps = conexion.prepareStatement(sql);
            ps.setString(1, email);
            ps.setString(2, password);
            
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                medico = new Usuario();
                medico.setId(rs.getInt("id"));
                medico.setEmail(rs.getString("email"));
                medico.setMedicoId(rs.getInt("medico_id"));
                medico.setEspecialidadId(rs.getInt("especialidad_id"));
                medico.setNumeroColegiado(rs.getString("numero_colegiado"));
                medico.setNombre(obtenerNombreCompleto(rs.getInt("id")));
            }
            
            rs.close();
            ps.close();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return medico;
    }

    private String obtenerNombreCompleto(int usuarioId) {
        String nombreCompleto = "";
        String sql = "SELECT nombre, apellido FROM informacion_personal WHERE usuario_id = ?";
        
        try {
            PreparedStatement ps = conexion.prepareStatement(sql);
            ps.setInt(1, usuarioId);
            
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                nombreCompleto = rs.getString("nombre") + " " + rs.getString("apellido");
            }
            
            rs.close();
            ps.close();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return nombreCompleto;
    }

    // Método para verificar si existe un usuario con el email dado
    public boolean existeEmail(String email) {
        String sql = "SELECT COUNT(*) FROM usuarios WHERE email = ?";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try {
            conn = Conexion.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, email);
            
            // Debug
            System.out.println("Verificando email: " + email);
            System.out.println("SQL Query: " + sql);
            
            rs = ps.executeQuery();
            if (rs.next()) {
                int count = rs.getInt(1);
                // Debug
                System.out.println("Cantidad de emails encontrados: " + count);
                return count > 0;
            }
            return false;
            
        } catch (SQLException e) {
            System.out.println("Error al verificar email: " + e.getMessage());
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // Método para crear un nuevo usuario
    public boolean crearUsuario(Usuario usuario) {
        String sql = "INSERT INTO usuarios (email, password) VALUES (?, PASSWORD(?))";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setString(1, usuario.getEmail());
            stmt.setString(2, usuario.getPassword());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error al crear usuario: " + e.getMessage()); // Debug
            e.printStackTrace();
            return false;
        }
    }

    public int registrarUsuario(Usuario usuario, String nombre, String apellido) {
        Connection conn = null;
        PreparedStatement psUsuario = null;
        PreparedStatement psInfoPersonal = null;
        PreparedStatement psRol = null;
        ResultSet generatedKeys = null;
        
        try {
            conn = Conexion.getConnection();
            conn.setAutoCommit(false);
            
            // Insertar usuario
            String sqlUsuario = "INSERT INTO usuarios (email, password) VALUES (?, PASSWORD(?))";
            psUsuario = conn.prepareStatement(sqlUsuario, PreparedStatement.RETURN_GENERATED_KEYS);
            psUsuario.setString(1, usuario.getEmail());
            psUsuario.setString(2, usuario.getPassword());
            
            System.out.println("Ejecutando insert de usuario"); // Debug
            psUsuario.executeUpdate();
            
            // Obtener ID generado
            generatedKeys = psUsuario.getGeneratedKeys();
            if (!generatedKeys.next()) {
                throw new SQLException("Fallo al crear usuario, no se obtuvo ID.");
            }
            int usuarioId = generatedKeys.getInt(1);
            
            // Insertar información personal
            String sqlInfoPersonal = "INSERT INTO informacion_personal (usuario_id, nombre, apellido) VALUES (?, ?, ?)";
            psInfoPersonal = conn.prepareStatement(sqlInfoPersonal);
            psInfoPersonal.setInt(1, usuarioId);
            psInfoPersonal.setString(2, nombre);
            psInfoPersonal.setString(3, apellido);
            psInfoPersonal.executeUpdate();
            
            // Asignar rol de paciente
            String sqlRol = "INSERT INTO usuario_roles (usuario_id, rol_id) VALUES (?, (SELECT id FROM roles WHERE nombre = 'PACIENTE'))";
            psRol = conn.prepareStatement(sqlRol);
            psRol.setInt(1, usuarioId);
            psRol.executeUpdate();
            
            conn.commit();
            return usuarioId;
            
        } catch (SQLException e) {
            System.out.println("Error SQL: " + e.getMessage()); // Debug
            e.printStackTrace();
            try {
                if (conn != null) conn.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            return -1;
        } finally {
            // Cerrar recursos
            try {
                if (generatedKeys != null) generatedKeys.close();
                if (psUsuario != null) psUsuario.close();
                if (psInfoPersonal != null) psInfoPersonal.close();
                if (psRol != null) psRol.close();
                if (conn != null) {
                    conn.setAutoCommit(true);
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public Usuario validarUsuario(String email, String password) {
        Usuario usuario = null;
        String sql = "SELECT u.*, r.nombre as rol_nombre, ip.nombre as nombre_usuario, ip.apellido " +
                    "FROM usuarios u " +
                    "INNER JOIN usuario_roles ur ON u.id = ur.usuario_id " +
                    "INNER JOIN roles r ON ur.rol_id = r.id " +
                    "INNER JOIN informacion_personal ip ON u.id = ip.usuario_id " +
                    "WHERE u.email = ? AND u.password = PASSWORD(?)";
        
        try (Connection conn = Conexion.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setString(1, email);
            ps.setString(2, password);
            
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    usuario = new Usuario();
                    usuario.setId(rs.getInt("id"));
                    usuario.setEmail(rs.getString("email"));
                    usuario.setRol(rs.getString("rol_nombre"));
                    usuario.setNombre(rs.getString("nombre_usuario") + " " + rs.getString("apellido"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return usuario;
    }

    public int getTotalUsuarios() throws SQLException {
        String sql = "SELECT COUNT(*) FROM usuarios WHERE id NOT IN " +
                    "(SELECT usuario_id FROM medicos)";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        }
        return 0;
    }

    public int getTotalMedicos() throws SQLException {
        String sql = "SELECT COUNT(*) FROM medicos";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        }
        return 0;
    }

    public int getTotalEspecialidades() throws SQLException {
        String sql = "SELECT COUNT(*) FROM especialidades";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        }
        return 0;
    }

    public List<Usuario> getUsuariosRecientes(int limit) throws SQLException {
        List<Usuario> usuarios = new ArrayList<>();
        String sql = "SELECT u.*, ip.nombre, ip.apellido " +
                    "FROM usuarios u " +
                    "LEFT JOIN informacion_personal ip ON u.id = ip.usuario_id " +
                    "ORDER BY u.fecha_registro DESC LIMIT ?";
        
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setInt(1, limit);
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setId(rs.getInt("id"));
                usuario.setEmail(rs.getString("email"));
                
                // Construir el nombre completo
                String nombre = rs.getString("nombre");
                String apellido = rs.getString("apellido");
                if (nombre != null && apellido != null) {
                    usuario.setNombre(nombre + " " + apellido); // Asumiendo que tienes setNombre()
                } else {
                    usuario.setNombre(rs.getString("email")); // Si no hay nombre, usar email
                }
                usuarios.add(usuario);
            }
        }
        return usuarios;
    }
}
package com.mycompany.labendecida.dao;

import com.mycompany.labendecida.config.Conexion;
import com.mycompany.labendecida.model.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.mindrot.jbcrypt.BCrypt;

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

    public int registrarUsuario(Usuario usuario, String nombre, String apellido) throws SQLException {
        String sqlUsuario = "INSERT INTO usuarios (email, password, fecha_registro) VALUES (?, ?, NOW())";
        String sqlInfoPersonal = "INSERT INTO informacion_personal (usuario_id, nombre, apellido) VALUES (?, ?, ?)";
        
        try (Connection conn = Conexion.getConnection()) {
            conn.setAutoCommit(false);
            try {
                // Generar el hash de la contraseña
                String hashedPassword = BCrypt.hashpw(usuario.getPassword(), BCrypt.gensalt(12));
                System.out.println("Contraseña original: " + usuario.getPassword()); // Debug
                System.out.println("Contraseña hasheada: " + hashedPassword); // Debug
                
                // 1. Insertar usuario con la contraseña hasheada
                PreparedStatement pstmtUsuario = conn.prepareStatement(sqlUsuario, Statement.RETURN_GENERATED_KEYS);
                pstmtUsuario.setString(1, usuario.getEmail());
                pstmtUsuario.setString(2, hashedPassword); // Usar la contraseña hasheada
                pstmtUsuario.executeUpdate();

                // Obtener el ID generado
                int userId;
                try (ResultSet generatedKeys = pstmtUsuario.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        userId = generatedKeys.getInt(1);
                    } else {
                        throw new SQLException("No se pudo obtener el ID del usuario creado");
                    }
                }

                // 2. Insertar información personal
                PreparedStatement pstmtInfo = conn.prepareStatement(sqlInfoPersonal);
                pstmtInfo.setInt(1, userId);
                pstmtInfo.setString(2, nombre);
                pstmtInfo.setString(3, apellido);
                pstmtInfo.executeUpdate();

                conn.commit();
                return userId;
                
            } catch (SQLException e) {
                conn.rollback();
                e.printStackTrace();
                throw e;
            }
        }
    }

    public Usuario validarUsuario(String email, String password) {
        String sql = "SELECT u.*, ip.nombre, ip.apellido FROM usuarios u " +
                    "LEFT JOIN informacion_personal ip ON u.id = ip.usuario_id " +
                    "WHERE u.email = ?";
        
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                String hashedPassword = rs.getString("password");
                // Verificar la contraseña
                if (BCrypt.checkpw(password, hashedPassword)) {
                    Usuario usuario = new Usuario();
                    usuario.setId(rs.getInt("id"));
                    usuario.setEmail(rs.getString("email"));
                    usuario.setNombre(rs.getString("nombre"));
                    usuario.setApellido(rs.getString("apellido"));
                    return usuario;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public int getTotalUsuarios() throws SQLException {
        String sql = "SELECT COUNT(*) FROM usuarios u " +
                    "LEFT JOIN usuario_roles ur ON u.id = ur.usuario_id " +
                    "LEFT JOIN roles r ON ur.rol_id = r.id " +
                    "WHERE r.nombre = 'PACIENTE'";
                
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        }
        return 0;
    }

    public int getTotalMedicos() throws SQLException {
        String sql = "SELECT COUNT(*) FROM usuarios u " +
                    "INNER JOIN usuario_roles ur ON u.id = ur.usuario_id " +
                    "INNER JOIN roles r ON ur.rol_id = r.id " +
                    "WHERE r.nombre = 'MEDICO'";
                
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
        String sql = "SELECT u.*, ip.nombre, ip.apellido, r.nombre as rol_nombre " +
                    "FROM usuarios u " +
                    "LEFT JOIN informacion_personal ip ON u.id = ip.usuario_id " +
                    "LEFT JOIN usuario_roles ur ON u.id = ur.usuario_id " +
                    "LEFT JOIN roles r ON ur.rol_id = r.id " +
                    "ORDER BY u.fecha_registro DESC LIMIT ?";
        
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setInt(1, limit);
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setId(rs.getInt("id"));
                usuario.setEmail(rs.getString("email"));
                usuario.setNombre(rs.getString("nombre"));
                usuario.setApellido(rs.getString("apellido"));
                usuario.setRol(rs.getString("rol_nombre"));
                usuario.setFechaRegistro(rs.getTimestamp("fecha_registro"));
                usuarios.add(usuario);
            }
        }
        return usuarios;
    }

    public List<Usuario> getAllUsuarios() throws SQLException {
        List<Usuario> usuarios = new ArrayList<>();
        String sql = "SELECT u.*, ip.nombre, ip.apellido, r.nombre as rol_nombre " +
                    "FROM usuarios u " +
                    "LEFT JOIN informacion_personal ip ON u.id = ip.usuario_id " +
                    "LEFT JOIN usuario_roles ur ON u.id = ur.usuario_id " +
                    "LEFT JOIN roles r ON ur.rol_id = r.id";
        
        try (Connection conn = Conexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setId(rs.getInt("id"));
                usuario.setEmail(rs.getString("email"));
                usuario.setNombre(rs.getString("nombre"));
                usuario.setApellido(rs.getString("apellido"));
                usuario.setFechaRegistro(rs.getTimestamp("fecha_registro"));
                usuario.setRol(rs.getString("rol_nombre")); // Establecer el nombre del rol
                usuarios.add(usuario);
            }
        }
        return usuarios;
    }

    public Usuario getUsuarioById(int id) throws SQLException {
        String sql = "SELECT u.*, ip.nombre, ip.apellido, r.nombre as rol_nombre " +
                    "FROM usuarios u " +
                    "LEFT JOIN informacion_personal ip ON u.id = ip.usuario_id " +
                    "LEFT JOIN usuario_roles ur ON u.id = ur.usuario_id " +
                    "LEFT JOIN roles r ON ur.rol_id = r.id " +
                    "WHERE u.id = ?";
        
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setId(rs.getInt("id"));
                usuario.setEmail(rs.getString("email"));
                usuario.setNombre(rs.getString("nombre"));
                usuario.setApellido(rs.getString("apellido"));
                usuario.setFechaRegistro(rs.getTimestamp("fecha_registro"));
                return usuario;
            }
        }
        return null;
    }

    public void createUsuario(Usuario usuario) throws SQLException {
        String sqlUsuario = "INSERT INTO usuarios (email, password, fecha_registro) VALUES (?, ?, NOW())";
        String sqlInfoPersonal = "INSERT INTO informacion_personal (usuario_id, nombre, apellido) VALUES (?, ?, ?)";
        String sqlRol = "INSERT INTO usuario_roles (usuario_id, rol_id) VALUES (?, ?)";
        
        try (Connection conn = Conexion.getConnection()) {
            conn.setAutoCommit(false);
            try {
                // 1. Insertar usuario
                PreparedStatement pstmtUsuario = conn.prepareStatement(sqlUsuario, Statement.RETURN_GENERATED_KEYS);
                pstmtUsuario.setString(1, usuario.getEmail());
                pstmtUsuario.setString(2, usuario.getPassword());
                pstmtUsuario.executeUpdate();

                // Obtener el ID generado
                int userId;
                try (ResultSet generatedKeys = pstmtUsuario.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        userId = generatedKeys.getInt(1);
                    } else {
                        throw new SQLException("No se pudo obtener el ID del usuario creado");
                    }
                }

                // 2. Insertar información personal
                PreparedStatement pstmtInfo = conn.prepareStatement(sqlInfoPersonal);
                pstmtInfo.setInt(1, userId);
                pstmtInfo.setString(2, usuario.getNombre());
                pstmtInfo.setString(3, usuario.getApellido());
                pstmtInfo.executeUpdate();

                // 3. Insertar rol
                PreparedStatement pstmtRol = conn.prepareStatement(sqlRol);
                pstmtRol.setInt(1, userId);
                pstmtRol.setInt(2, usuario.getRol_id());
                pstmtRol.executeUpdate();

                // Si todo salió bien, hacer commit
                conn.commit();
                
            } catch (SQLException e) {
                // Si algo salió mal, hacer rollback
                conn.rollback();
                e.printStackTrace();
                throw new SQLException("Error al crear usuario: " + e.getMessage());
            }
        }
    }

    public void updateUsuario(Usuario usuario) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        
        try {
            conn = Conexion.getConnection();
            conn.setAutoCommit(false);
            
            // Actualizar información personal
            String sql = "UPDATE informacion_personal SET nombre = ?, apellido = ? WHERE usuario_id = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, usuario.getNombre());
            pstmt.setString(2, usuario.getApellido());
            pstmt.setInt(3, usuario.getId());
            pstmt.executeUpdate();
            
            // Actualizar email en usuarios
            sql = "UPDATE usuarios SET email = ? WHERE id = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, usuario.getEmail());
            pstmt.setInt(2, usuario.getId());
            pstmt.executeUpdate();
            
            // Actualizar rol
            sql = "UPDATE usuario_roles SET rol_id = ? WHERE usuario_id = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, usuario.getRol_id());
            pstmt.setInt(2, usuario.getId());
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

    public boolean deleteUsuario(int id) throws SQLException {
        String sql = "DELETE FROM usuarios WHERE id = ?";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        }
    }
}
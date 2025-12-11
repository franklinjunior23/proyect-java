/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.proyect.domain.repository;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import org.proyect.config.Database;
import org.proyect.domain.models.Usuario;
import org.proyect.utils.PasswordHasher;
/**
 *
 * @author frijol
 */
import org.mindrot.jbcrypt.BCrypt;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserRepository {

    // =========================================================
    // CREATE USER (con hash de contraseña)
    // =========================================================
    public Usuario createUser(Usuario user) throws SQLException {

        String sql = "INSERT INTO usuario (nombre, email, password, rol) VALUES (?, ?, ?, ?)";

        // Hashear contraseña antes de guardarla
        String hashed = PasswordHasher.hashPassword(user.getPassword());
        user.setPassword(hashed);

        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, user.getNombre());
            stmt.setString(2, user.getEmail());
            stmt.setString(3, hashed);
            stmt.setString(4, user.getRol().name());

            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) user.setId(rs.getInt(1));

            return user;
        }
    }

    // =========================================================
    // FIND BY ID
    // =========================================================
    public Usuario findById(Number id) throws Exception {

        String sql = "SELECT * FROM usuario WHERE id = ?";

        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, id.toString());

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return mapToUser(rs);
            }

            return null;
        }
    }


    // =========================================================
    // FIND BY EMAIL
    // =========================================================
    public Usuario findByEmail(String email) throws Exception {

        String sql = "SELECT * FROM usuario WHERE email = ?";

        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, email);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return mapToUser(rs);
            }

            return null;
        }
    }

    // =========================================================
    // FIND ALL
    // =========================================================
    public List<Usuario> findAll() throws Exception {
        List<Usuario> users = new ArrayList<>();

        String sql = "SELECT * FROM usuario ORDER BY id ASC";

        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                users.add(mapToUser(rs));
            }
        }
        return users;
    }

    // =========================================================
    // UPDATE USER (si envía password nueva, se hashea)
    // =========================================================
    public boolean update(Usuario user) throws Exception {
        String sql = """
                UPDATE usuario
                SET nombre = ?, email = ?
                WHERE id = ?
                """;

        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, user.getNombre());
            stmt.setString(2, user.getEmail());
            stmt.setInt(5, user.getId());

            return stmt.executeUpdate() > 0;
        }
    }

    // =========================================================
    // DELETE USER
    // =========================================================
    public boolean delete(int id) throws Exception {
        String sql = "DELETE FROM usuario WHERE id = ?";

        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        }
    }

    // =========================================================
    // MAPEAR RESULTSET A USER
    // =========================================================
    private Usuario mapToUser(ResultSet rs) throws Exception {
        Usuario user = new Usuario();

        user.setId(rs.getInt("id"));
        user.setNombre(rs.getString("nombre"));
        user.setEmail(rs.getString("email"));
        user.setPassword(rs.getString("password")); // si lo necesitas en el modelo
        user.setFechaCreacion(rs.getTimestamp("fecha_creacion").toLocalDateTime());
        user.setFechaActualizacion(rs.getTimestamp("fecha_actualizacion").toLocalDateTime());

        // Convertimos el role de texto a enum
        String roleStr = rs.getString("rol");
        user.setRol(Usuario.Rol.valueOf(roleStr));

        return user;
    }

}

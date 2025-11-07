/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.proyect.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author frijol
 */
public class Database {

    // Ruta del archivo SQLite (se creará en la carpeta del proyecto)
    private static final String URL = "jdbc:sqlite:db.db";

    // Método para conectar
    public static Connection connect() {
        Connection conn = null; 
        try {
            conn = DriverManager.getConnection(URL);
            System.out.println("✅ Conexión a SQLite establecida correctamente.");
        } catch (SQLException e) {
            System.out.println("❌ Error al conectar con la base de datos.");
            e.printStackTrace();
        }
        return conn;
    }

    // Crear tabla de usuarios si no existe
    public static void createUserTable() {
        String sql = """
                CREATE TABLE IF NOT EXISTS users (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    username TEXT NOT NULL UNIQUE,
                    password TEXT NOT NULL
                );
                """;

        try (Connection conn = connect();
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
            System.out.println("👤 Tabla 'users' lista para usar.");
        } catch (SQLException e) {
            System.out.println("❌ Error al crear la tabla 'users'.");
            e.printStackTrace();
        }
    }

    
    
    // Método principal para probar conexión
    public static void main(String[] args) {
        createUserTable(); // crea la tabla si no existe
    }
}
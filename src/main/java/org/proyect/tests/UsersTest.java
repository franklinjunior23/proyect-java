/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.proyect.tests;
import org.proyect.config.Database;
import org.proyect.models.User;

/**
 *
 * @author frijol
 */
public class UsersTest {
     public static void main(String[] args) {
        Database.createUserTable();

        // Crear usuario
        User.create("frijol", "123456");

        // Listar todos
        System.out.println(User.getAll());

        // Login correcto
        User u = User.findByUsernameAndPassword("frijol", "123456");
        if (u != null) {
            System.out.println("✅ Login correcto: " + u);
        } else {
            System.out.println("❌ Usuario o contraseña incorrectos.");
        }
    }
}

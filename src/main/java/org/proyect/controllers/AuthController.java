/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.proyect.controllers;


import org.proyect.domain.models.Usuario;
import org.proyect.service.UserService;

import javax.swing.*;

/**
 * @author frijol
 */
public class AuthController {
    private UserService userService;

    public AuthController() {
        this.userService = new UserService();
    }

    public Usuario SignIn(String email, String password) {
        try {
            System.out.println(password);
            return this.userService.login(email, password);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error de autenticación: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }

}

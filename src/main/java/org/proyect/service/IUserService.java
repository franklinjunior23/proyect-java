/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.proyect.service;
import org.proyect.domain.models.User;

/**
 *
 * @author frijol
 */
public interface IUserService {
    User signIn(String username, String rawPassword);
    boolean registerUser(String username, String rawPassword);
}
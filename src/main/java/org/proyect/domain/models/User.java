/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.proyect.domain.models;

import java.io.Serializable;

/**
 *
 * @author frijol
 */
public class User implements Serializable {

    // 1. ATRIBUTOS (Variables de la clase)
    private final int id;        // Usar 'final' porque el ID lo asigna la BD
    private String username;     // Contiene el nombre de usuario
    private String password;     // Contiene el HASH de la contraseña de la BD

    // 2. CONSTRUCTOR COMPLETO (Usado al LEER de la Base de Datos)
    public User(int id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    // 3. CONSTRUCTOR PARA NUEVO USUARIO (Usado al crear en la aplicación, la BD asignará el ID)
    public User(String username, String password) {
        // Llama al constructor completo, inicializando id a 0 o un valor por defecto
        this(0, username, password);
    }

    // 4. GETTERS (Para leer los valores)
    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    // Devolvemos el HASH; la capa de Servicio se encargará de la verificación
    public String getPassword() {
        return password;
    }

    // 5. SETTERS (Para modificar el objeto después de crearlo)
    // El setPassword es crucial si se cambia la contraseña.
    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // 6. MÉTODO toString (Recomendado para debugging, ocultando el hash)
    @Override
    public String toString() {
        return "User{id=" + id + ", username='" + username + "'}";
    }
}

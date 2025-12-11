package org.proyect.utils;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordHasher {

    // Costo (o fuerza) de hashing. 12 es un buen valor por defecto.
    private static final int HASH_STRENGTH = 12; 

    /**
     * Genera un hash seguro para una contraseña en texto plano usando BCrypt.
     * * @param rawPassword La contraseña original del usuario.
     * @return La cadena de texto que contiene el salt y el hash final.
     */
    public static String hashPassword(String rawPassword) {
        // BCrypt.hashpw genera automáticamente un "salt" aleatorio 
        // y lo combina con la contraseña y el hash en la cadena final.
        return BCrypt.hashpw(rawPassword, BCrypt.gensalt(HASH_STRENGTH));
    }

    /**
     * Verifica una contraseña en texto plano contra un hash existente.
     * * @param rawPassword La contraseña ingresada por el usuario (texto plano).
     * @param hashedPasswordFromDB El hash almacenado en la base de datos.
     * @return true si las contraseñas coinciden, false en caso contrario.
     */
    public static boolean verifyPassword(String rawPassword, String hashedPasswordFromDB) {
        // BCrypt.checkpw es un método diseñado para ser lento (seguro)
        // y maneja la extracción del salt y la comparación por ti.
        return BCrypt.checkpw(rawPassword, hashedPasswordFromDB);
    }
}
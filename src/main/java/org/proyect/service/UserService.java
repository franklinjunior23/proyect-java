package org.proyect.service;

import org.proyect.domain.models.Usuario;
import org.proyect.domain.repository.UserRepository;
import org.proyect.utils.PasswordHasher;

public class UserService {
    private final UserRepository userRepository;

    public UserService() {
        this.userRepository = new UserRepository();
    }

    public Usuario login(String email, String password) throws Exception {

        Usuario user = userRepository.findByEmail(email);
        if (user == null || user.getId() == null) {
            throw new Exception("Usuario no encontrado.");
        }

        boolean passwordOk = PasswordHasher.verifyPassword(password, user.getPassword());
        if (!passwordOk) {
            throw new Exception("Contraseña incorrecta.");
        }

        return user;
    }
}

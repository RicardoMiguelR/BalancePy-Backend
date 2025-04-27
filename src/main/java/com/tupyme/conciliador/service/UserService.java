package com.tupyme.conciliador.service;

import com.tupyme.conciliador.model.User;
import com.tupyme.conciliador.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service // Anotación que marca esta clase como un servicio de Spring
public class UserService {

    @Autowired
    private UserRepository userRepository; // Inyección del repositorio de usuarios

    // Método para registrar un nuevo usuario
    public User registerUser(User user) {
        // Verificación de que el correo no esté registrado previamente
        Optional<User> existing = userRepository.findByEmail(user.getEmail());
        if (existing.isPresent()) {
            throw new RuntimeException("El email ya se encuentra registrado"); // Lanza error si ya existe
        }
        return userRepository.save(user); // Guarda el nuevo usuario
    }
}

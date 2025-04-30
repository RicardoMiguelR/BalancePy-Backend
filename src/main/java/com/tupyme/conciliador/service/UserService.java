package com.tupyme.conciliador.service;

import com.tupyme.conciliador.dto.request.LoginDTO;
import com.tupyme.conciliador.dto.request.UserDTO;
import com.tupyme.conciliador.dto.response.ErrorResponse;
import com.tupyme.conciliador.model.User;
import com.tupyme.conciliador.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service // Anotación que marca esta clase como un servicio de Spring
public class UserService {

    @Autowired
    private UserRepository userRepository; // Inyección del repositorio de usuarios

    @Autowired
    private PasswordEncoder passwordEncoder;

    // Método para registrar un nuevo usuario ->
    public User registerUser(UserDTO userDTO) {
        // Verificación de que el correo no esté registrado previamente
        Optional<User> existing = userRepository.findByEmail(userDTO.getEmail());
        if (existing.isPresent()) {
            throw new RuntimeException("El email ya se encuentra registrado"); // Lanza error si ya existe
        }

        // Creación de un nuevo objeto User a partir de UserDTO
        User user = new User();
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        // Encriptación de la contraseña
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));

        return userRepository.save(user); // Guarda el nuevo usuario
    }

    public boolean loginUser(LoginDTO loginDTO) {
        // Buscar al usuario por correo
        Optional<User> optionalUser = userRepository.findByEmail(loginDTO.getEmail());

        // Si no existe el usuario con el correo proporcionado
        if (optionalUser.isEmpty()) {
            return false;
        }

        // Obtener el usuario desde el Optional
        User user = optionalUser.get();

        // Verificar que la contraseña ingresada coincide con la almacenada
        return passwordEncoder.matches(loginDTO.getPassword(), user.getPassword());
    }

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}

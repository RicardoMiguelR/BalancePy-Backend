package com.tupyme.conciliador.controller;

import com.tupyme.conciliador.dto.response.ErrorResponse;
import com.tupyme.conciliador.dto.request.LoginDTO;
import com.tupyme.conciliador.dto.response.SuccessResponse;
import com.tupyme.conciliador.dto.request.UserDTO;
import com.tupyme.conciliador.model.User;
import com.tupyme.conciliador.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/users") // Define la ruta base para todas las operaciones sobre usuarios
public class UserController {

    // Dependencias inyectadas (repositorio y codificador de contraseñas)
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserController(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // Registro de un nuevo usuario
    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody @Valid UserDTO userDTO) {
        // Creación de un nuevo objeto User a partir de UserDTO
        User user = new User();
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        // Encriptación de la contraseña
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));

        // Guardado del usuario en la base de datos
        User savedUser = userRepository.save(user);
        return ResponseEntity.ok(savedUser); // Retorna el usuario guardado en la respuesta
    }

    // Login de un usuario
    @PostMapping("/login")
    public ResponseEntity<?> userLogin(@RequestBody @Valid LoginDTO loginDTO) {
        // Buscar al usuario por correo
        Optional<User> optionalUser = userRepository.findByEmail(loginDTO.getEmail());

        // Si no existe el usuario con el correo proporcionado
        if (optionalUser.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ErrorResponse("Correo o contraseña incorrectos"));
        }

        // Obtener el usuario desde el Optional
        User user = optionalUser.get();

        // Verificar que la contraseña ingresada coincide con la almacenada
        boolean matchPassword = passwordEncoder.matches(loginDTO.getPassword(), user.getPassword());

        // Si las contraseñas no coinciden
        if (!matchPassword) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ErrorResponse("Correo o contraseña incorrectos"));
        }

        // Si todo está bien, respondemos con un mensaje de éxito
        return ResponseEntity.ok(new SuccessResponse("¡Se inicio sesion exitosamente!"));
    }

}
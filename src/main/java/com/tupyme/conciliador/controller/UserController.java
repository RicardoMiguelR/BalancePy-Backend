package com.tupyme.conciliador.controller;

import com.tupyme.conciliador.dto.response.ErrorResponse;
import com.tupyme.conciliador.dto.request.LoginDTO;
import com.tupyme.conciliador.dto.response.SuccessResponse;
import com.tupyme.conciliador.dto.request.UserDTO;
import com.tupyme.conciliador.model.User;
import com.tupyme.conciliador.repository.UserRepository;
import com.tupyme.conciliador.service.UserService;
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
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // Registro de un nuevo usuario
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody @Valid UserDTO userDTO) {
        try {
            // Guardado del usuario en la base de datos
            User user = userService.registerUser(userDTO);
            return ResponseEntity.ok(user); // Retorna el usuario guardado en la respuesta
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new ErrorResponse(e.getMessage()));
        }
    }

    // Login de un usuario
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid LoginDTO loginDTO) {
        boolean success = userService.loginUser (loginDTO);

        // Si las contraseñas no coinciden
        if (!success) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ErrorResponse("Correo o contraseña incorrectos"));
        }

        // Si todo está bien, respondemos con un mensaje de éxito
        return ResponseEntity.ok(new SuccessResponse("¡Se inicio sesion exitosamente!"));
    }

}
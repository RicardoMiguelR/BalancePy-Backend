package com.tupyme.conciliador.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class LoginDTO {

    // Validaciones de campos (anotaciones)
    @NotBlank(message = "El correo no puede estar vacio")
    @Email(message = "Formato de correo invalido") // Validación de formato de correo
    private String email;

    @NotBlank(message = "La contraseña no puede estar vacia") // Validación de campo obligatorio
    private String password;

    // Getters y Setters
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

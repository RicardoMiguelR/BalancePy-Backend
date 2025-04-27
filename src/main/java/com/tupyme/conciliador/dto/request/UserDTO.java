package com.tupyme.conciliador.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

public class UserDTO {

    @NotBlank(message = "El nombre no puede estar vacio") // Validación de campo obligatorio
    private String name;

    @NotBlank(message = "El correo no puede estar vacio")
    @Email(message = "El correo debe ser valido") // Validación de formato de correo
    private String email;

    @NotBlank(message = "La contraseña no puede estar vacia")
    @Size(min = 6, message = "La contraseña debe tener al menos 6 caracteres") // Validación de longitud mínima
    private String password;

    // Getters y Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

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

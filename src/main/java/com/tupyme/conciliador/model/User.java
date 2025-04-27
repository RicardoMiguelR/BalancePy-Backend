package com.tupyme.conciliador.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "users") // Definir que esta clase representa la tabla "users" en la base de datos
@Data // Lombok: genera getters, setters, equals, hashCode y toString automáticamente
@NoArgsConstructor // Constructor sin argumentos generado por Lombok
@AllArgsConstructor // Constructor con todos los atributos generado por Lombok
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Generación automática del ID
    private Long id;

    private String name; // Nombre del usuario

    @Column(unique = true) // Asegura que el correo será único
    private String email;

    private String password; // Contraseña del usuario
}

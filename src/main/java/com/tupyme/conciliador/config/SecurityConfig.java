package com.tupyme.conciliador.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    // Bean que define la configuración de seguridad para las rutas HTTP
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // Desactiva la protección CSRF (Cross-Site Request Forgery)
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().permitAll() // Permite el acceso sin autenticación a todas las rutas
                );
        return http.build(); // Retorna la configuración de seguridad construida
    }

    // Bean que devuelve un PasswordEncoder usando BCrypt para cifrar contraseñas
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // Usamos BCrypt para cifrar las contraseñas
    }

}
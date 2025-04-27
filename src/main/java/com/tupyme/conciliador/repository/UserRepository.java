package com.tupyme.conciliador.repository;

import com.tupyme.conciliador.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    // Método que busca un usuario por su correo electrónico
    Optional<User> findByEmail(String email);
}

package com.proyect.CompilAir.repositories;

import com.proyect.CompilAir.dto.request.RegisterRequest;
import com.proyect.CompilAir.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IUserRepository extends JpaRepository<User, Long> {
    static void save(RegisterRequest registerRequest) {
    }

    Optional<User> findByUsername(String username);
}

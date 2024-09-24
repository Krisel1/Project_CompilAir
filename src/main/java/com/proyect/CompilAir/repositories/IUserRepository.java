package com.proyect.CompilAir.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import com.proyect.CompilAir.models.User;

import java.util.Optional;



public interface IUserRepository extends JpaRepository<User, Long> {
    Optional<User> getTasksByUsername(String username);
    Optional<User> findByUsername(String username);
}
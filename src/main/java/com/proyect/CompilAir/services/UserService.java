package com.proyect.CompilAir.services;


import com.proyect.CompilAir.models.User;
import com.proyect.CompilAir.repositories.IUserRepository;

import java.util.Optional;

public class UserService {

    private final IUserRepository iuserRepository;

    public UserService(IUserRepository iuserRepository) {
        this.iuserRepository = iuserRepository;
    }
    public Optional<User> getUserById(Long id) {
        return iuserRepository.findById(id);
    }
}
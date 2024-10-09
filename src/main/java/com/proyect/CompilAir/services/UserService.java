package com.proyect.CompilAir.services;


import com.proyect.CompilAir.models.User;
import com.proyect.CompilAir.repositories.IUserRepository;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.stereotype.Service;

    @Service
    public class UserService {

        private final IUserRepository iuserRepository;

         public UserService(IUserRepository iuserRepository) {
            this.iuserRepository = iuserRepository;
        }

        public ArrayList<User> getAllUsers(){
        return(ArrayList<User>) iuserRepository.findAll();
    }

        public Optional<User> getUserById(Long id) {
            return iuserRepository.findById(id);
        }

        public User createUser(User newUser){
            return iuserRepository.save(newUser);
        }

        public void updateUser(User user){
            iuserRepository.save(user);
        }

        public String deleteUser(Long id){
            try{
                iuserRepository.deleteById(id);
            return "User has been deleted";
        }catch (Exception error){
            return "User not found";

        }
        }

    }

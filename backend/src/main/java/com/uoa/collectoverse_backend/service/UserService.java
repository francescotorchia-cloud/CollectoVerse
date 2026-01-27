package com.uoa.collectoverse_backend.service;

import com.uoa.collectoverse_backend.repository.UserRepository;
import com.uoa.collectoverse_backend.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;


    public User saveUser(User user){
        return userRepository.save(user);
    }

    public Optional<User> getUtenteById(long id) {
        User utente = userRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Utente con id " + id + " non trovato"));
        return Optional.ofNullable(utente);
    }

}

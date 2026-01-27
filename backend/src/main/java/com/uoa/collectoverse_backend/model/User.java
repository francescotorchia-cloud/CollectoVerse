package com.uoa.collectoverse_backend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Entity
@Getter
@NoArgsConstructor
@Setter
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    // BCryptPasswordEncoder come campo statico per riuso
    private static final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        setPassword(password); // hashata subito
    }

    public void setPassword(String password) {
        // hash della password con BCrypt
        this.password = passwordEncoder.encode(password);
    }
}
package UOA.backend.controller;

import UOA.backend.DTO.Request.UserRequest;
import UOA.backend.DTO.Response.UserResponse;
import UOA.backend.models.User;
import UOA.backend.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;


    @GetMapping("/{id}")
    public User getUser(@PathVariable UUID id) {
        return userService.getUser(id);
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable UUID id, @Valid @RequestBody User user) {
        return userService.update(id, user.getUsername(), user.getEmail(), user.getPassword());
    }

    @GetMapping
    public List<User> getUsers() {
        return userService.getUsers();
    }

    @GetMapping("/username/{username}")
    public User getUserByUsername(@PathVariable String username) {
        return userService.getUserByUsername(username);
    }

    @GetMapping("/email/{email}")
    public User getUserByEmail(@PathVariable String email) {
        return userService.getUserByEmail(email);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable UUID id) {
        userService.deleteUser(id);
    }

    @PostMapping("/login")
    public UserResponse login(@RequestBody UserRequest request) {
        return userService.login(request);
    }

    @PostMapping("/register")
    public UserResponse register(@RequestBody UserRequest request) {
        return userService.register(request);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleExceptions(IllegalArgumentException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }


}

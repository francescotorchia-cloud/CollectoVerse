package com.uoa.collectoverse_backend;

import com.uoa.collectoverse_backend.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUser(@PathVariable Long id) {
        Optional<User> userOptional = userService.getUtenteById(id);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            return ResponseEntity.ok(userMapper.toDTO(user));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO inputdto){
        //converto il DTO ricevuto in entity
        User userEntity = userMapper.toEntity(inputdto);
        //salvo nel db tramite service
        User result = userService.saveUser(userEntity);
        //riconverto l'utente in DTO per la risposta
        return ResponseEntity.ok(userMapper.toDTO(result));
    }
}

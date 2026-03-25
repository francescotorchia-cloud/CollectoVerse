package UOA.backend.service;

import UOA.backend.DTO.Request.UserRequest;
import UOA.backend.DTO.Response.UserResponse;
import UOA.backend.models.User;
import UOA.backend.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import UOA.backend.security.JwtService;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;



    public User update(Long id, String username, String email, String password) {
        User user = userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("User non trovato"));
        if (username != null && !username.isBlank()) user.setUsername(username);
        if (email != null && !email.isBlank()) user.setEmail(email);
        if (password != null && !password.isBlank()) user.setPassword(password);
        return userRepository.save(user);
    }

    public User getUser(Long id) {
        return userRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("User non trovato"));
    }

    public User getUserByUsername(String username) {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new EntityNotFoundException("User non trovato");
        }
        return user;
    }

    public User getUserByEmail(String email) {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new EntityNotFoundException("User non trovato");
        }
        return user;
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new EntityNotFoundException("User non trovato");
        }
        userRepository.deleteById(id);
    }

    public UserResponse login(UserRequest request){
        if(request == null){ throw new IllegalArgumentException("Request non valida");}
        String email = request.getEmail().toLowerCase();
        if(email == null || email.isBlank()){ throw new IllegalArgumentException("Email obbligatoria");}
        String password = request.getPassword();
        if(password == null || password.isBlank()){ throw new IllegalArgumentException("Password obbligatoria");}

        User user = userRepository.findByEmail(email);
        if(user == null) { throw new IllegalArgumentException("Credenziali non valide");}

        boolean passwordMatch = passwordEncoder.matches(password, user.getPassword());
        if(!passwordMatch){ throw new IllegalArgumentException("Credenziali non valide");}

        // --- Generazione JWT ---
        String jwtToken = jwtService.generateToken(user);

        UserResponse response = new UserResponse();
        response.setUsername(user.getUsername());
        response.setEmail(user.getEmail());
        response.setToken(jwtToken); // Inserisci il token nella risposta
        return response;
    }

    public UserResponse register(UserRequest request) {
        if(request == null){ throw new IllegalArgumentException("Request non valida");}
        String email = request.getEmail();
        if(email == null || email.isBlank()){ throw new IllegalArgumentException("Email obbligatoria");}
        String username = request.getUsername();
        if(username == null || username.isBlank()){ throw new IllegalArgumentException("Username obbligatorio");}
        String password = request.getPassword();
        if(password == null || password.isBlank()){ throw new IllegalArgumentException("Password obbligatoria");}
        User user = new User();
        user.setUsername(username);
        user.setEmail(email.toLowerCase());
        user.setPassword(passwordEncoder.encode(password));

        User savedUser = userRepository.save(user);

        UserResponse response = new UserResponse();
        response.setUsername(savedUser.getUsername());
        response.setEmail(savedUser.getEmail());
        return response;
    }








}

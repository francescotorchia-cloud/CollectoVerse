package UOA.backend.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "users")
@Data //Con Lombok, genera automaticamente getter e setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;
}

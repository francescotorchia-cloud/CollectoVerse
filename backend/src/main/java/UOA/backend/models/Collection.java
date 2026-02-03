package UOA.backend.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Generated;

import java.util.List;

@Entity
@Table(name ="collections")
@Data

public class Collection {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String title;
    @Column(nullable = true)
    private String description;


    //Proprietario della collezione
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    //items della collezione
    //aggiunta di cascade se no non vengono salvati/eliminati mai
    @OneToMany(mappedBy = "collection", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Item> items;






}

package UOA.backend.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


import java.util.List;
import java.util.UUID;

@Entity
@Table(name ="collections")
@Getter
@Setter

public class Collection {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(nullable = false)
    private String title;
    @Column
    private String description;


    // proprietario della collezione
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    //items della collezione
    @ManyToMany
    @JoinTable(
            name = "collection_item",
            joinColumns = @JoinColumn(name = "collection_id"),
            inverseJoinColumns = @JoinColumn(name = "item_id")
    )
    private List<Item> items;


}

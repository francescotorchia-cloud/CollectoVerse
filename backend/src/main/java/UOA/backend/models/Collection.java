package UOA.backend.models;

import jakarta.persistence.*;
import lombok.Data;


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
    @Column
    private String description;

    //items della collezione
    @ManyToMany
    @JoinTable(
            name = "collection_item",
            joinColumns = @JoinColumn(name = "collection_id"),
            inverseJoinColumns = @JoinColumn(name = "item_id")
    )
    private List<Item> items;


}

package UOA.backend.models;

import UOA.backend.category.Category;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
@Entity
@Table(name= "items" )
@Data
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String description;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Category category;
    @Column(nullable = false)
    private LocalDateTime date = LocalDateTime.now();  //definisce la data di aggiunta di un item

    @ManyToOne
    @JoinColumn(name = "collection_id", nullable = false)
    private Collection collection;

}


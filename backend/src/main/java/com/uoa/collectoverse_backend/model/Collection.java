package com.uoa.collectoverse_backend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@Setter
@Table(name = "collections")
public class Collection {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Nome della collezione (es: "Vinili Rock Anni 80")
    @Column(nullable = false)
    private String name;

    // Descrizione testuale della collezione
    @Column(length = 1000)
    private String description;

    // Categoria standard (FILM, MUSICA, CARTE, VIDEOGIOCHI)
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CollectionCategory category;

    // Immagine di copertina della collezione (URL o path)
    private String coverImage;

    // Data di creazione della collezione
    @Column(nullable = false)
    private LocalDateTime createdAt;

    // Proprietario della collezione
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User owner;

    // Lista degli item appartenenti alla collezione
    @OneToMany(mappedBy = "collection", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Item> items;

    public Collection(String name, String description, CollectionCategory category, User owner) {
        this.name = name;
        this.description = description;
        this.category = category;
        this.owner = owner;
        this.createdAt = LocalDateTime.now();
    }
}
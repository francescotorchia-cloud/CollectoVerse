package com.uoa.collectoverse_backend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@Setter
@Table(name = "items")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Nome dell'item (es: "Charizard Base Set", "PlayStation 2")
    @Column(nullable = false)
    private String name;

    // Descrizione testuale dell'item
    @Column(length = 1000)
    private String description;

    // Immagine principale dell'item (URL o path)
    private String image;

    // Simbolo/icona usata per la rappresentazione grafica (cerchio, rettangolo, ecc)
    private String symbol;

    // Data di inserimento dell'item nella collezione
    @Column(nullable = false)
    private LocalDateTime createdAt;

    // Collezione di appartenenza
    @ManyToOne
    @JoinColumn(name = "collection_id", nullable = false)
    private Collection collection;

    public Item(String name, String description, String image, String symbol, Collection collection) {
        this.name = name;
        this.description = description;
        this.image = image;
        this.symbol = symbol;
        this.collection = collection;
        this.createdAt = LocalDateTime.now();
    }
}
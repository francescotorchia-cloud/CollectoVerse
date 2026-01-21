package com.uoa.collectoverse_backend.model;

import jakarta.persistence.*;

@Entity
@Table(name = "items")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    private String symbol; // per rappresentazione grafica dell’item

    @ManyToOne
    @JoinColumn(name = "collection_id")
    private Collection collection;

    public Item() {}

    public Item(String name, String description, String symbol, Collection collection) {
        this.name = name;
        this.description = description;
        this.symbol = symbol;
        this.collection = collection;
    }

    // getter e setter
    public Long getId() { return id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getSymbol() { return symbol; }
    public void setSymbol(String symbol) { this.symbol = symbol; }
    public Collection getCollection() { return collection; }
    public void setCollection(Collection collection) { this.collection = collection; }
}
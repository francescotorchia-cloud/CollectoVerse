package com.uoa.collectoverse_backend.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "collections")
public class Collection {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private String description;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User owner;

    @OneToMany(mappedBy = "collection", cascade = CascadeType.ALL)
    private List<Item> items;

    public Collection() {}

    public Collection(String name, String description, User owner) {
        this.name = name;
        this.description = description;
        this.owner = owner;
    }

    // getter e setter
    public Long getId() { return id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public User getOwner() { return owner; }
    public void setOwner(User owner) { this.owner = owner; }
    public List<Item> getItems() { return items; }
    public void setItems(List<Item> items) { this.items = items; }
}
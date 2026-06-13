package com.example.blog.model;
import jakarta.persistence.*;

@Entity
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    // Default constructor
    public Author() {}
    public Author(String name) {
        this.name = name;
    }
    // Gtters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
}
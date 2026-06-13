package com.example.blog.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Title cannot be empty!")
    @Size(min = 5, message = "Title must be at least 5 characters long")
    private String title;
    private String content;
    private String imageFileName;
    @ManyToOne
    private Author author;
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }
    public String getImageFileName() { return imageFileName; }
    public void setImageFileName(String imageFileName) { this.imageFileName = imageFileName; }
    public Author getAuthor() { return author; }
    public void setAuthor(Author author) { this.author = author; }
}
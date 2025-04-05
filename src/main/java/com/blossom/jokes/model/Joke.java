package com.blossom.jokes.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "jokes")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Joke {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Content is required")
    @Size(min = 10, max = 500, message = "Content must be between 10 and 500 characters")
    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    @NotBlank(message = "Author is required")
    @Size(min = 2, max = 100, message = "Author must be between 2 and 100 characters")
    @Column(nullable = false)
    private String author;

    @Column
    private String category = "general";

    @Column
    private Integer likes = 0;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}
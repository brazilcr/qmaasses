package com.example.quimaassesment.entity;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.Set;
@Getter
@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private Category parent;

    // Getters and setters
}
package com.example.quimaassesment.dto;

import com.example.quimaassesment.entity.Product;
import lombok.Getter;

@Getter
public class ProductDTO {
    private Long id;
    private String name;
    private String description;
    private double price;
    private String categoryPath;
    private boolean available;

    public ProductDTO(Product product, String categoryPath) {
        this.id = product.getId();
        this.name = product.getName();
        this.description = product.getDescription();
        this.price = product.getPrice();
        this.categoryPath = categoryPath;
        this.available = product.isAvailable();
    }

    // Getters e Setters
}

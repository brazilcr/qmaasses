package com.example.quimaassesment.repository;

import com.example.quimaassesment.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findAll();
    List<Product> findByAvailable(Boolean available);
    List<Product> findByNameContaining(String name);
    List<Product> findByCategoryId(Long categoryId);

    @Query("SELECT p FROM Product p JOIN FETCH p.category c ORDER BY p.name ASC")
    List<Product> findAllWithCategoriesSorted();


}

package com.example.quimaassesment.service;

import com.example.quimaassesment.entity.Category;
import com.example.quimaassesment.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public Category getCategoryById(Long id) {
        return categoryRepository.findById(id).orElse(null);
    }

    public void saveCategory(Category category) {
        categoryRepository.save(category);
    }

    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }

    public String getCategoryPath(Category category) {
        StringBuilder path = new StringBuilder();

        while (category != null) {
            path.insert(0, category.getName() + " > ");
            category = category.getParent();
        }

        return path.length() > 0 ? path.substring(0, path.length() - 3) : "";
    }
}

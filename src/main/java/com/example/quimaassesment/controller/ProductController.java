package com.example.quimaassesment.controller;

import com.example.quimaassesment.dto.ProductDTO;
import com.example.quimaassesment.entity.Category;
import com.example.quimaassesment.entity.Product;
import com.example.quimaassesment.service.CategoryService;
import com.example.quimaassesment.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/list")
    public String getAllProducts(Model model) {
        List<Product> products = productService.getAllProducts();

        List<ProductDTO> productDTOs = products.stream()
                .map(product -> {
                    String categoryPath = categoryService.getCategoryPath(product.getCategory());
                    return new ProductDTO(product, categoryPath);
                })
                .collect(Collectors.toList());

        model.addAttribute("products", productDTOs);
        return "product/list";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("product", new Product());
        List<Category> categories = categoryService.getAllCategories();
        model.addAttribute("categories", categories);
        return "product/add";
    }

    @PostMapping("/add")
    public String addProduct(@ModelAttribute Product product, @RequestParam Long categoryId) {
        Category category = categoryService.getCategoryById(categoryId);
        if (category != null) {
            product.setCategory(category);
        } else {
            return "redirect:/product/list";
        }

        productService.saveProduct(product);
        return "redirect:/product/list";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Product product = productService.getProductById(id);
        if (product == null) {
            return "redirect:/product/list";
        }
        model.addAttribute("product", product);
        List<Category> categories = categoryService.getAllCategories();
        model.addAttribute("categories", categories);
        return "product/edit";
    }

    @PostMapping("/edit")
    public String updateProduct(@ModelAttribute("product") Product product, @RequestParam Long categoryId) {
        Category category = categoryService.getCategoryById(categoryId);
        product.setCategory(category);

        productService.saveProduct(product);
        return "redirect:/product/list";
    }

    @PostMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return "redirect:/product/list";
    }
}

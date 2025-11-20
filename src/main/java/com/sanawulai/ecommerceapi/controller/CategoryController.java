package com.sanawulai.ecommerceapi.controller;

import com.sanawulai.ecommerceapi.Service.CategoryService;
import com.sanawulai.ecommerceapi.model.Category;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CategoryController {

    private CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("api/public/categories")
    public List<Category> getAllCategories() {
        return categoryService.getAllCategories();
    }

    @PostMapping("api/public/categories")
    public String createCategory(@RequestBody Category category) {
        categoryService.createCategory(category);
        return "Category added successfully";
    }

    @DeleteMapping("api/public/categories/{categoryId}")
    public String deleteCategory(@PathVariable long categoryId){
        String status = categoryService.deleteCategory(categoryId);
        return status;
    }
}

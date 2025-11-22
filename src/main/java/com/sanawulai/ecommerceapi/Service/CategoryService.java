package com.sanawulai.ecommerceapi.Service;

import com.sanawulai.ecommerceapi.model.Category;

import java.util.List;

public interface CategoryService {
    List<Category> getAllCategories();
    void createCategory(Category category);
    String deleteCategory(long categoryId);



    Category updateCategory(Category category, Long categoryId);
}

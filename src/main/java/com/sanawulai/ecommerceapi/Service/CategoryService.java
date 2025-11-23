package com.sanawulai.ecommerceapi.Service;

import com.sanawulai.ecommerceapi.model.Category;
import com.sanawulai.ecommerceapi.payload.CategoryResponse;

import java.util.List;

public interface CategoryService {
    CategoryResponse getAllCategories();
    void createCategory(Category category);
    String deleteCategory(long categoryId);



    Category updateCategory(Category category, Long categoryId);
}

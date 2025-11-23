package com.sanawulai.ecommerceapi.Service;

import com.sanawulai.ecommerceapi.model.Category;
import com.sanawulai.ecommerceapi.payload.CategoryDTO;
import com.sanawulai.ecommerceapi.payload.CategoryResponse;

import java.util.List;

public interface CategoryService {
    CategoryResponse getAllCategories();
    CategoryDTO createCategory(CategoryDTO categoryDTO);
    CategoryDTO deleteCategory(long categoryId);

    CategoryDTO updateCategory(CategoryDTO categoryDTO, Long categoryId);
}

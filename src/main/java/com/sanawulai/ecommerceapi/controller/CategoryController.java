package com.sanawulai.ecommerceapi.controller;

import com.sanawulai.ecommerceapi.Service.CategoryService;
import com.sanawulai.ecommerceapi.model.Category;
import com.sanawulai.ecommerceapi.payload.CategoryDTO;
import com.sanawulai.ecommerceapi.payload.CategoryResponse;
import com.sanawulai.ecommerceapi.repository.CategoryRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;
    @Autowired
    private CategoryRepository categoryRepository;


    @GetMapping("/public/categories")
    public ResponseEntity<CategoryResponse> getAllCategories() {
        CategoryResponse categoryResponse = categoryService.getAllCategories();
        return new ResponseEntity<>(categoryResponse,HttpStatus.OK);
    }

    @PostMapping("/public/categories")
    public ResponseEntity<CategoryDTO> createCategory(@Valid @RequestBody CategoryDTO categoryDTO) {
        categoryService.createCategory(categoryDTO);
        CategoryDTO savedCategoryDTO = categoryService.createCategory(categoryDTO);
        return new ResponseEntity<>(savedCategoryDTO, HttpStatus.CREATED);
    }

    @DeleteMapping("/admin/categories/{categoryId}")
    public ResponseEntity<CategoryDTO> deleteCategory(@PathVariable long categoryId){
            CategoryDTO deltedCategory = categoryService.deleteCategory(categoryId);
            return new ResponseEntity<>(deltedCategory, HttpStatus.OK);

    }

    @PutMapping("/public/categories/{categoryId}")
    public ResponseEntity<CategoryDTO>updateCategory(@Valid @RequestBody CategoryDTO categoryDTO,
                                                @PathVariable Long categoryId){

            CategoryDTO savedCategoryDTO = categoryService.updateCategory(categoryDTO,categoryId);
            return new ResponseEntity<>(savedCategoryDTO, HttpStatus.OK);

    }
}

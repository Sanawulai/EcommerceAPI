package com.sanawulai.ecommerceapi.Service.Impl;

import com.sanawulai.ecommerceapi.Service.CategoryService;

import com.sanawulai.ecommerceapi.exception.APIException;
import com.sanawulai.ecommerceapi.exception.ResourceNotFoundException;
import com.sanawulai.ecommerceapi.model.Category;
import com.sanawulai.ecommerceapi.payload.CategoryDTO;
import com.sanawulai.ecommerceapi.payload.CategoryResponse;
import com.sanawulai.ecommerceapi.repository.CategoryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ModelMapper modelMapper;




    @Override
    public CategoryResponse getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        if (categories.isEmpty()) {
            throw new APIException("You havent created any category yet.. Please try again this time around creating a category");
        }
        List<CategoryDTO> categoryDTOS = categories
                .stream().map(category -> modelMapper
                        .map(category, CategoryDTO.class)).toList();
        CategoryResponse categoryResponse = new CategoryResponse();
        categoryResponse.setContent(categoryDTOS);
        return categoryResponse;
    }

    @Override
    public void createCategory(Category category) {
        Category savedCategory = categoryRepository.findByCategoryName(category.getCategoryName());
        if (savedCategory != null) {
            throw new APIException("Category with the name " + category.getCategoryName() + " already exists");
        }
        categoryRepository.save(category);


    }

    @Override
    public String deleteCategory(long categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(()->new ResourceNotFoundException("Category","categoryId",categoryId));

        categoryRepository.delete(category);
        return "Category Id: "+categoryId + " deleted succesfully";
    }

    @Override
    public Category updateCategory(Category category, Long categoryId) {
        Optional<Category>savedCategoryOptional = categoryRepository.findById(categoryId);
        Category savedCategory = savedCategoryOptional
                .orElseThrow(()->new ResourceNotFoundException("Category","categoryId",categoryId));

        category.setCategoryId(categoryId);
        savedCategory = categoryRepository.save(category);
        return savedCategory;


    }

}

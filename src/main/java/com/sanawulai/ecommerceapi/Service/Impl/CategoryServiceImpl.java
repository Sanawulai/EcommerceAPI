package com.sanawulai.ecommerceapi.Service.Impl;

import com.sanawulai.ecommerceapi.Service.CategoryService;
import com.sanawulai.ecommerceapi.model.Category;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    private List<Category> categories =new ArrayList<>();
    private Long nextId=1L;
    @Override
    public List<Category> getAllCategories() {
        return categories;
    }

    @Override
    public void createCategory(Category category) {
        category.setCategoryId(nextId++);
        categories.add(category);


    }

    @Override
    public String deleteCategory(long categoryId) {
        Category category = categories.stream()
                .filter(c->c.getCategoryId()==categoryId)
                .findFirst().orElse(null);
        if(category==null){
            return "Category Id: "+categoryId + " not found";
        }
        categories.remove(category);
        return "Category Id: "+categoryId + " deleted succesfully";
    }
}

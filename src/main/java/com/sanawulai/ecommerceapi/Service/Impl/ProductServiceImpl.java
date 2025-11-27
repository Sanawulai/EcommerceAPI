package com.sanawulai.ecommerceapi.Service.Impl;

import com.sanawulai.ecommerceapi.Service.ProductService;
import com.sanawulai.ecommerceapi.exception.ResourceNotFoundException;
import com.sanawulai.ecommerceapi.model.Category;
import com.sanawulai.ecommerceapi.model.Product;
import com.sanawulai.ecommerceapi.payload.ProductDTO;
import com.sanawulai.ecommerceapi.repository.CategoryRepository;
import com.sanawulai.ecommerceapi.repository.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public ProductDTO addProduct(Long categoryId, Product product) {

        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "categoryId", categoryId));

        return null;
    }
}

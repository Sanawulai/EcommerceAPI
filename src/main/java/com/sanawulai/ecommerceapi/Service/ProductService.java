package com.sanawulai.ecommerceapi.Service;


import com.sanawulai.ecommerceapi.model.Product;
import com.sanawulai.ecommerceapi.payload.ProductDTO;
import com.sanawulai.ecommerceapi.payload.ProductResponse;

public interface ProductService {

    ProductDTO addProduct(Long categoryId, Product product);

    ProductResponse getAllProducts();
}

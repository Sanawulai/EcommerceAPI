package com.sanawulai.ecommerceapi.controller;

import com.sanawulai.ecommerceapi.Service.ProductService;
import com.sanawulai.ecommerceapi.model.Product;
import com.sanawulai.ecommerceapi.payload.ProductDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/")
public class ProductController {

    @Autowired
    private ProductService productService;

    public ResponseEntity<ProductDTO> addProduct(@RequestBody Product product,
                                                 @PathVariable Long categoryId){

        ProductDTO productDTO = productService.addProduct(categoryId,product);
        return productDTO;

    }

}

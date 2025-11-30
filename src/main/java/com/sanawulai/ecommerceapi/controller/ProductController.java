package com.sanawulai.ecommerceapi.controller;

import com.sanawulai.ecommerceapi.Service.ProductService;
import com.sanawulai.ecommerceapi.payload.ProductDTO;
import com.sanawulai.ecommerceapi.payload.ProductResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/admin/categories/{categoryId}/product")
    public ResponseEntity<ProductDTO> addProduct(@RequestBody ProductDTO productDTO,
                                                 @PathVariable Long categoryId){

        ProductDTO savedProductDTO = productService.addProduct(categoryId,productDTO);
        return new  ResponseEntity<>(savedProductDTO, HttpStatus.CREATED);

    }

    @GetMapping("/public/products")
    public ResponseEntity<ProductResponse> getAllProducts(){
        ProductResponse productResponse = productService.getAllProducts();
        return new ResponseEntity<>(productResponse,HttpStatus.OK);
    }

    @GetMapping("/public/categories/{categoryId}/products")
    public ResponseEntity<ProductResponse> getProductsByCategory(@PathVariable long categoryId){
       ProductResponse productResponse = productService.searchByCategory(categoryId);
       return new ResponseEntity<>(productResponse,HttpStatus.OK);
    }

    @GetMapping("/public/products/keyword/{keyword}")
    public ResponseEntity<ProductResponse> getProductsByKeyWord(@PathVariable String keyword){

        ProductResponse productResponse = productService.searchProductByKeyWord(keyword);
        return new ResponseEntity<>(productResponse,HttpStatus.FOUND);
    }

    @PutMapping("/admin/products/{productId}")
    public ResponseEntity<ProductDTO> updateProduct(@RequestBody ProductDTO productDTO,
                                                    @PathVariable long productId){
        ProductDTO updatedProductDTO = productService.updateProduct(productId,productDTO);
        return new ResponseEntity<>(updatedProductDTO, HttpStatus.OK);

    }

    @DeleteMapping("/admin/products/{productId}")
    public ResponseEntity<ProductDTO> deleteProduct(@PathVariable long productId){
        ProductDTO  deletedProduct = productService.deleteProduct(productId);
        return new ResponseEntity<>(deletedProduct,HttpStatus.OK);
    }

    @PutMapping("admin/products/{productId}/image")
    public ResponseEntity<ProductDTO> updatePrductImage(@PathVariable long productId,
                                                        @RequestParam("image") MultipartFile image) throws IOException {
        ProductDTO updateProduct = productService.updateProductImage(productId,image);
        return new ResponseEntity<>(updateProduct,HttpStatus.OK);
    }

}

package com.sanawulai.ecommerceapi.Service;


import com.sanawulai.ecommerceapi.payload.ProductDTO;
import com.sanawulai.ecommerceapi.payload.ProductResponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ProductService {

    ProductDTO addProduct(Long categoryId, ProductDTO product);

    ProductResponse getAllProducts();

    ProductResponse searchByCategory(long categoryId);

    ProductResponse searchProductByKeyWord(String keyword);

    ProductDTO updateProduct(long productId, ProductDTO product);

    ProductDTO deleteProduct(long productId);

    ProductDTO updateProductImage(long productId, MultipartFile image) throws IOException;
}

package com.sanawulai.ecommerceapi.Service.Impl;

import com.sanawulai.ecommerceapi.Service.ProductService;
import com.sanawulai.ecommerceapi.exception.ResourceNotFoundException;
import com.sanawulai.ecommerceapi.model.Category;
import com.sanawulai.ecommerceapi.model.Product;
import com.sanawulai.ecommerceapi.payload.ProductDTO;
import com.sanawulai.ecommerceapi.payload.ProductResponse;
import com.sanawulai.ecommerceapi.repository.CategoryRepository;
import com.sanawulai.ecommerceapi.repository.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public ProductDTO addProduct(Long categoryId, ProductDTO productDTO) {

        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Category", "categoryId", categoryId));

        Product product = modelMapper.map(productDTO, Product.class);

        product.setCategory(category);
        product.setImage("default.png");
        double specialPrice = product.getPrice() - (product.getPrice() * product.getDiscount() / 100);
        product.setSpecialPrice(specialPrice);
        Product savedProduct = productRepository.save(product);
        return modelMapper.map(savedProduct, ProductDTO.class);
    }

    @Override
    public ProductResponse getAllProducts() {

        List<Product> products = productRepository.findAll();
        List<ProductDTO> productDTOS = products
                .stream().map(product -> modelMapper.map(product, ProductDTO.class)).toList();
        ProductResponse productResponse = new ProductResponse();
        productResponse.setContent(productDTOS);
        return productResponse;
    }

    @Override
    public ProductResponse searchByCategory(long categoryId) {

        Category category = categoryRepository.findById(categoryId)
            .orElseThrow(() -> new ResourceNotFoundException("Category", "categoryId", categoryId));

        List<Product> products = productRepository.findByCategoryOrderByPriceAsc(category);
        List<ProductDTO> productDTOS = products
                .stream().map(product -> modelMapper.map(product, ProductDTO.class)).toList();
        ProductResponse productResponse = new ProductResponse();
        productResponse.setContent(productDTOS);
        return productResponse;
    }

    @Override
    public ProductResponse searchProductByKeyWord(String keyword) {
        List<Product> products = productRepository.findByProductNameLikeIgnoreCase('%'+ keyword+'%');
        List<ProductDTO> productDTOS = products
                .stream().map(product -> modelMapper.map(product, ProductDTO.class)).toList();
        ProductResponse productResponse = new ProductResponse();
        productResponse.setContent(productDTOS);
        return productResponse;
    }

    @Override
    public ProductDTO updateProduct(long productId, ProductDTO productDTO) {

        Product product = modelMapper.map(productDTO, Product.class);

        //Get the existing product from the db
        Product productFromDB = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product", "productId", productId));

        //update the product info with the one in the db
        productFromDB.setProductName(product.getProductName());
        productFromDB.setDescription(product.getDescription());
        productFromDB.setQuantity(product.getQuantity());
        productFromDB.setDiscount(product.getDiscount());
        productFromDB.setPrice(product.getPrice());
        productFromDB.setSpecialPrice(product.getSpecialPrice());

        //save to the database
        Product savedProduct = productRepository.save(productFromDB);

        return modelMapper.map(savedProduct,ProductDTO.class);


    }

    @Override
    public ProductDTO deleteProduct(long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product", "productId", productId));

        productRepository.delete(product);
        return modelMapper.map(product,ProductDTO.class);
    }

    @Override
    public ProductDTO updateProductImage(long productId, MultipartFile image) throws IOException {

        //Get the product form the db
        Product productFromDb = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product", "productId", productId));
        //upload image to server
        //Get the file name of uploaded image
        String path = "/images/";
        String fileName = uploadImage(path,image);

        //updating the new file name to the product
        productFromDb.setImage(fileName);

        //save updated product
        Product updatedProduct = productRepository.save(productFromDb);

        //return DTO after mapping product to DTO
        return modelMapper.map(updatedProduct,ProductDTO.class);
    }




}

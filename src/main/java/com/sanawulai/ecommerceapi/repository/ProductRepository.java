package com.sanawulai.ecommerceapi.repository;

import com.sanawulai.ecommerceapi.model.Category;
import com.sanawulai.ecommerceapi.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository <Product,Long>{


    List<Product> findByCategoryOrderByPriceAsc(Category category);
}

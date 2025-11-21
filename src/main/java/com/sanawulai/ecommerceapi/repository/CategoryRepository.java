package com.sanawulai.ecommerceapi.repository;

import com.sanawulai.ecommerceapi.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Long> {
}

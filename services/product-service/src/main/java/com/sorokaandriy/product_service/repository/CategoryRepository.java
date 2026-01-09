package com.sorokaandriy.product_service.repository;

import com.sorokaandriy.product_service.category.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Long> {
}

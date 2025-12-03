package com.sorokaandriy.product.repository;

import com.sorokaandriy.product.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Long> {

}

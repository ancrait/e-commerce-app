package com.sorokaandriy.product_service.service;

import com.sorokaandriy.product_service.category.Category;
import com.sorokaandriy.product_service.dto.ProductPurchaseRequest;
import com.sorokaandriy.product_service.dto.ProductPurchaseResponse;
import com.sorokaandriy.product_service.dto.ProductRequest;
import com.sorokaandriy.product_service.dto.ProductResponse;
import com.sorokaandriy.product_service.exception.CategoryNotFoundException;
import com.sorokaandriy.product_service.product.Product;
import com.sorokaandriy.product_service.repository.CategoryRepository;
import com.sorokaandriy.product_service.repository.ProductRepository;
import jakarta.validation.Valid;
import org.jspecify.annotations.Nullable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper mapper;
    private final CategoryRepository categoryRepository;

    public ProductService(ProductRepository repository, ProductMapper mapper, CategoryRepository categoryRepository) {
        this.productRepository = repository;
        this.mapper = mapper;
        this.categoryRepository = categoryRepository;
    }

    public @Nullable ProductResponse createProduct(@Valid ProductRequest request) {
        Product product = mapper.fromProductRequestToProduct(request);
        productRepository.save(product);
        Category category = categoryRepository.findById(product.getCategory().getId())
                .orElseThrow(() -> new CategoryNotFoundException("Category with id " + product.getCategory().getId()
                + " not found"));
        return mapper.fromProductToProductResponse(product,category);
    }

    public @Nullable List<ProductPurchaseResponse> purchase(List<ProductPurchaseRequest> requests) {
        return null;
    }

    public @Nullable ProductResponse findById(Long id) {
        return null;
    }

    public @Nullable List<ProductResponse> findAll() {
        return null;
    }
}

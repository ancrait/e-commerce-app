package com.sorokaandriy.product.service;

import com.sorokaandriy.product.dto.ProductRequest;
import com.sorokaandriy.product.dto.ProductResponse;
import com.sorokaandriy.product.model.Category;
import com.sorokaandriy.product.model.Product;
import org.springframework.stereotype.Service;

@Service
public class ProductMapper {
    public Product fromRequestToProduct(ProductRequest request) {

        return Product.builder()
                .id(request.id())
                .name(request.name())
                .description(request.description())
                .availableQuantity(request.availableQuantity())
                .price(request.price())
                .category(Category.builder()
                        .id(request.categoryId())
                        .build())
                .build();

    }

    public ProductResponse fromProductToResponse(Product product) {

        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .availableQuantity(product.getAvailableQuantity())
                .price(product.getPrice())
                .categoryId(product.getCategory().getId())
                .categoryName(product.getCategory().getName())
                .categoryDescription(product.getCategory().getDescription())
                .build();
    }
}

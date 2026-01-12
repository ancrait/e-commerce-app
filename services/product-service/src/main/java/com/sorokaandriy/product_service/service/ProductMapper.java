package com.sorokaandriy.product_service.service;

import com.sorokaandriy.product_service.category.Category;
import com.sorokaandriy.product_service.dto.ProductPurchaseResponse;
import com.sorokaandriy.product_service.dto.ProductRequest;
import com.sorokaandriy.product_service.dto.ProductResponse;
import com.sorokaandriy.product_service.product.Product;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.jspecify.annotations.Nullable;
import org.springframework.stereotype.Service;

@Service
public class ProductMapper {

    public Product fromProductRequestToProduct(@Valid ProductRequest request) {
        return Product.builder()
                .id(request.id())
                .name(request.name())
                .description(request.description())
                .availableQuantity(request.availableQuantity())
                .price(request.price())
                .category(Category.builder().id(request.categoryId()).build())
                .build();
    }

    public @Nullable ProductResponse fromProductToProductResponse(Product product,Category category) {
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .availableQuantity(product.getAvailableQuantity())
                .price(product.getPrice())
                .category(category)
                .build();
    }

    public ProductPurchaseResponse fromProductToProductPurchaseResponse(Product product, @NotNull(message = "Quantity is mandatory") double quantity) {
        return ProductPurchaseResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .quantity(quantity)
                .build();
    }
}

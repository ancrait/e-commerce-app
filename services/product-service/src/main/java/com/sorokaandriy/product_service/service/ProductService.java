package com.sorokaandriy.product_service.service;

import com.sorokaandriy.product_service.category.Category;
import com.sorokaandriy.product_service.dto.ProductPurchaseRequest;
import com.sorokaandriy.product_service.dto.ProductPurchaseResponse;
import com.sorokaandriy.product_service.dto.ProductRequest;
import com.sorokaandriy.product_service.dto.ProductResponse;
import com.sorokaandriy.product_service.exception.CategoryNotFoundException;
import com.sorokaandriy.product_service.exception.ProductNotFoundException;
import com.sorokaandriy.product_service.exception.ProductPurchaseException;
import com.sorokaandriy.product_service.product.Product;
import com.sorokaandriy.product_service.repository.CategoryRepository;
import com.sorokaandriy.product_service.repository.ProductRepository;
import jakarta.validation.Valid;
import org.jspecify.annotations.Nullable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
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

    public @Nullable List<ProductPurchaseResponse> purchase(List<ProductPurchaseRequest> request) {
        List<Long> productIds = request.stream().map(pr -> pr.id()).toList();
        List<Product> storedProducts = productRepository.findAllByIdInOrderById(productIds);
        if (productIds.size() != storedProducts.size()){
            throw new ProductNotFoundException("Product with specified id not found");
        }

        List<ProductPurchaseRequest> storedRequest = request
                .stream()
                .sorted(Comparator.comparing(ProductPurchaseRequest::id))
                .toList();

        List<ProductPurchaseResponse> purchasedProducts = new ArrayList<>();
        for (int i = 0; i < storedProducts.size(); i++) {
            Product product = storedProducts.get(i);
            ProductPurchaseRequest purchaseRequest = storedRequest.get(i);
            if (product.getAvailableQuantity() < purchaseRequest.quantity()){
                throw new ProductPurchaseException("Insufficient stock quantity for product" +
                        " with id " + product.getId());
            }

            double availableQuantity = product.getAvailableQuantity() - purchaseRequest.quantity();
            product.setAvailableQuantity(availableQuantity);
            productRepository.save(product);

            purchasedProducts.add(i, mapper.fromProductToProductPurchaseResponse(product,purchaseRequest.quantity()));

        }

        return purchasedProducts;
    }

    public @Nullable ProductResponse findById(Long id) {
        Product product =  productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException("Product with id " + id +
                " not found") );
        Category category = categoryRepository.findById(product.getCategory().getId())
                .orElseThrow(() -> new CategoryNotFoundException("Category with id " + product.getCategory().getId()
                        + " not found"));
        return mapper.fromProductToProductResponse(product,category);
    }

    public @Nullable List<ProductResponse> findAll() {
        List<Product> products = productRepository.findAll();
        List<ProductResponse> productResponses = new ArrayList<>();
        for (int i = 0; i < products.size(); i++) {
            Product product = products.get(i);
            Category category = products.get(i).getCategory();
            productResponses.add(i,mapper.fromProductToProductResponse(product,category));
        }

        return productResponses;
    }
}

package com.sorokaandriy.product.service;

import com.sorokaandriy.product.dto.ProductPurchaseRequest;
import com.sorokaandriy.product.dto.ProductPurchaseResponse;
import com.sorokaandriy.product.dto.ProductRequest;
import com.sorokaandriy.product.dto.ProductResponse;
import com.sorokaandriy.product.exception.ProductNotFoundException;
import com.sorokaandriy.product.model.Product;
import com.sorokaandriy.product.repository.ProductRepository;
import org.jspecify.annotations.Nullable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public ProductService(ProductRepository productRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }


    public ProductResponse createProduct(ProductRequest request) {
        Product product = productMapper.fromRequestToProduct(request);
        productRepository.save(product);
        return productMapper.fromProductToResponse(product);
    }

    public List<ProductPurchaseResponse> purchaseProduct(List<ProductPurchaseRequest> request) {

    }

    public ProductResponse findById(Long id) {
         return productMapper
                 .fromProductToResponse(productRepository
                 .findById(id)
                 .orElseThrow(() -> new ProductNotFoundException("Product with " + id + " not found")));
    }

    public List<ProductResponse> findAll() {
        return productRepository.findAll()
                .stream().map(product -> productMapper.fromProductToResponse(product))
                .collect(Collectors.toList());
    }
}

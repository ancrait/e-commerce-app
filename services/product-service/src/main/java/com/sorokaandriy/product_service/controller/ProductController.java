package com.sorokaandriy.product_service.controller;

import com.sorokaandriy.product_service.dto.ProductPurchaseRequest;
import com.sorokaandriy.product_service.dto.ProductPurchaseResponse;
import com.sorokaandriy.product_service.dto.ProductRequest;
import com.sorokaandriy.product_service.dto.ProductResponse;
import com.sorokaandriy.product_service.product.Product;
import com.sorokaandriy.product_service.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ProductResponse> createProduct(
            @RequestBody @Valid ProductRequest request
            ){
        return ResponseEntity.ok(service.createProduct(request));
    }

    @PostMapping
    public ResponseEntity<List<ProductPurchaseResponse>> purchaseProducts(
            @RequestBody List<ProductPurchaseRequest> requests
    ){
        return ResponseEntity.ok(service.purchase(requests));
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<ProductResponse> findById(@PathVariable Long id){
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<ProductResponse>> findByAll(){
        return ResponseEntity.ok(service.findAll());
    }
}

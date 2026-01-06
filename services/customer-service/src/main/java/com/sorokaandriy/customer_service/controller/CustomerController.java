package com.sorokaandriy.customer_service.controller;

import com.sorokaandriy.customer_service.dto.CustomerRequest;
import com.sorokaandriy.customer_service.repository.CustomerRepository;
import com.sorokaandriy.customer_service.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerService service;

    public CustomerController(CustomerService service, CustomerRepository repository) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<CustomerResponse> createCustomer(
            @RequestBody @Valid CustomerRequest customerRequest
    ){
        return ResponseEntity.ok(service.createCustomer(customerRequest));
    }
}

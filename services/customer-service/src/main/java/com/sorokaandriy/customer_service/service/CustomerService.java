package com.sorokaandriy.customer_service.service;

import com.sorokaandriy.customer_service.dto.CustomerRequest;
import com.sorokaandriy.customer_service.repository.CustomerRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    private final CustomerRepository repository;

    public CustomerService(CustomerRepository repository) {
        this.repository = repository;
    }

    public CustomerResponse createCustomer(@Valid CustomerRequest customerRequest) {

    }
}

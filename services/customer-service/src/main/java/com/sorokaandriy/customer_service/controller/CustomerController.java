package com.sorokaandriy.customer_service.controller;

import com.sorokaandriy.customer_service.dto.CustomerRequest;
import com.sorokaandriy.customer_service.dto.CustomerResponse;
import com.sorokaandriy.customer_service.repository.CustomerRepository;
import com.sorokaandriy.customer_service.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PutMapping
    public ResponseEntity<Void> updateCustomer(
            @RequestBody @Valid CustomerRequest customerRequest
    ){
        service.updateCustomer(customerRequest);
        return ResponseEntity.accepted().build();
    }

    @GetMapping
    public ResponseEntity<List<CustomerResponse>> findAll(){
        return ResponseEntity.ok(service.findAllCustomers());
    }

    @GetMapping("/exists/{id}")
    public ResponseEntity<Boolean> existsCustomerById(@PathVariable String id){
        return ResponseEntity.ok(service.existsCustomerById(id));
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<CustomerResponse> findById(@PathVariable String id){
        return ResponseEntity.ok(service.findCustomerById(id));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable String id){
        service.deleteCustomerById(id);
        return ResponseEntity.accepted().build();
    }

}

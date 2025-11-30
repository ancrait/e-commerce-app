package com.sorrokaandriy.customer.controller;

import com.sorrokaandriy.customer.model.Customer;
import com.sorrokaandriy.customer.model.CustomerRequest;
import com.sorrokaandriy.customer.model.CustomerResponse;
import com.sorrokaandriy.customer.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    public ResponseEntity<CustomerRequest> createCustomer(@RequestBody @Valid CustomerRequest request){
        return ResponseEntity.ok(customerService.createCustomer(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerRequest> updateCustomer(@PathVariable String id,
                                                          @RequestBody @Valid CustomerRequest request){
        return ResponseEntity.ok(customerService.updateCustomer(id,request));
    }

    @GetMapping
    public ResponseEntity<List<CustomerResponse>> findAll(){
        return ResponseEntity.ok(customerService.findAllCustomers());
    }
}

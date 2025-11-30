package com.sorrokaandriy.customer.service;

import com.sorrokaandriy.customer.model.Customer;
import com.sorrokaandriy.customer.model.CustomerRequest;
import com.sorrokaandriy.customer.model.CustomerResponse;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapper {
    public Customer fromRequestToCustomer(CustomerRequest request) {
        return Customer.builder()
                .id(request.id())
                .firstname(request.firstname())
                .lastname(request.lastname())
                .email(request.email())
                .address(request.address())
                .build();
    }

    public CustomerRequest fromCustomerToRequest(Customer customer) {
        return CustomerRequest
                .builder()
                .id(customer.getId())
                .firstname(customer.getFirstname())
                .lastname(customer.getLastname())
                .email(customer.getEmail())
                .address(customer.getAddress())
                .build();
    }

    public CustomerResponse fromCustomerToResponse(Customer customer){
        return CustomerResponse
                .builder()
                .id(customer.getId())
                .firstname(customer.getFirstname())
                .lastname(customer.getLastname())
                .email(customer.getEmail())
                .address(customer.getAddress())
                .build();
    }
}

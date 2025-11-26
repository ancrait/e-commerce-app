package com.sorrokaandriy.customer.service;

import com.sorrokaandriy.customer.model.Customer;
import com.sorrokaandriy.customer.model.CustomerRequest;

public class CustomerMapper {
    public Customer toCustomer(CustomerRequest request) {
        return Customer.builder()
                .id(request.id())
                .firstname(request.firstname())
                .lastname(request.lastname())
                .email(request.email())
                .address(request.address())
                .build();
    }
}

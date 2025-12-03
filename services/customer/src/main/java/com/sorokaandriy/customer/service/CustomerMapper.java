package com.sorokaandriy.customer.service;

import com.sorokaandriy.customer.model.Customer;
import com.sorokaandriy.customer.dto.CustomerRequest;
import com.sorokaandriy.customer.dto.CustomerResponse;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
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


    public CustomerResponse fromRequestToResponse(CustomerRequest request){

        return CustomerResponse
                .builder()
                .id(request.id())
                .firstname(request.firstname())
                .lastname(request.lastname())
                .email(request.email())
                .address(request.address())
                .build();
    }

}

package com.sorokaandriy.customer_service.service;

import com.sorokaandriy.customer_service.dto.CustomerRequest;
import com.sorokaandriy.customer_service.dto.CustomerResponse;
import com.sorokaandriy.customer_service.model.Customer;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

@Service
public class CustomerMapper {

    public Customer fromCustomerRequestToCustomer(@Valid CustomerRequest customerRequest) {
        if(customerRequest == null){
            return null;
        }
        return new Customer(
                customerRequest.id(),
                customerRequest.firstname(),
                customerRequest.lastname(),
                customerRequest.email(),
                customerRequest.address()
        );
    }

    public CustomerResponse fromCustomerToCustomerResponse(Customer customer) {

        return new CustomerResponse(
                customer.getId(),
                customer.getFirstname(),
                customer.getLastname(),
                customer.getEmail(),
                customer.getAddress()
        );
    }
}

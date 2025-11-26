package com.sorrokaandriy.customer.service;

import com.sorrokaandriy.customer.model.CustomerRequest;
import com.sorrokaandriy.customer.repository.CustomerRepository;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    public CustomerService(CustomerRepository customerRepository, CustomerMapper customerMapper) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
    }

    public CustomerRequest createCustomer(CustomerRequest request){
        var customer = customerRepository.save(customerMapper.toCustomer(request));
    }
}

package com.sorrokaandriy.customer.service;

import com.sorrokaandriy.customer.exception.CustomerNotFoundException;
import com.sorrokaandriy.customer.model.Customer;
import com.sorrokaandriy.customer.model.CustomerRequest;
import com.sorrokaandriy.customer.model.CustomerResponse;
import com.sorrokaandriy.customer.repository.CustomerRepository;
import org.apache.catalina.mapper.Mapper;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    public CustomerService(CustomerRepository customerRepository, CustomerMapper customerMapper) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
    }

    public CustomerRequest createCustomer(CustomerRequest request) {
        var customer = customerRepository.save(customerMapper.fromRequestToCustomer(request));
        return request;
    }

    public CustomerRequest updateCustomer(String id, CustomerRequest request) {
        Customer customer = customerRepository
                .findById(id)
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found wit this " + id));
        mergeCustomer(customer,request);
        customerRepository.save(customer);
        return request;
    }

    public List<CustomerResponse> findAllCustomers() {
        return customerRepository.findAll()
                .stream().map(customer -> customerMapper.fromCustomerToResponse(customer))
                .collect(Collectors.toList());
    }

    private void mergeCustomer(Customer customer, CustomerRequest request) {
        if (StringUtils.isNotBlank(request.firstname())){
            customer.setFirstname(request.firstname());
        }
        if (StringUtils.isNotBlank(request.lastname())){
            customer.setLastname(request.lastname());
        }
        if (StringUtils.isNotBlank(request.email())){
            customer.setEmail(request.email());
        }
        if (request.address()!=null){
            customer.setAddress(request.address());
        }
    }


}

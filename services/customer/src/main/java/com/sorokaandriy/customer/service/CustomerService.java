package com.sorokaandriy.customer.service;

import com.sorokaandriy.customer.exception.CustomerNotFoundException;
import com.sorokaandriy.customer.model.Customer;
import com.sorokaandriy.customer.dto.CustomerRequest;
import com.sorokaandriy.customer.dto.CustomerResponse;
import com.sorokaandriy.customer.repository.CustomerRepository;
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

    public CustomerResponse createCustomer(CustomerRequest request) {
        var customer = customerRepository.save(customerMapper.fromRequestToCustomer(request));
        return customerMapper.fromCustomerToResponse(customer);
    }

    public CustomerResponse updateCustomer(String id, CustomerRequest request) {
        Customer customer = customerRepository
                .findById(id)
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found wit this " + id));
        mergeCustomer(customer,request);
        customerRepository.save(customer);
        return customerMapper.fromCustomerToResponse(customer);
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

    public Boolean existCustomerById(String id) {
        return customerRepository.findById(id).isPresent();
    }

    public CustomerResponse findById(String id) {
        Customer customer = customerRepository
                .findById(id)
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found wit this " + id));
        return customerMapper.fromCustomerToResponse(customer);
    }

    public void deleteById(String id) {
         customerRepository.deleteById(id);
    }
}

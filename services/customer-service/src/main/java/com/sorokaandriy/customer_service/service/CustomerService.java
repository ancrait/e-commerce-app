package com.sorokaandriy.customer_service.service;

import com.sorokaandriy.customer_service.dto.CustomerRequest;
import com.sorokaandriy.customer_service.dto.CustomerResponse;
import com.sorokaandriy.customer_service.exception.CustomerNotFoundException;
import com.sorokaandriy.customer_service.model.Customer;
import com.sorokaandriy.customer_service.repository.CustomerRepository;
import jakarta.validation.Valid;
import org.apache.commons.lang.StringUtils;
import org.jspecify.annotations.Nullable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomerService {

    private final CustomerRepository repository;
    private final CustomerMapper mapper;

    public CustomerService(CustomerRepository repository, CustomerMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public CustomerResponse createCustomer(@Valid CustomerRequest customerRequest) {
        Customer customer = mapper.fromCustomerRequestToCustomer(customerRequest);
        repository.save(customer);
        return mapper.fromCustomerToCustomerResponse(customer);
    }


    public void updateCustomer(@Valid CustomerRequest customerRequest) {

        Customer customer = repository.findById(customerRequest.id())
                .orElseThrow(() -> new CustomerNotFoundException("Cannot update customer " + customerRequest.id()
                        + " no found customer with this id"));
        mergeCustomer(customer,customerRequest);
        repository.save(customer);
    }

    private void mergeCustomer(Customer customer, @Valid CustomerRequest customerRequest) {
        if (StringUtils.isNotBlank(customerRequest.firstname())){
            customer.setFirstname(customerRequest.firstname());
        }
        if (StringUtils.isNotBlank(customerRequest.lastname())){
            customer.setLastname(customerRequest.lastname());
        }
        if (StringUtils.isNotBlank(customerRequest.email())){
            customer.setEmail(customerRequest.email());
        }
        if (customerRequest.address() != null){
            customer.setAddress(customerRequest.address());
        }

    }

    public @Nullable List<CustomerResponse> findAllCustomers() {
        return repository.findAll().stream().map(customer -> mapper.fromCustomerToCustomerResponse(customer))
                .collect(Collectors.toList());
    }

    public @Nullable Boolean existsCustomerById(String id) {
        return repository.findById(id).isPresent();

    }


    public CustomerResponse findCustomerById(String id) {
        return repository.findById(id)
                .map(c -> mapper.fromCustomerToCustomerResponse(c))
                .orElseThrow(() -> new CustomerNotFoundException("Cannot update customer " + id
                        + " no found customer with this id"));
    }


    public void deleteCustomerById(String id) {
        repository.deleteById(id);
    }
}

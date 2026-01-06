package com.sorokaandriy.customer_service.repository;

import com.sorokaandriy.customer_service.model.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends MongoRepository<Customer,String> {
}

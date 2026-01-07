package com.sorokaandriy.customer_service.dto;


import com.sorokaandriy.customer_service.model.Address;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record CustomerResponse(

        String id,
        String firstname,
        String lastname,
        String email,
        Address address
) {
}

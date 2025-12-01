package com.sorokaandriy.customer.dto;

import com.sorokaandriy.customer.model.Address;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record CustomerRequest(
        String id,
        @NotNull(message = "Customer firstname is required")
        String firstname,
        @NotNull(message = "Customer lastname is required")
        String lastname,
        @Email(message = "Not valid email address")
        String email,
        Address address
) {


}

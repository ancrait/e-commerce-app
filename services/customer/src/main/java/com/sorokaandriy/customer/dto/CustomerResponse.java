package com.sorokaandriy.customer.dto;

import com.sorokaandriy.customer.model.Address;
import lombok.Builder;

@Builder
public record CustomerResponse(
        String id,
        String firstname,
        String lastname,
        String email,
        Address address
) {
}

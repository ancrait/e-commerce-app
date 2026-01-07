package com.sorokaandriy.customer_service.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = false)
@Data
public class CustomerNotFoundException extends RuntimeException{
    private final String message;
}

package com.sorokaandriy.product_service.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = false)
@Data
public class ProductNotFoundException extends RuntimeException {
    private final String message;
}

package com.sorokaandriy.order_service.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = false)
@Data
public class OrderNotFoundException extends RuntimeException{
    private final String message;
}
